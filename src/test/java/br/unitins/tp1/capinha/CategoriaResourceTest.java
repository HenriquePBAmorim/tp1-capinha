package br.unitins.tp1.capinha;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.capinha.dto.CategoriaRequestDTO;
import br.unitins.tp1.capinha.model.Categoria;
import br.unitins.tp1.capinha.resource.CategoriaResource;
import br.unitins.tp1.capinha.service.CategoriaService;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
@TestHTTPEndpoint(CategoriaResource.class)
public class CategoriaResourceTest {

    @Inject
    CategoriaService categoriaService;

    @Test
    public void testFindAll() {
        given()
            .when().get()
            .then()
                .statusCode(200);
    }

    @Test
    public void testCreate() {
        CategoriaRequestDTO dto = new CategoriaRequestDTO();
        dto.setNome("Eletrônicos");
        dto.setDescricao("Capinhas para eletrônicos");

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when()
                .post()
            .then()
                .statusCode(201)
                .body("id", notNullValue(),
                      "nome", is("Eletrônicos"),
                      "descricao", is("Capinhas para eletrônicos"));

        // Limpeza dos dados
        Categoria categoria = categoriaService.findByNome("Eletrônicos").get(0);
        categoriaService.delete(categoria.getId());
    }

    @Test
    public void testUpdate() {
        // Inserindo dado para atualização
        CategoriaRequestDTO dto = new CategoriaRequestDTO();
        dto.setNome("Acessórios");
        dto.setDescricao("Capinhas e acessórios");

        Long id = categoriaService.create(dto).getId();

        CategoriaRequestDTO novoDto = new CategoriaRequestDTO();
        novoDto.setNome("Acessórios Premium");
        novoDto.setDescricao("Capinhas premium e acessórios");

        given()
            .contentType(ContentType.JSON)
            .body(novoDto)
            .when()
                .put("/" + id)
            .then()
                .statusCode(200);

        Categoria categoriaAtualizada = categoriaService.findById(id);
        assertEquals("Acessórios Premium", categoriaAtualizada.getNome());
        assertEquals("Capinhas premium e acessórios", categoriaAtualizada.getDescricao());

        // Limpeza dos dados
        categoriaService.delete(id);
    }

    @Test
    public void testDelete() {
        // Inserindo dado para deleção
        CategoriaRequestDTO dto = new CategoriaRequestDTO();
        dto.setNome("Simples");
        dto.setDescricao("Capinhas simples");

        Long id = categoriaService.create(dto).getId();

        given()
            .when()
                .delete("/" + id)
            .then()
                .statusCode(204);

        // Verificando se foi apagado
        Categoria categoria = categoriaService.findById(id);
        assertNull(categoria);
    }

    @Test
    public void testFindById() {
        // Inserindo dado
        CategoriaRequestDTO dto = new CategoriaRequestDTO();
        dto.setNome("Intermediário");
        dto.setDescricao("Capinhas intermediárias");

        Long id = categoriaService.create(dto).getId();

        given()
            .when()
                .get("/" + id)
            .then()
                .statusCode(200)
                .body("id", is(id.intValue()),
                      "nome", is("Intermediário"),
                      "descricao", is("Capinhas intermediárias"));

        // Limpeza dos dados
        categoriaService.delete(id);
    }
}