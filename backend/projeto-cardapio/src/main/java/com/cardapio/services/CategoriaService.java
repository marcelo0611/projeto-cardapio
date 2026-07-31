package com.cardapio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cardapio.entities.Categoria;
import com.cardapio.repositories.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public List<Categoria> listarTodos() {
        return repository.findAll();
    }

    public Categoria buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Categoria salvar(Categoria categoria) {
        return repository.save(categoria);
    }

    public Categoria atualizar(Long id, Categoria categoria) {

        Categoria categoriaExistente = repository.findById(id).orElse(null);

        if (categoriaExistente == null) {
            return null;
        }

        categoriaExistente.setNome(categoria.getNome());

        return repository.save(categoriaExistente);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}