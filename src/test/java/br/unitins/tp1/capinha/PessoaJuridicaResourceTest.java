package br.unitins.tp1.capinha;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.capinha.dto.PessoaJuridicaRequestDTO;
import br.unitins.tp1.capinha.model.PessoaJuridica;
import br.unitins.tp1.capinha.resource.PessoaJuridicaResource;
import br.unitins.tp1.capinha.service.PessoaJuridicaService;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
@TestHTTPEndpoint(PessoaJuridicaResource.class)
public class PessoaJuridicaResourceTest {

    @Inject
    PessoaJuridicaService pessoaJuridicaService;

    @Test
    public void testFindAll() {
        given()
            .when().get()
            .then()
                .statusCode(200);
    }

    @Test
    public void testCreate() {
        PessoaJuridicaRequestDTO dto = new PessoaJuridicaRequestDTO("Empresa Teste", "12.345.678/0001-99", "Nome Fantasia Teste");

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when()
                .post()
            .then()
                .statusCode(201)
                .body("id", notNullValue(),
                      "nome", is("Empresa Teste"),
                      "cnpj", is("12.345.678/0001-99"), // Verifica o CNPJ formatado
                      "nomeFantasia", is("Nome Fantasia Teste"));

        // Limpeza dos dados
        PessoaJuridica pessoaJuridica = pessoaJuridicaService.findByNome("Empresa Teste").get(0);
        pessoaJuridicaService.delete(pessoaJuridica.getId());
    }

    @Test
    public void testUpdate() {
        // Inserindo dado para atualização
        PessoaJuridicaRequestDTO dto = new PessoaJuridicaRequestDTO("Empresa Teste", "12.345.678/0001-99", "Nome Fantasia Teste");
        Long id = pessoaJuridicaService.create(dto).getId();
    
        PessoaJuridicaRequestDTO novoDto = new PessoaJuridicaRequestDTO("Empresa Atualizada", "98.765.432/0001-88", "Nome Fantasia Atualizada");
    
        given()
            .contentType(ContentType.JSON)
            .body(novoDto)
            .when()
                .put("/" + id)
            .then()
                .statusCode(204);
    
        PessoaJuridica pessoaJuridicaAtualizada = pessoaJuridicaService.findById(id);
        assertEquals("Empresa Atualizada", pessoaJuridicaAtualizada.getNome());
        assertEquals("98765432000188", pessoaJuridicaAtualizada.getCnpj()); // Verifique o CNPJ formatado
        assertEquals("Nome Fantasia Atualizada", pessoaJuridicaAtualizada.getNomeFantasia());
    
        // Limpeza dos dados
        pessoaJuridicaService.delete(id);
    }

    @Test
    public void testDelete() {
        // Inserindo dado para deleção
        PessoaJuridicaRequestDTO dto = new PessoaJuridicaRequestDTO("Empresa Teste", "12.345.678/0001-99", "Nome Fantasia Teste");
        Long id = pessoaJuridicaService.create(dto).getId();

        given()
            .when()
                .delete("/" + id)
            .then()
                .statusCode(204);

        // Verificando se foi apagado
        PessoaJuridica pessoaJuridica = pessoaJuridicaService.findById(id);
        assertNull(pessoaJuridica);
    }

    @Test
    public void testFindById() {
        // Inserindo dado
        PessoaJuridicaRequestDTO dto = new PessoaJuridicaRequestDTO("Empresa Teste", "12.345.678/0001-99", "Nome Fantasia Teste");
        Long id = pessoaJuridicaService.create(dto).getId();

        given()
            .when()
                .get("/" + id)
            .then()
                .statusCode(200)
                .body("id", is(id.intValue()),
                      "nome", is("Empresa Teste"),
                      "cnpj", is("12.345.678/0001-99"), // Verifica o CNPJ formatado
                      "nomeFantasia", is("Nome Fantasia Teste"));

        // Limpeza dos dados
        pessoaJuridicaService.delete(id);
    }

    @Test
    public void testFindByNome() {
        // Inserindo dado para busca
        PessoaJuridicaRequestDTO dto = new PessoaJuridicaRequestDTO("Empresa Teste", "12.345.678/0001-99", "Nome Fantasia Teste");
        Long id = pessoaJuridicaService.create(dto).getId();

        given()
            .when()
                .get("/search/Empresa Teste")
            .then()
                .statusCode(200)
                .body("[0].id", is(id.intValue()),
                      "[0].nome", is("Empresa Teste"),
                      "[0].cnpj", is("12.345.678/0001-99"), // Verifica o CNPJ formatado
                      "[0].nomeFantasia", is("Nome Fantasia Teste"));

        // Limpeza dos dados
        pessoaJuridicaService.delete(id);
    }
}
