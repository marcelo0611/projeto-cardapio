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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cardapio.Entities.Prato;
import com.example.cardapio.Services.PratoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pratos")
public class PratoController {

	@Autowired
	private PratoService service;
	
	@GetMapping
	public ResponseEntity<List<Prato>> listar(){
		return ResponseEntity.ok(service.listarTodos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Prato> buscar(@PathVariable Long id){
		Optional<Prato> prato = service.buscarPorId(id);
		
		if(prato != null) {
			return ResponseEntity.ok(prato.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/cadastro")
	public ResponseEntity<Prato> criar(@Valid @RequestBody Prato prato){
		Prato novoPrato = service.salvar(prato);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(novoPrato);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Prato> atualizar(@PathVariable Long id, @Valid @RequestBody Prato prato) {
		Prato pratoAtualizado = service.atualizar(id, prato);
		
		if (pratoAtualizado != null) {
			return ResponseEntity.ok(pratoAtualizado);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> excluir(@PathVariable Long id) {
		Optional<Prato> prato = service.buscarPorId(id);
		
		if(prato.isPresent()) {
			
			service.deletar(id);
		
			return ResponseEntity.status(HttpStatus.OK).body("Sucesso: O prato foi excluído permanentemente!");
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: Não foi possível deletar. O prato com ID " + id + " não foi encontrado.");
	}
	
	
	
	
}
