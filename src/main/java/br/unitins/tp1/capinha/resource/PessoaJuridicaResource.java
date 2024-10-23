package br.unitins.tp1.capinha.resource;

import br.unitins.tp1.capinha.dto.PessoaJuridicaRequestDTO;
import br.unitins.tp1.capinha.dto.PessoaJuridicaResponseDTO;
import br.unitins.tp1.capinha.service.PessoaJuridicaService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/pessoasjuridicas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PessoaJuridicaResource {

    @Inject
    PessoaJuridicaService pessoaJuridicaService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(PessoaJuridicaResponseDTO.valueOf(pessoaJuridicaService.findById(id))).build();
    }

    @GET
    @Path("/search/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        return Response.ok(pessoaJuridicaService.findByNome(nome).
                stream().
                map(PessoaJuridicaResponseDTO::valueOf).
                toList()).build();
    }

    @GET
    public Response findAll() {
        return Response.ok(pessoaJuridicaService.findAll().
                stream().
                map(PessoaJuridicaResponseDTO::valueOf).
                toList()).build();
    }

    @POST
    public Response create(@Valid PessoaJuridicaRequestDTO dto) {
        return Response.status(Status.CREATED).entity(
            PessoaJuridicaResponseDTO.valueOf(pessoaJuridicaService.create(dto))
        ).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid PessoaJuridicaRequestDTO dto) {
        pessoaJuridicaService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        pessoaJuridicaService.delete(id);
        return Response.noContent().build();
    }
}
