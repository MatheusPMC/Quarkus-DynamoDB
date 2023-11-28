package org.study.port.in;

import org.study.domain.entity.ClientEntity;

public interface ClientCoreIntegration {
    ClientEntity save(ClientEntity clientEntity);
}