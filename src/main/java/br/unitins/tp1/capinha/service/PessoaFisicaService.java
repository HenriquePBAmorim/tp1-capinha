package br.unitins.tp1.capinha.service;

import java.util.List;
import br.unitins.tp1.capinha.dto.PessoaFisicaRequestDTO;
import br.unitins.tp1.capinha.model.PessoaFisica;

public interface PessoaFisicaService {

    PessoaFisica findById(Long id);

    List<PessoaFisica> findByNome(String nome);

    List<PessoaFisica> findAll();
    
    PessoaFisica findByCpf(String cpf);

    PessoaFisica create(PessoaFisicaRequestDTO dto);

    PessoaFisica update(Long id, PessoaFisicaRequestDTO dto);

    void delete(Long id);

    
}
