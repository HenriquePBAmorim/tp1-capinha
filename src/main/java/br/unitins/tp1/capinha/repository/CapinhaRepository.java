package br.unitins.tp1.capinha.repository;

import br.unitins.tp1.capinha.model.Capinha;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CapinhaRepository implements PanacheRepository<Capinha> {
}
