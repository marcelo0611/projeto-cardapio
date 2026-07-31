package com.example.cardapio.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.cardapio.Entities.Categoria;
import com.example.cardapio.Repositories.CategoriaRepository;

public class CategoriaService {

	@Autowired
    private CategoriaRepository repository;

    public List<Categoria> listarTodos() {
        return repository.findAll();
    }

    public Optional<Categoria> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Categoria salvar(Categoria categoria) {
        return repository.save(categoria);
    }

    public Categoria atualizar(Long id, Categoria categoriaAlterada) {
        Optional<Categoria> categoriaExistente = buscarPorId(id);

        if (categoriaExistente.isPresent()) {
            Categoria catAtualizada = categoriaExistente.get();

            catAtualizada.setEntrada(categoriaAlterada.getEntrada());
            catAtualizada.setPratoPrincipal(categoriaAlterada.getPratoPrincipal());
            catAtualizada.setSobremesa(categoriaAlterada.getSobremesa());

            return repository.save(catAtualizada);
        }

        return null;
    }

 
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
