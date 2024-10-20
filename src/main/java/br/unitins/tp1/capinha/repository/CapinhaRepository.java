package br.unitins.tp1.capinha.repository;

import br.unitins.tp1.capinha.model.Capinha;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class CapinhaRepository implements PanacheRepository<Capinha> {

    public List<Capinha> findByMaterial(String material) {
        return find("material", material).list();
    }

    public List<Capinha> findByPrecoMenorQue(Double preco) {
        return find("preco < ?1", preco).list();
    }
}
