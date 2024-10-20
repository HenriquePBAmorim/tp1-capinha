package br.unitins.tp1.capinha.service;

import br.unitins.tp1.capinha.dto.CategoriaRequestDTO;
import br.unitins.tp1.capinha.dto.CategoriaResponseDTO;
import br.unitins.tp1.capinha.model.Categoria;
import br.unitins.tp1.capinha.repository.CategoriaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CategoriaServiceImpl implements CategoriaService {

    @Inject
    CategoriaRepository categoriaRepository;

    @Override
    public List<CategoriaResponseDTO> findAll() {
        return categoriaRepository.listAll().stream()
                .map(CategoriaResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public CategoriaResponseDTO findById(Long id) {
        Categoria categoria = categoriaRepository.findByIdOptional(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada."));
        return new CategoriaResponseDTO(categoria);
    }

    @Override
    @Transactional
    public CategoriaResponseDTO create(CategoriaRequestDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setNome(dto.getNome());
        categoria.setDescricao(dto.getDescricao());

        categoriaRepository.persist(categoria);
        return new CategoriaResponseDTO(categoria);
    }

    @Override
    @Transactional
    public CategoriaResponseDTO update(Long id, CategoriaRequestDTO dto) {
        Categoria categoria = categoriaRepository.findByIdOptional(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada."));

        categoria.setNome(dto.getNome());
        categoria.setDescricao(dto.getDescricao());

        categoriaRepository.persist(categoria);
        return new CategoriaResponseDTO(categoria);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Categoria categoria = categoriaRepository.findByIdOptional(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada."));
        categoriaRepository.delete(categoria);
    }
}
