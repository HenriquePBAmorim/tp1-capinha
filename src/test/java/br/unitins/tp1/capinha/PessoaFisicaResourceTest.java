package br.unitins.tp1.capinha;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.capinha.dto.PessoaFisicaRequestDTO;
import br.unitins.tp1.capinha.model.PessoaFisica;
import br.unitins.tp1.capinha.model.Sexo;
import br.unitins.tp1.capinha.resource.PessoaFisicaResource;
import br.unitins.tp1.capinha.service.PessoaFisicaService;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
@TestHTTPEndpoint(PessoaFisicaResource.class)
public class PessoaFisicaResourceTest {

    @Inject
    public PessoaFisicaService pfService;

    @Test
    public void testFindAll() {
        given()
                .when().get()
                .then().statusCode(200);
    }

    @Test
    public void testCreate() {
        PessoaFisicaRequestDTO dto = new PessoaFisicaRequestDTO("João Silva", "111.111.111-11", 2);

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post()
                .then()
                .statusCode(201)
                .body("id", notNullValue(),
                        "nome", is("João Silva"),
                        "cpf", is("111.111.111-11"));

        // removendo o dado que foi inserido
        pfService.delete(pfService.findByCpf("111.111.111-11").getId());
    }

    @Test
    public void testUpdate() {
        // inserindo dado para alteração
        PessoaFisicaRequestDTO dto = new PessoaFisicaRequestDTO("Maria Souza", "222.222.222-22", 2);

        long id = pfService.create(dto).getId();

        PessoaFisicaRequestDTO novoDto = new PessoaFisicaRequestDTO("Marcos Souza", "222.222.222-22", 2);

        given()
                .contentType(ContentType.JSON)
                .body(novoDto)
                .when()
                .put("/" + id)
                .then()
                .statusCode(204);

        PessoaFisica pf = pfService.findById(id);

        assertEquals("Marcos Souza", pf.getNome());
        assertEquals(Sexo.MASCULINO, pf.getSexo());

        // removendo o dado que foi inserido
        pfService.delete(pfService.findByCpf("222.222.222-22").getId());
    }

    @Test
    public void testDelete() {
        // inserindo dado para exclusão
        PessoaFisicaRequestDTO dto = new PessoaFisicaRequestDTO("Evellyn Mayara", "333.333.333-33", 1);

        long id = pfService.create(dto).getId();

        given()
                .when()
                .delete("/" + id)
                .then()
                .statusCode(204);

        // verificando se foi apagado no banco de dados
        PessoaFisica pf = pfService.findById(id);
        assertNull(pf);
    }

    @Test
    @TestHTTPEndpoint(PessoaFisicaResource.class)
    public void testFindAll2() {
        given()
                .when().get()
                .then()
                .statusCode(200) 
                .body("$", notNullValue()); 
    }
}
