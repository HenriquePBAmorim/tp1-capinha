package br.unitins.tp1.capinha.service;

import java.util.List;

import br.unitins.tp1.capinha.dto.CapinhaRequestDTO;
import br.unitins.tp1.capinha.model.Capinha;
import br.unitins.tp1.capinha.repository.CapinhaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CapinhaServiceImpl implements CapinhaService {

    @Inject
    CapinhaRepository capinhaRepository;

    @Override
    public Capinha findById(Long id) {
        return capinhaRepository.findById(id);
    }

    @Override
    public List<Capinha> findByTipoProtecao(String tipoProtecao) {
        return capinhaRepository.findByTipoProtecao(tipoProtecao);
    }

    @Override
    public List<Capinha> findAll() {
        return capinhaRepository.findAll().list();
    }

    @Override
    @Transactional
    public Capinha create(CapinhaRequestDTO dto) {
        Capinha capinha = new Capinha();
        capinha.setTipoProtecao(dto.tipoProtecao());
        capinha.setPreco(dto.preco());
        capinha.setDescricao(dto.descricao());

        capinhaRepository.persist(capinha);
        return capinha;
    }

    @Override
    @Transactional
    public Capinha update(Long id, CapinhaRequestDTO dto) {
        Capinha capinha = capinhaRepository.findById(id);
        capinha.setTipoProtecao(dto.tipoProtecao());
        capinha.setPreco(dto.preco());
        capinha.setDescricao(dto.descricao());
        return capinha;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        capinhaRepository.deleteById(id);
    }
}
