package br.unitins.tp1.capinha.dto;

import br.unitins.tp1.capinha.model.PessoaJuridica;

public record PessoaJuridicaResponseDTO(Long id, String nome, String cnpj, String nomeFantasia) {

    public static PessoaJuridicaResponseDTO valueOf(PessoaJuridica pessoaJuridica) {
        return new PessoaJuridicaResponseDTO(
            pessoaJuridica.getId(),
            pessoaJuridica.getNome(),
            formatCnpj(pessoaJuridica.getCnpj()),
            pessoaJuridica.getNomeFantasia()
        );
    }

    private static String formatCnpj(String cnpj) {
        if (cnpj != null && cnpj.length() == 14) {
            return cnpj.replaceFirst("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
        }
        return cnpj; 
    }
}
