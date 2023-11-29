package org.study.port.in;

import org.study.domain.entity.ClientEntity;

import java.util.UUID;

public interface ClientCoreIntegration {
    ClientEntity save(ClientEntity clientEntity);

    PageCoreIntegration<ClientEntity> findAll(String name, PageableCoreIntegration pageable);

    ClientEntity findById(UUID id);

    void delete(UUID id);
}