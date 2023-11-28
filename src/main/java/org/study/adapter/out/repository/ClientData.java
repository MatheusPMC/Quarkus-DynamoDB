package org.study.adapter.out.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import org.study.domain.entity.ClientEntity;
import org.study.port.out.ClientDataIntegration;

@ApplicationScoped
@RequiredArgsConstructor
public class ClientData implements ClientDataIntegration {
    private final DynamoDBMapper dynamoDBMapper;

    @Override
    public ClientEntity save(ClientEntity clientEntity) {
        dynamoDBMapper.save(clientEntity);
        return clientEntity;
    }
}
