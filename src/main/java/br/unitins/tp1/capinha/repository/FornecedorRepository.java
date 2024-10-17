package br.unitins.tp1.capinha.repository;

import java.util.List;

import br.unitins.tp1.capinha.model.Fornecedor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FornecedorRepository implements PanacheRepository<Fornecedor> {

    public List<Fornecedor> findByNome(String nome) {
        return find("nome LIKE ?1", "%" + nome + "%").list();
    }

    public List<Fornecedor> findByCnpj(String cnpj) {
        return find("cnpj = ?1", cnpj).list();
    }
}
