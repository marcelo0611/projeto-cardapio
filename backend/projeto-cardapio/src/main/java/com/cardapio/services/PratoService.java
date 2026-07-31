package com.cardapio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cardapio.entities.Prato;
import com.cardapio.repositories.PratoRepository;

@Service
public class PratoService {

    @Autowired
    private PratoRepository repository;

    public List<Prato> listarTodos() {
        return repository.findAll();
    }

    public Prato buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Prato salvar(Prato prato) {
        return repository.save(prato);
    }

    public Prato atualizar(Long id, Prato prato) {
        Prato pratoExistente = repository.findById(id).orElse(null);

        if (pratoExistente == null) {
            return null;
        }

        pratoExistente.setNome(prato.getNome());
        pratoExistente.setDescricao(prato.getDescricao());
        pratoExistente.setPreco(prato.getPreco());
        pratoExistente.setCategoria(prato.getCategoria());
        pratoExistente.setChef(prato.getChef());

        return repository.save(pratoExistente);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}