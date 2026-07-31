package com.cardapio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cardapio.entities.Chef;
import com.cardapio.repositories.ChefRepository;

@Service
public class ChefService {

    @Autowired
    private ChefRepository repository;

    public List<Chef> listarTodos() {
        return repository.findAll();
    }

    public Chef buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Chef salvar(Chef chef) {
        return repository.save(chef);
    }

    public Chef atualizar(Long id, Chef chef) {

        Chef chefExistente = repository.findById(id).orElse(null);

        if (chefExistente == null) {
            return null;
        }

        chefExistente.setNome(chef.getNome());
        chefExistente.setEspecialidade(chef.getEspecialidade());
        chefExistente.setTelefone(chef.getTelefone());
        chefExistente.setEmail(chef.getEmail());

        return repository.save(chefExistente);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}