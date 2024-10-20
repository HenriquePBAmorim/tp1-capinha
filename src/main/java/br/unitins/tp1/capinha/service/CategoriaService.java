package br.unitins.tp1.capinha.service;

import br.unitins.tp1.capinha.dto.CategoriaRequestDTO;
import br.unitins.tp1.capinha.model.Categoria;
import java.util.List;

public interface CategoriaService {
    Categoria findById(Long id);
    List<Categoria> findByNome(String nome);
    List<Categoria> findAll();
    Categoria create(CategoriaRequestDTO dto);
    Categoria update(Long id, CategoriaRequestDTO dto);
    void delete(Long id);
}
