package br.unitins.tp1.capinha.service;

import java.util.List;

import br.unitins.tp1.capinha.dto.EstoqueRequestDTO;
import br.unitins.tp1.capinha.model.Estoque;

public interface EstoqueService {

    Estoque findById(Long id);

    List<Estoque> findAll();

    Estoque create(EstoqueRequestDTO dto);

    Estoque update(Long id, EstoqueRequestDTO dto);

    void delete(Long id);
}