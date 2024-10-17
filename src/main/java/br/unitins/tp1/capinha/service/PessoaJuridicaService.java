package br.unitins.tp1.capinha.service;

import java.util.List;

import br.unitins.tp1.capinha.dto.PessoaJuridicaRequestDTO;
import br.unitins.tp1.capinha.model.PessoaJuridica;

public interface PessoaJuridicaService {

    PessoaJuridica findById(Long id);

    List<PessoaJuridica> findByNome(String nome);

    List<PessoaJuridica> findAll();

    PessoaJuridica create(PessoaJuridicaRequestDTO dto);

    PessoaJuridica update(Long id, PessoaJuridicaRequestDTO dto);

    void delete(Long id);
}