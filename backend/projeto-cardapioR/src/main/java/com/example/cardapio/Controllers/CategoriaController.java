package com.example.cardapio.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.cardapio.Entities.Categoria;
import com.example.cardapio.Services.CategoriaService;

import jakarta.validation.Valid;

public class CategoriaController {

	@Autowired
	private CategoriaService service;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> listar(){
		return ResponseEntity.ok(service.listarTodos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> buscar(@PathVariable Long id){
		Optional<Categoria> categoria = service.buscarPorId(id);
		
		if(categoria != null) {
			return ResponseEntity.ok(categoria.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/cadastro")
	public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria categoria){
		Categoria novaCategoria = service.salvar(categoria);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(novaCategoria);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Categoria> atualizar(@PathVariable Long id, @Valid @RequestBody Categoria categoria) {
		Categoria categoriaAtualizada = service.atualizar(id, categoria);
		
		if (categoriaAtualizada != null) {
			return ResponseEntity.ok(categoriaAtualizada);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> excluir(@PathVariable Long id) {
		Optional<Categoria> categoria = service.buscarPorId(id);
		
		if(categoria.isPresent()) {
			
			service.deletar(id);
		
			return ResponseEntity.status(HttpStatus.OK).body("Sucesso: A categoria foi excluído permanentemente!");
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: Não foi possível deletar. A categoria com ID " + id + " não foi encontrado.");
	}
}
