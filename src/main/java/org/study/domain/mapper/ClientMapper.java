package org.study.domain.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.study.adapter.in.model.ClientModel;
import org.study.domain.entity.ClientEntity;

@ApplicationScoped
@RequiredArgsConstructor
public class ClientMapper {

    public ClientEntity mapOf(ClientModel model) {
        var mapper = new ModelMapper();
        return mapper.map(model, ClientEntity.class);
    }

    public ClientModel mapOf(ClientEntity entity) {
        var mapper = new ModelMapper();
        return mapper.map(entity, ClientModel.class);
    }
}