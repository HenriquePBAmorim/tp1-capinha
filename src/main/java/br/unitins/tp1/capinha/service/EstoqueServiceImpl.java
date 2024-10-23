package br.unitins.tp1.capinha.service;

import java.util.List;

import br.unitins.tp1.capinha.dto.EstoqueRequestDTO;
import br.unitins.tp1.capinha.model.Estoque;
import br.unitins.tp1.capinha.repository.EstoqueRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class EstoqueServiceImpl implements EstoqueService {

    @Inject
    EstoqueRepository estoqueRepository;

    @Override
    public Estoque findById(Long id) {
        return estoqueRepository.findById(id);
    }

    @Override
    public List<Estoque> findAll() {
        return estoqueRepository.findAll().list();
    }

    @Override
    @Transactional
    public Estoque create(EstoqueRequestDTO dto) {
        Estoque estoque = new Estoque();
        estoque.setNomeProduto(dto.nomeProduto());
        estoque.setQuantidade(dto.quantidade());

        estoqueRepository.persist(estoque);
        return estoque;
    }

    @Override
    @Transactional
    public Estoque update(Long id, EstoqueRequestDTO dto) {
        Estoque estoque = estoqueRepository.findById(id);

        estoque.setNomeProduto(dto.nomeProduto());
        estoque.setQuantidade(dto.quantidade());

        return estoque;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        estoqueRepository.deleteById(id);
    }

    // Implementação do método findByNomeProduto
    @Override
    public List<Estoque> findByNomeProduto(String nomeProduto) {
        return estoqueRepository.findByNomeProduto(nomeProduto);
    }
}
