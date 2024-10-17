package br.unitins.tp1.capinha.dto;

public class CapinhaResponseDTO {
    private Long id;
    private String nome;
    private String descricao;

    // Construtor
    public CapinhaResponseDTO(Long id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
