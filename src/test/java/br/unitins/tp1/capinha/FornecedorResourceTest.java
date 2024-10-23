package br.unitins.tp1.capinha;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.capinha.dto.FornecedorRequestDTO;
import br.unitins.tp1.capinha.model.Fornecedor;
import br.unitins.tp1.capinha.resource.FornecedorResource;
import br.unitins.tp1.capinha.service.FornecedorService;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
@TestHTTPEndpoint(FornecedorResource.class)
public class FornecedorResourceTest {

    @Inject
    FornecedorService fornecedorService;

    @Test
    public void testFindAll() {
        given()
            .when().get()
            .then()
                .statusCode(200);
    }

    @Test
    public void testCreate() {
        FornecedorRequestDTO dto = new FornecedorRequestDTO("Fornecedor Teste", "12.345.678/0001-99", "99999-9999", "email@teste.com");

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when()
                .post()
            .then()
                .statusCode(201)
                .body("id", notNullValue(),
                      "nome", is("Fornecedor Teste"),
                      "cnpj", is("12.345.678/0001-99"),
                      "telefone", is("99999-9999"),
                      "email", is("email@teste.com"));

        // Limpeza dos dados
        Fornecedor fornecedor = fornecedorService.findByNome("Fornecedor Teste").get(0);
        fornecedorService.delete(fornecedor.getId());
    }

    @Test
    public void testUpdate() {
        // Inserindo dado para atualização
        FornecedorRequestDTO dto = new FornecedorRequestDTO("Fornecedor Teste", "12.345.678/0001-99", "99999-9999", "email@teste.com");
        Long id = fornecedorService.create(dto).getId();

        FornecedorRequestDTO novoDto = new FornecedorRequestDTO("Fornecedor Atualizado", "98.765.432/0001-88", "88888-8888", "novoemail@teste.com");

        given()
            .contentType(ContentType.JSON)
            .body(novoDto)
            .when()
                .put("/" + id)
            .then()
                .statusCode(204);

        Fornecedor fornecedorAtualizado = fornecedorService.findById(id);
        assertEquals("Fornecedor Atualizado", fornecedorAtualizado.getNome());
        assertEquals("98765432000188", fornecedorAtualizado.getCnpj());
        assertEquals("88888-8888", fornecedorAtualizado.getTelefone());
        assertEquals("novoemail@teste.com", fornecedorAtualizado.getEmail());

        // Limpeza dos dados
        fornecedorService.delete(id);
    }

    @Test
    public void testDelete() {
        // Inserindo dado para deleção
        FornecedorRequestDTO dto = new FornecedorRequestDTO("Fornecedor Teste", "12.345.678/0001-99", "99999-9999", "email@teste.com");
        Long id = fornecedorService.create(dto).getId();

        given()
            .when()
                .delete("/" + id)
            .then()
                .statusCode(204);

        // Verificando se foi apagado
        Fornecedor fornecedor = fornecedorService.findById(id);
        assertNull(fornecedor);
    }

    @Test
    public void testFindById() {
        // Inserindo dado
        FornecedorRequestDTO dto = new FornecedorRequestDTO("Fornecedor Teste", "12.345.678/0001-99", "99999-9999", "email@teste.com");
        Long id = fornecedorService.create(dto).getId();

        given()
            .when()
                .get("/" + id)
            .then()
                .statusCode(200)
                .body("id", is(id.intValue()),
                      "nome", is("Fornecedor Teste"),
                      "cnpj", is("12.345.678/0001-99"),
                      "telefone", is("99999-9999"),
                      "email", is("email@teste.com"));

        // Limpeza dos dados
        fornecedorService.delete(id);
    }

    @Test
    public void testFindByNome() {
        // Inserindo dado para busca
        FornecedorRequestDTO dto = new FornecedorRequestDTO("Fornecedor Teste", "12.345.678/0001-99", "99999-9999", "email@teste.com");
        Long id = fornecedorService.create(dto).getId();

        given()
            .when()
                .get("/search/Fornecedor Teste")
            .then()
                .statusCode(200)
                .body("[0].id", is(id.intValue()),
                      "[0].nome", is("Fornecedor Teste"),
                      "[0].cnpj", is("12.345.678/0001-99"),
                      "[0].telefone", is("99999-9999"),
                      "[0].email", is("email@teste.com"));

        // Limpeza dos dados
        fornecedorService.delete(id);
    }
}
