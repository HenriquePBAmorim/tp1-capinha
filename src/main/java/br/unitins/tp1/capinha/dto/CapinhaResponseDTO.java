package br.unitins.tp1.capinha.dto;

import br.unitins.tp1.capinha.model.Capinha;

public record CapinhaResponseDTO(Long id, String tipoProtecao, float preco, String descricao) {

    public static CapinhaResponseDTO valueOf(Capinha capinha) {
        return new CapinhaResponseDTO(
            capinha.getId(),
            capinha.getTipoProtecao(),
            capinha.getPreco(),
            capinha.getDescricao()
        );
    }
}
