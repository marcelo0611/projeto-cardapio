package com.example.cardapio.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.cardapio.Entities.Chef;
import com.example.cardapio.Repositories.ChefRepository;

public class ChefService {

	@Autowired
    private ChefRepository repository;

    public List<Chef> listarTodos() {
        return repository.findAll();
    }

    public Optional<Chef> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Chef salvar(Chef chef) {
        return repository.save(chef);
    }

    public Chef atualizar(Long id, Chef chefAlterado) {
        Optional<Chef> chefExistente = buscarPorId(id);

        if (chefExistente.isPresent()) {
            Chef chefAtualizado = chefExistente.get();

            chefAtualizado.setNome(chefAlterado.getNome());
            chefAtualizado.setCargo(chefAlterado.getCargo());

            return repository.save(chefAtualizado);
        }

        return null;
    }

 
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
