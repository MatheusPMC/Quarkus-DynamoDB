package org.study.adapter.in.controller;

import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.study.adapter.in.model.ClientModel;
import org.study.domain.entity.ClientEntity;
import org.study.domain.mapper.ClientMapper;
import org.study.port.in.ClientCoreIntegration;

import java.util.UUID;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Path("/clients/v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Slf4j
@RequiredArgsConstructor
public class ClientController {
    private final ClientCoreIntegration clientCore;
    private final ClientMapper mapper;

    @POST
    public Response create(@Valid ClientModel clientModel, @Context UriInfo uriInfo) {
        clientModel.setId(UUID.randomUUID());
        log.info("Creating new client: {}", kv("client", clientModel));
        log.info("Mapping client: {}", kv("client", clientModel));
        var client = this.mapper.mapOf(clientModel);
        log.info("Saving client: {}", kv("client", client));
        ClientEntity savedClient = this.clientCore.save(client);
        log.info("Client was saved successfully: {}", savedClient);
        ClientModel clientEntity = this.mapper.mapOf(savedClient);
        var uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(clientEntity.getId().toString());
        return Response.created(uriBuilder.build()).entity(clientEntity).build();
    }
}