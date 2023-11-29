package org.study.domain.exceptions;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;
import org.study.adapter.in.model.ErrorModel;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Provider
@Priority(Priorities.AUTHENTICATION)
@Slf4j
public class ExceptionHandlerController implements ExceptionMapper<Exception> {
    @Context
    private ResourceInfo resourceInfo;
    @Context
    private UriInfo uriInfo;

    @Override
    public Response toResponse(Exception ex) {
        var absolutePath = uriInfo.getAbsolutePath();
        var resourceMethod = resourceInfo.getResourceMethod();
        log.error("Error processing request: {}, {}, {}", kv("url", absolutePath), kv("method", resourceMethod.getName()), kv("exception", ex));
        ex.printStackTrace();
        var error = new ErrorModel();
        error.setMessage(ex.getMessage());
        if (ex instanceof ClientNotFoundException) {
            error.setCode(404);
            log.warn("Error processing request: {}", kv("error", error));
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        error.setCode(500);
        log.error("Error processing request: {}", kv("error", error));
        return Response.serverError().entity(error).build();
    }
}