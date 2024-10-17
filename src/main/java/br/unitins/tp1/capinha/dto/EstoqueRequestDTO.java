package br.unitins.tp1.capinha.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EstoqueRequestDTO(
    @NotBlank(message = "O nome do produto deve ser informado.")
    String nomeProduto,
    
    @NotNull(message = "A quantidade deve ser informada.")
    @Min(value = 0, message = "A quantidade não pode ser negativa.")
    Integer quantidade
) {}
