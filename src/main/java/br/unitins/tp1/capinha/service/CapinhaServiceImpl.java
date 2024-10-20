package br.unitins.tp1.capinha.service;

import br.unitins.tp1.capinha.dto.CapinhaRequestDTO;
import br.unitins.tp1.capinha.dto.CapinhaResponseDTO;
import br.unitins.tp1.capinha.model.Capinha;
import br.unitins.tp1.capinha.repository.CapinhaRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CapinhaServiceImpl implements CapinhaService {

    @Inject
    CapinhaRepository capinhaRepository;

    @Override
    public List<CapinhaResponseDTO> findAll() {
        return capinhaRepository.listAll().stream()
                .map(CapinhaResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public CapinhaResponseDTO findById(Long id) {
        Capinha capinha = capinhaRepository.findByIdOptional(id)
                .orElseThrow(() -> new RuntimeException("Capinha não encontrada."));
        return new CapinhaResponseDTO(capinha);
    }

    @Override
    @Transactional
    public CapinhaResponseDTO create(CapinhaRequestDTO dto) {
        Capinha capinha = new Capinha();
        capinha.setTipoProtecao(dto.getTipoProtecao());
        capinha.setCompatibilidadeCarregamento(dto.isCompatibilidadeCarregamento());
        capinha.setPreco(dto.getPreco());
        capinha.setDescricao(dto.getDescricao());
        capinha.setMaterial(dto.getMaterial());

        capinhaRepository.persist(capinha);
        return new CapinhaResponseDTO(capinha);
    }

    @Override
    @Transactional
    public CapinhaResponseDTO update(Long id, CapinhaRequestDTO dto) {
        Capinha capinha = capinhaRepository.findByIdOptional(id)
                .orElseThrow(() -> new RuntimeException("Capinha não encontrada."));
        
        capinha.setTipoProtecao(dto.getTipoProtecao());
        capinha.setCompatibilidadeCarregamento(dto.isCompatibilidadeCarregamento());
        capinha.setPreco(dto.getPreco());
        capinha.setDescricao(dto.getDescricao());
        capinha.setMaterial(dto.getMaterial());

        return new CapinhaResponseDTO(capinha);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Capinha capinha = capinhaRepository.findByIdOptional(id)
                .orElseThrow(() -> new RuntimeException("Capinha não encontrada."));
        capinhaRepository.delete(capinha);
    }
}
