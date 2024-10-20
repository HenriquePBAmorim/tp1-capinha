package br.unitins.tp1.capinha.service;

import br.unitins.tp1.capinha.dto.CategoriaRequestDTO;
import br.unitins.tp1.capinha.model.Categoria;
import br.unitins.tp1.capinha.repository.CategoriaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class CategoriaServiceImpl implements CategoriaService {

    @Inject
    CategoriaRepository categoriaRepository;

    @Override
    public Categoria findById(Long id) {
        return categoriaRepository.findById(id);
    }

    @Override
    public List<Categoria> findByNome(String nome) {
        return categoriaRepository.find("nome", nome).list();
    }

    @Override
    public List<Categoria> findAll() {
        return categoriaRepository.listAll();
    }

    @Override
    @Transactional
    public Categoria create(CategoriaRequestDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setNome(dto.getNome());
        categoria.setDescricao(dto.getDescricao());

        categoriaRepository.persist(categoria);
        return categoria;
    }

    @Override
    @Transactional
    public Categoria update(Long id, CategoriaRequestDTO dto) {
        Categoria categoria = categoriaRepository.findById(id);
        if (categoria != null) {
            categoria.setNome(dto.getNome());
            categoria.setDescricao(dto.getDescricao());
        }
        return categoria;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        categoriaRepository.deleteById(id);
    }
}
