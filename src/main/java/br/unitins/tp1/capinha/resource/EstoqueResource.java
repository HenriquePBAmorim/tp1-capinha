package br.unitins.tp1.capinha.resource;

import br.unitins.tp1.capinha.dto.EstoqueRequestDTO;
import br.unitins.tp1.capinha.dto.EstoqueResponseDTO;
import br.unitins.tp1.capinha.service.EstoqueService;
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

@Path("/estoques")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EstoqueResource {

    @Inject
    EstoqueService estoqueService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(EstoqueResponseDTO.valueOf(estoqueService.findById(id))).build();
    }

    @GET
    public Response findAll() {
        return Response.ok(estoqueService.findAll().stream()
                        .map(EstoqueResponseDTO::valueOf).toList()).build();
    }

    @POST
    public Response create(@Valid EstoqueRequestDTO dto) {
        return Response.status(Status.CREATED).entity(
            EstoqueResponseDTO.valueOf(estoqueService.create(dto))
        ).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid EstoqueRequestDTO dto) {
        estoqueService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        estoqueService.delete(id);
        return Response.noContent().build();
    }
}
