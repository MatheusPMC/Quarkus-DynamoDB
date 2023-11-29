package org.study.domain.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.study.adapter.in.model.ClientModel;
import org.study.domain.entity.ClientEntity;
import org.study.domain.enumerations.Gender;

@ApplicationScoped
@RequiredArgsConstructor
public class ClientMapper {

    public ClientEntity mapOf(ClientModel model) {
        var mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<ClientModel, ClientEntity>() {
            @Override
            protected void configure() {
                using((Converter<String, Gender>) mappingContext ->
                        Gender.fromPrefix(mappingContext.getSource().charAt(0)))
                        .map(this.source.getGender(), this.destination.getGender());
            }
        });
        return mapper.map(model, ClientEntity.class);
    }

    public ClientModel mapOf(ClientEntity entity) {
        var mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<ClientEntity, ClientModel>() {
            @Override
            protected void configure() {
                using((Converter<Gender, String>) mappingContext ->
                        Gender.fromGender(mappingContext.getSource()).toString())
                        .map(this.source.getGender(), this.destination.getGender());
            }
        });
        return mapper.map(entity, ClientModel.class);
    }
}