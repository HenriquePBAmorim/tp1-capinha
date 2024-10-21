package br.unitins.tp1.capinha.dto;

import br.unitins.tp1.capinha.model.Capinha;
import br.unitins.tp1.capinha.model.Material;

public class CapinhaResponseDTO {
    private Long id;
    private String tipoProtecao;
    private boolean compatibilidadeCarregamento;
    private Double preco;
    private String descricao;
    private Material material;

    public CapinhaResponseDTO(Capinha capinha) {
        this.id = capinha.getId();
        this.tipoProtecao = capinha.getTipoProtecao();
        this.compatibilidadeCarregamento = capinha.isCompatibilidadeCarregamento();
        this.preco = capinha.getPreco();
        this.descricao = capinha.getDescricao();
        this.material = capinha.getMaterial();
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getTipoProtecao() {
        return tipoProtecao;
    }

    public boolean isCompatibilidadeCarregamento() {
        return compatibilidadeCarregamento;
    }

    public Double getPreco() {
        return preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public Material getMaterial() {
        return material;
    }
}
