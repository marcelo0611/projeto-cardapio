package com.example.cardapio.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.cardapio.Entities.Fornecedor;
import com.example.cardapio.Repositories.FornecedorRepository;

public class FornecedorService {

	@Autowired
    private FornecedorRepository repository;

    public List<Fornecedor> listarTodos() {
        return repository.findAll();
    }

    public Optional<Fornecedor> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Fornecedor salvar(Fornecedor fornecedor) {
        return repository.save(fornecedor);
    }

    public Fornecedor atualizar(Long id, Fornecedor fornecedorAlterado) {
        Optional<Fornecedor> fornecedorExistente = buscarPorId(id);

        if (fornecedorExistente.isPresent()) {
        	Fornecedor fornecedorAtualizado = fornecedorExistente.get();

        	fornecedorAtualizado.setNome(fornecedorAlterado.getNome());
        	fornecedorAtualizado.setCnpj(fornecedorAlterado.getCnpj());
        	fornecedorAtualizado.setTelefone(fornecedorAlterado.getTelefone());

            return repository.save(fornecedorAtualizado);
        }

        return null;
    }

 
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
