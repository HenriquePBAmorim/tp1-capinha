package br.unitins.tp1.capinha.dto;

import br.unitins.tp1.capinha.model.Material;

public class CapinhaRequestDTO {
    private String tipoProtecao;
    private boolean compatibilidadeCarregamento;
    private Double preco;
    private String descricao;
    private Material material;

    // Getters e setters
    public String getTipoProtecao() {
        return tipoProtecao;
    }

    public void setTipoProtecao(String tipoProtecao) {
        this.tipoProtecao = tipoProtecao;
    }

    public boolean isCompatibilidadeCarregamento() {
        return compatibilidadeCarregamento;
    }

    public void setCompatibilidadeCarregamento(boolean compatibilidadeCarregamento) {
        this.compatibilidadeCarregamento = compatibilidadeCarregamento;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
