package com.cardapio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cardapio.entities.Categoria;
import com.cardapio.services.CategoriaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @GetMapping
    public ResponseEntity<List<Categoria>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorId(@PathVariable Long id) {

        Categoria categoria = service.buscarPorId(id);

        if (categoria == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(categoria);
    }

    @PostMapping
    public ResponseEntity<Categoria> cadastrar(@Valid @RequestBody Categoria categoria) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.salvar(categoria));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizar(@PathVariable Long id,
                                               @Valid @RequestBody Categoria categoria) {

        Categoria atualizada = service.atualizar(id, categoria);

        if (atualizada == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(atualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        Categoria categoria = service.buscarPorId(id);

        if (categoria == null) {
            return ResponseEntity.notFound().build();
        }

        service.excluir(id);

        return ResponseEntity.noContent().build();
    }
}