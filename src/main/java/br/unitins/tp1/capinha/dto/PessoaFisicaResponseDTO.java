package br.unitins.tp1.capinha.dto;

import br.unitins.tp1.capinha.model.PessoaFisica;
import br.unitins.tp1.capinha.model.Sexo;

public record PessoaFisicaResponseDTO(Long id, String nome, String cpf, Sexo sexo) {

    public static PessoaFisicaResponseDTO valueOf(PessoaFisica pessoafisica) {
        return new PessoaFisicaResponseDTO (
            pessoafisica.getId(), 
            pessoafisica.getNome(), 
            pessoafisica.getCpf(),
            pessoafisica.getSexo());
    }
    
}
