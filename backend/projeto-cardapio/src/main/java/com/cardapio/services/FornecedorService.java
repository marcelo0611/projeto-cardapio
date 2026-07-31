package com.cardapio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cardapio.entities.Fornecedor;
import com.cardapio.repositories.FornecedorRepository;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository repository;

    public List<Fornecedor> listarTodos() {
        return repository.findAll();
    }

    public Fornecedor buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Fornecedor salvar(Fornecedor fornecedor) {
        return repository.save(fornecedor);
    }

    public Fornecedor atualizar(Long id, Fornecedor fornecedor) {

        Fornecedor fornecedorExistente = repository.findById(id).orElse(null);

        if (fornecedorExistente == null) {
            return null;
        }

        fornecedorExistente.setNome(fornecedor.getNome());
        fornecedorExistente.setCnpj(fornecedor.getCnpj());
        fornecedorExistente.setTelefone(fornecedor.getTelefone());
        fornecedorExistente.setEmail(fornecedor.getEmail());

        return repository.save(fornecedorExistente);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}