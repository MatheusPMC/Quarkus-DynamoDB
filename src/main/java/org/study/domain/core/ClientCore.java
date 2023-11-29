package org.study.domain.core;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import org.study.domain.entity.ClientEntity;
import org.study.domain.exceptions.ClientNotFoundException;
import org.study.port.in.ClientCoreIntegration;
import org.study.port.in.PageCoreIntegration;
import org.study.port.in.PageableCoreIntegration;
import org.study.port.out.ClientDataIntegration;

import java.util.UUID;

@ApplicationScoped
@RequiredArgsConstructor
public class ClientCore implements ClientCoreIntegration {
    private final ClientDataIntegration clientData;

    @Override
    public ClientEntity save(ClientEntity clientEntity) {
        return clientData.save(clientEntity);
    }

    @Override
    public PageCoreIntegration<ClientEntity> findAll(String name, PageableCoreIntegration pageable) {
        return clientData.findAll(name, pageable);
    }

    @Override
    public ClientEntity findById(UUID id) {
        ClientEntity client = clientData.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(String.format("Client for ID %s does not exist", id)));
        return client;
    }
}