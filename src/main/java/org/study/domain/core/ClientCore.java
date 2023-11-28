package org.study.domain.core;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import org.study.domain.entity.ClientEntity;
import org.study.port.in.ClientCoreIntegration;
import org.study.port.out.ClientDataIntegration;

@ApplicationScoped
@RequiredArgsConstructor
public class ClientCore implements ClientCoreIntegration {
    private final ClientDataIntegration clientData;

    @Override
    public ClientEntity save(ClientEntity clientEntity) {
        var test = clientData.save(clientEntity);
        System.out.println(test);
        return test;
    }
}