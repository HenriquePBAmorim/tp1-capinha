package br.unitins.tp1.capinha.resource;

import br.unitins.tp1.capinha.dto.CapinhaRequestDTO;
import br.unitins.tp1.capinha.dto.CapinhaResponseDTO;
import br.unitins.tp1.capinha.service.CapinhaService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/capinhas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CapinhaResource {

    @Inject
    CapinhaService capinhaService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(CapinhaResponseDTO.valueOf(capinhaService.findById(id))).build();
    }

    @GET
    @Path("/search/{tipoProtecao}")
    public Response findByTipoProtecao(@PathParam("tipoProtecao") String tipoProtecao) {
        return Response.ok(capinhaService.findByTipoProtecao(tipoProtecao)
                    .stream()
                    .map(CapinhaResponseDTO::valueOf)
                    .toList()).build();
    }

    @GET
    public Response findAll() {
        return Response.ok(capinhaService.findAll()
                    .stream()
                    .map(CapinhaResponseDTO::valueOf)
                    .toList()).build();
    }

    @POST
    public Response create(@Valid CapinhaRequestDTO dto) {
        return Response.status(Status.CREATED).entity(
            CapinhaResponseDTO.valueOf(capinhaService.create(dto))
        ).build();
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
