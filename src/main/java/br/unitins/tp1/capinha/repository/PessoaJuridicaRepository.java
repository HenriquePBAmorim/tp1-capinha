package br.unitins.tp1.capinha.repository;

import java.util.List;

import br.unitins.tp1.capinha.model.PessoaJuridica;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PessoaJuridicaRepository implements PanacheRepository<PessoaJuridica> {

    public List<PessoaJuridica> findByNome(String nome) {
        return find("nome LIKE ?1", "%" + nome + "%").list();
    }

    public List<PessoaJuridica> findByCnpj(String cnpj) {
        return find("cnpj = ?1", cnpj).list();
    }
}
