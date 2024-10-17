package br.unitins.tp1.capinha.dto;

import br.unitins.tp1.capinha.model.Fornecedor;

public record FornecedorResponseDTO(Long id, String nome, String cnpj, String telefone, String email) {

    public static FornecedorResponseDTO valueOf(Fornecedor fornecedor) {
        return new FornecedorResponseDTO(
            fornecedor.getId(),
            fornecedor.getNome(),
            fornecedor.getCnpj(),
            fornecedor.getTelefone(),
            fornecedor.getEmail()
        );
    }
}
