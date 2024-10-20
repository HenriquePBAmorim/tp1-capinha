package br.unitins.tp1.capinha.dto;

import br.unitins.tp1.capinha.model.Categoria;

public class CategoriaResponseDTO {
    private Long id;
    private String nome;
    private String descricao;

    public CategoriaResponseDTO(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
        this.descricao = categoria.getDescricao();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
