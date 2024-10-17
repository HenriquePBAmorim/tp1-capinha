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
        // Busca todas as capinhas do banco e converte para CapinhaResponseDTO
        return capinhaRepository.listAll().stream()
                .map(CapinhaResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public CapinhaResponseDTO findById(Long id) {
        // Busca a capinha pelo ID e lança exceção se não for encontrada
        Capinha capinha = capinhaRepository.findByIdOptional(id)
                .orElseThrow(() -> new RuntimeException("Capinha não encontrada."));
        return new CapinhaResponseDTO(capinha);
    }

    @Override
    @Transactional
    public CapinhaResponseDTO create(CapinhaRequestDTO dto) {
        // Converte o DTO em uma entidade Capinha e salva no banco de dados
        Capinha capinha = new Capinha();
        capinha.setNome(dto.getNome());
        capinha.setModelo(dto.getModelo());
        capinha.setPreco(dto.getPreco());

        capinhaRepository.persist(capinha);
        return new CapinhaResponseDTO(capinha);
    }

    @Override
    @Transactional
    public CapinhaResponseDTO update(Long id, CapinhaRequestDTO dto) {
        // Busca a capinha pelo ID e atualiza com os dados do DTO
        Capinha capinha = capinhaRepository.findByIdOptional(id)
                .orElseThrow(() -> new RuntimeException("Capinha não encontrada."));
        
        capinha.setNome(dto.getNome());
        capinha.setModelo(dto.getModelo());
        capinha.setPreco(dto.getPreco());

        capinhaRepository.persist(capinha);
        return new CapinhaResponseDTO(capinha);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        // Remove a capinha pelo ID
        Capinha capinha = capinhaRepository.findByIdOptional(id)
                .orElseThrow(() -> new RuntimeException("Capinha não encontrada."));
        capinhaRepository.delete(capinha);
    }

    @Override
    public List<CapinhaResponseDTO> findByNome(String nome) {
        // Busca capinhas pelo nome (ou parte dele)
        return capinhaRepository.findByNome(nome).stream()
                .map(CapinhaResponseDTO::new)
                .collect(Collectors.toList());
    }
}
