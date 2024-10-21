package br.unitins.tp1.capinha.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Entity
public class Capinha extends DefaultEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoProtecao;
    private boolean compatibilidadeCarregamento;
    private Double preco;
    private String descricao;

    @Enumerated(EnumType.STRING)
    private Material material;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
