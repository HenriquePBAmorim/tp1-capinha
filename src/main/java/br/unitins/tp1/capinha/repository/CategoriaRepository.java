package br.unitins.tp1.capinha.repository;

import br.unitins.tp1.capinha.model.Categoria;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class CategoriaRepository implements PanacheRepository<Categoria> {

    public List<Categoria> findByNome(String nome) {
        return find("nome", nome).list();
    }

    public List<Categoria> findByDescricaoContaining(String descricao) {
        return find("descricao like ?1", "%" + descricao + "%").list();
    }
}
