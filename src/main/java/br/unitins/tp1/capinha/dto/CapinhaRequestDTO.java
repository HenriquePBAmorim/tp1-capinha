package br.unitins.tp1.capinha.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CapinhaRequestDTO(
    @NotBlank(message = "O tipo de proteção deve ser informado.")
    String tipoProtecao,
    
    @NotNull(message = "O preço deve ser informado.")
    float preco,
    
    String descricao
) {}
