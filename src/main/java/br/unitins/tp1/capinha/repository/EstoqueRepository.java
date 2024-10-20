package br.unitins.tp1.capinha.repository;

import br.unitins.tp1.capinha.model.Estoque;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class EstoqueRepository implements PanacheRepository<Estoque> {

    public List<Estoque> findByNomeProduto(String nomeProduto) {
        return find("nomeProduto", nomeProduto).list();
    }

    public List<Estoque> findByQuantidadeGreaterThan(int quantidade) {
        return find("quantidade > ?1", quantidade).list();
    }
}
