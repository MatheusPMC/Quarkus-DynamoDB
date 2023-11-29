package org.study.port.out;

import org.study.domain.entity.ClientEntity;
import org.study.port.in.PageCoreIntegration;
import org.study.port.in.PageableCoreIntegration;

import java.util.Optional;
import java.util.UUID;

public interface ClientDataIntegration {
    ClientEntity save(ClientEntity clientEntity);

    PageCoreIntegration<ClientEntity> findAll(String name, PageableCoreIntegration pageable);

    Optional<ClientEntity> findById(UUID id);
}