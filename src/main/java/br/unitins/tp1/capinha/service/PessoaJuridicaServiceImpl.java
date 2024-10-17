package br.unitins.tp1.capinha.service;

import java.util.List;

import br.unitins.tp1.capinha.dto.PessoaJuridicaRequestDTO;
import br.unitins.tp1.capinha.model.PessoaJuridica;
import br.unitins.tp1.capinha.repository.PessoaJuridicaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PessoaJuridicaServiceImpl implements PessoaJuridicaService {

    @Inject
    PessoaJuridicaRepository pessoaJuridicaRepository;

    @Override
    public PessoaJuridica findById(Long id) {
        return pessoaJuridicaRepository.findById(id);
    }

    @Override
    public List<PessoaJuridica> findByNome(String nome) {
        return pessoaJuridicaRepository.findByNome(nome);
    }

    @Override
    public List<PessoaJuridica> findAll() {
        return pessoaJuridicaRepository.findAll().list();
    }

    @Override
    @Transactional
    public PessoaJuridica create(PessoaJuridicaRequestDTO dto) {
        PessoaJuridica pessoaJuridica = new PessoaJuridica();
        pessoaJuridica.setNome(dto.nome());
        pessoaJuridica.setCnpj(dto.cnpj());
        pessoaJuridica.setNomeFantasia(dto.nomeFantasia());

        pessoaJuridicaRepository.persist(pessoaJuridica);
        return pessoaJuridica;
    }

    @Override
    @Transactional
    public PessoaJuridica update(Long id, PessoaJuridicaRequestDTO dto) {
        PessoaJuridica pessoaJuridica = pessoaJuridicaRepository.findById(id);
        pessoaJuridica.setNome(dto.nome());
        pessoaJuridica.setCnpj(dto.cnpj());
        pessoaJuridica.setNomeFantasia(dto.nomeFantasia());

        return pessoaJuridica;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        pessoaJuridicaRepository.deleteById(id);
    }
}