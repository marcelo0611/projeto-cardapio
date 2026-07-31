package com.example.cardapio.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cardapio.Entities.Prato;
import com.example.cardapio.Repositories.PratoRepository;

@Service
public class PratoService {

	@Autowired
    private PratoRepository repository;

    public List<Prato> listarTodos() {
        return repository.findAll();
    }

    public Optional<Prato> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Prato salvar(Prato prato) {
        return repository.save(prato);
    }

    public Prato atualizar(Long id, Prato pratoAlterado) {
        Optional<Prato> pratoExistente = buscarPorId(id);

        if (pratoExistente.isPresent()) {
            Prato atualizado = pratoExistente.get();

            atualizado.setIngredientes(pratoAlterado.getIngredientes());
            atualizado.setPeso(pratoAlterado.getPeso());

            return repository.save(atualizado);
        }

        return null;
    }

 
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
