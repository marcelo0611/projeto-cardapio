package com.cardapio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cardapio.entities.Prato;
import com.cardapio.services.PratoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/prato")
public class PratoController {

    @Autowired
    private PratoService service;

    @GetMapping
    public ResponseEntity<List<Prato>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prato> buscarPorId(@PathVariable Long id) {

        Prato prato = service.buscarPorId(id);

        if (prato == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(prato);
    }

    @PostMapping
    public ResponseEntity<Prato> cadastrar(@Valid @RequestBody Prato prato) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.salvar(prato));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prato> atualizar(@PathVariable Long id,
                                           @Valid @RequestBody Prato prato) {

        Prato atualizado = service.atualizar(id, prato);

        if (atualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        Prato prato = service.buscarPorId(id);

        if (prato == null) {
            return ResponseEntity.notFound().build();
        }

        service.excluir(id);

        return ResponseEntity.noContent().build();
    }
}