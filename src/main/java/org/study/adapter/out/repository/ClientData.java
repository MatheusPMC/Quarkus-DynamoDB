package org.study.adapter.out.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import org.study.domain.core.PageCore;
import org.study.domain.entity.ClientEntity;
import org.study.port.in.PageCoreIntegration;
import org.study.port.in.PageableCoreIntegration;
import org.study.port.out.ClientDataIntegration;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@RequiredArgsConstructor
public class ClientData implements ClientDataIntegration {
    private final DynamoDBMapper dynamoDBMapper;

    @Override
    public ClientEntity save(ClientEntity clientEntity) {
        dynamoDBMapper.save(clientEntity);
        return clientEntity;
    }

    @Override
    public PageCoreIntegration<ClientEntity> findAll(String name, PageableCoreIntegration pageable) {

        /* Testig methods
// Using 'PaginatedScanList'
        final DynamoDBScanExpression paginatedScanListExpression = new DynamoDBScanExpression()
                .withLimit(5);
        final PaginatedScanList<ClientEntity> paginatedList = dynamoDBMapper.scan(ClientEntity.class, paginatedScanListExpression);
        paginatedList.forEach(System.out::println);

        System.out.println();
// using 'ScanResultPage'
        Map<String, AttributeValue> test = new HashMap<>();
        var test2 = new AttributeValue();
        test2.setS("4c6185e6-09f8-4f1c-83a3-d1d466ce6a47");
        test.put("Id", test2);
        final DynamoDBScanExpression scanPageExpression = new DynamoDBScanExpression()
                .withLimit(5).withExclusiveStartKey(test);
        do {
            ScanResultPage<ClientEntity> scanPage = dynamoDBMapper.scanPage(ClientEntity.class, scanPageExpression);
            scanPage.getResults().forEach(System.out::println);
            System.out.println("LastEvaluatedKey=" + scanPage.getLastEvaluatedKey());
            scanPageExpression.setExclusiveStartKey(scanPage.getLastEvaluatedKey());
        } while (scanPageExpression.getExclusiveStartKey() != null);
        */


        var conditions = new HashMap<String, Condition>();

        if (name != null) {
            conditions.put("Name", new Condition().withComparisonOperator(ComparisonOperator.BEGINS_WITH)
                    .withAttributeValueList(new AttributeValue().withS(name)));
        }
        DynamoDBScanExpression scan = new DynamoDBScanExpression().withScanFilter(conditions);

        List<ClientEntity> clientsEntities = dynamoDBMapper.scan(ClientEntity.class, scan);
        return new PageCore<>(clientsEntities, pageable, clientsEntities.size());
    }

    @Override
    public Optional<ClientEntity> findById(UUID id) {
        var clientEntity = this.dynamoDBMapper.load(ClientEntity.class, id);
        return Optional.ofNullable(clientEntity);
    }

    @Override
    public void delete(UUID id) {
        var clientEntity = this.dynamoDBMapper.load(ClientEntity.class, id);
        Optional.ofNullable(clientEntity)
                .ifPresent(this.dynamoDBMapper::delete);
    }
}