package br.unitins.tp1.capinha.repository;

import java.util.List;
import br.unitins.tp1.capinha.model.PessoaFisica;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PessoaFisicaRepository implements PanacheRepository<PessoaFisica> {

    public List<PessoaFisica> findByNome(String nome) {
        return find("nome like ?1", "%" + nome + "%").list();
    }

    public PessoaFisica findByCpf(String cpf) {
        return find("cpf", cpf).firstResult();
    }
}
