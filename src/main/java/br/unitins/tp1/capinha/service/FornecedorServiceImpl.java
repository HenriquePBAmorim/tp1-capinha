package br.unitins.tp1.capinha.service;

import java.util.List;

import br.unitins.tp1.capinha.dto.FornecedorRequestDTO;
import br.unitins.tp1.capinha.model.Fornecedor;
import br.unitins.tp1.capinha.repository.FornecedorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class FornecedorServiceImpl implements FornecedorService {

    @Inject
    FornecedorRepository fornecedorRepository;

    @Override
    public Fornecedor findById(Long id) {
        return fornecedorRepository.findById(id);
    }

    @Override
    public List<Fornecedor> findByNome(String nome) {
        return fornecedorRepository.findByNome(nome);
    }

    @Override
    public List<Fornecedor> findAll() {
        return fornecedorRepository.findAll().list();
    }

    @Override
    @Transactional
    public Fornecedor create(FornecedorRequestDTO dto) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(dto.nome());
        fornecedor.setCnpj(dto.cnpj().replaceAll("[^\\d]", "")); // Remove formatação
        fornecedor.setTelefone(dto.telefone());
        fornecedor.setEmail(dto.email());

        fornecedorRepository.persist(fornecedor);
        return fornecedor;
    }

    @Override
    @Transactional
    public Fornecedor update(Long id, FornecedorRequestDTO dto) {
        Fornecedor fornecedor = fornecedorRepository.findById(id);
        fornecedor.setNome(dto.nome());
        fornecedor.setCnpj(dto.cnpj().replaceAll("[^\\d]", "")); // Remove formatação
        fornecedor.setTelefone(dto.telefone());
        fornecedor.setEmail(dto.email());

        return fornecedor;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        fornecedorRepository.deleteById(id);
    }
}
