package br.unitins.tp1.capinha.dto;

import br.unitins.tp1.capinha.model.Estoque;

public record EstoqueResponseDTO(
    Long id,
    String nomeProduto,
    Integer quantidade
) {
    public static EstoqueResponseDTO valueOf(Estoque estoque) {
        return new EstoqueResponseDTO(
            estoque.getId(),
            estoque.getNomeProduto(),
            estoque.getQuantidade()
        );
    }
}

