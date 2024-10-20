package br.unitins.tp1.capinha.model.converterjpa;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Material {

    SILICONE(1, "Silicone"),
    COURO(2, "Couro"),
    TPU(3, "TPU"),
    MADEIRA(4, "Madeira"),
    RECICLADO(5, "Reciclado"),
    BIODEGRADAVEL(6, "Biodegradável");

    private final Integer id;
    private final String label;

    Material(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static Material valueOf(Integer id) {
        if (id == null) {
            return null;
        }
        for (Material material : Material.values()) {
            if (material.getId().equals(id)) {
                return material;
            }
        }
        throw new IllegalArgumentException("Id inválido para Material: " + id);
    }
}
