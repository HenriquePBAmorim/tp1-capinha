package br.unitins.tp1.capinha.dto;

import br.unitins.tp1.capinha.model.PessoaJuridica;

public record PessoaJuridicaResponseDTO(Long id, String nome, String cnpj, String nomeFantasia) {

    public static PessoaJuridicaResponseDTO valueOf(PessoaJuridica pessoaJuridica) {
        return new PessoaJuridicaResponseDTO(
            pessoaJuridica.getId(),
            pessoaJuridica.getNome(),
            pessoaJuridica.getCnpj(),
            pessoaJuridica.getNomeFantasia()
        );
    }
}
