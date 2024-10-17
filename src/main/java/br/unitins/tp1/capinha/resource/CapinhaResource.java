package br.unitins.tp1.capinha.resource;

import java.util.List;

import br.unitins.tp1.capinha.dto.CapinhaRequestDTO;
import br.unitins.tp1.capinha.dto.CapinhaResponseDTO;
import br.unitins.tp1.capinha.model.Capinha;
import br.unitins.tp1.capinha.service.CapinhaService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/capinhas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CapinhaResource {

    @Inject
    public CapinhaService capinhaService;

    @GET
    public Response findAll() {
        return Response.ok(
            capinhaService.findAll().stream()
                .map(CapinhaResponseDTO::valueOf)
                .toList()
        ).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(
            CapinhaResponseDTO.valueOf(capinhaService.findById(id))
        ).build();
    }

    @POST
    public Response create(@Valid CapinhaRequestDTO dto) {
        Capinha capinha = capinhaService.create(dto);
        return Response.status(Status.CREATED)
            .entity(CapinhaResponseDTO.valueOf(capinha))
            .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid CapinhaRequestDTO dto) {
        capinhaService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        capinhaService.delete(id);
        return Response.noContent().build();
    }
}
