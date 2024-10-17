package br.unitins.tp1.capinha.service;

import java.util.List;

import br.unitins.tp1.capinha.dto.CapinhaRequestDTO;
import br.unitins.tp1.capinha.model.Capinha;

public interface CapinhaService {

    Capinha findById(Long id);

    List<Capinha> findAll();

    Capinha create(CapinhaRequestDTO dto);

    Capinha update(Long id, CapinhaRequestDTO dto);

    void delete(Long id);
}
