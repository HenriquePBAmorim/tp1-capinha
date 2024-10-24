package br.unitins.tp1.capinha.dto;

import br.unitins.tp1.capinha.model.Fornecedor;

public record FornecedorResponseDTO(Long id, String nome, String cnpj, String telefone, String email) {

    public static FornecedorResponseDTO valueOf(Fornecedor fornecedor) {
        return new FornecedorResponseDTO(
            fornecedor.getId(),
            fornecedor.getNome(),
            formatCnpj(fornecedor.getCnpj()), 
            fornecedor.getTelefone(),
            fornecedor.getEmail()
        );
    }

    private static String formatCnpj(String cnpj) {
        if (cnpj != null && cnpj.length() == 14) {
            return cnpj.replaceFirst("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
        }
        return cnpj;
    }
}
