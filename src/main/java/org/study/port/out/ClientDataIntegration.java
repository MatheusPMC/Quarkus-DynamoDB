package org.study.port.out;

import org.study.domain.entity.ClientEntity;

public interface ClientDataIntegration {
    ClientEntity save(ClientEntity clientEntity);
}
