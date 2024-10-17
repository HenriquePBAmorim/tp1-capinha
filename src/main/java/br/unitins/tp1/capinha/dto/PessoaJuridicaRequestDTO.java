package br.unitins.tp1.capinha.dto;

import jakarta.validation.constraints.NotBlank;

public record PessoaJuridicaRequestDTO(
    @NotBlank(message = "O campo nome não pode ser nulo.")
    String nome,
    @NotBlank(message = "O campo CNPJ não pode ser nulo.")
    String cnpj,
    String nomeFantasia
) {}

