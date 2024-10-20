package br.unitins.tp1.capinha.resource;

import br.unitins.tp1.capinha.dto.CategoriaRequestDTO;
import br.unitins.tp1.capinha.model.Categoria;
import br.unitins.tp1.capinha.service.CategoriaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/categorias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoriaResource {

    @Inject
    CategoriaService categoriaService;

    @GET
    public List<Categoria> listarTodas() {
        return categoriaService.findAll();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        Categoria categoria = categoriaService.findById(id);
        if (categoria != null) {
            return Response.ok(categoria).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response criar(CategoriaRequestDTO dto) {
        Categoria novaCategoria = categoriaService.create(dto);
        return Response.status(Response.Status.CREATED).entity(novaCategoria).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, CategoriaRequestDTO dto) {
        Categoria atualizada = categoriaService.update(id, dto);
        if (atualizada != null) {
            return Response.ok(atualizada).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    public Response excluir(@PathParam("id") Long id) {
        categoriaService.delete(id);
        return Response.noContent().build();
    }
}
