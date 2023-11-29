package org.study.adapter.in.controller;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.study.adapter.in.model.ClientModel;
import org.study.adapter.in.model.PageModel;
import org.study.domain.core.PageCore;
import org.study.domain.entity.ClientEntity;
import org.study.domain.mapper.ClientMapper;
import org.study.port.in.ClientCoreIntegration;
import org.study.port.in.PageCoreIntegration;

import java.util.List;
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
        ClientEntity client = mapper.mapOf(clientModel);
        log.info("Saving client: {}", kv("client", client));
        ClientEntity savedClient = clientCore.save(client);
        log.info("Client was saved successfully: {}", savedClient);
        ClientModel clientModelResponse = mapper.mapOf(savedClient);
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(clientModelResponse.getId().toString());
        return Response.created(uriBuilder.build()).status(Response.Status.CREATED).entity(clientModelResponse).build();
    }

    @GET
    public Response findAll(
            @QueryParam("name") String name,
            @DefaultValue("0") @QueryParam("page") Integer pageNumber,
            @DefaultValue("30") @QueryParam("page_size") Integer pageSize
    ) {
        log.info("Searching for clients ...");
        PageModel pageable = PageModel.of(pageNumber, pageSize);
        PageCoreIntegration<ClientEntity> page = clientCore.findAll(name, pageable);
        List<ClientModel> listModel = page.getContent().stream()
                .map(mapper::mapOf).toList();
        if (!page.isEmpty()) {
            PageCoreIntegration<ClientModel> pageModel = new PageCore<>(listModel, pageable, page.getTotalElements());
            log.info("Client found: {}", kv("client", pageModel.getContent()));
            return Response.status(Response.Status.PARTIAL_CONTENT).entity(pageModel).build();
        }
        log.warn("No client found");
        return Response.noContent().build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") UUID id) {
        log.info("Searching for movie by id: {}", kv("id", id));
        ClientEntity client = clientCore.findById(id);
        log.info("Client found: {}", kv("client", client));
        ClientModel clientModelResponse = mapper.mapOf(client);
        return Response.status(Response.Status.OK).entity(clientModelResponse).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") UUID id, ClientModel clientModel) {
        log.info("Searching for movie by id: {}", kv("id", id));
        ClientEntity existingClient = clientCore.findById(id);
        clientModel.setId(existingClient.getId());
        ClientEntity client = mapper.mapOf(clientModel);
        log.info("Updating client: {}", kv("client", client));
        ClientEntity updatedClient = clientCore.save(client);
        ClientModel clientModelResponse = mapper.mapOf(updatedClient);
        return Response.status(Response.Status.OK).entity(clientModelResponse).build();
    }
}