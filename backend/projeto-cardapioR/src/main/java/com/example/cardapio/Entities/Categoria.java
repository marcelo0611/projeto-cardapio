package com.example.cardapio.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_categoria")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "A entrada é obrigatória")
	@Column(nullable = false)
	private String entrada;
	
	@NotBlank(message = "O prato principal é obrigatória")
	@Column(nullable = false)
	private String pratoPrincipal;
	
	@NotBlank(message = "A sobremesa é obrigatória")
	@Column(nullable = false)
	private String sobremesa;
	
	public Categoria() {
		
	}
	
	public Categoria(String entrada, String pratoPrincipal, String sobremesa) {
		this.entrada = entrada;
		this.pratoPrincipal = pratoPrincipal;
		this.sobremesa = sobremesa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEntrada() {
		return entrada;
	}

	public void setEntrada(String entrada) {
		this.entrada = entrada;
	}

	public String getPratoPrincipal() {
		return pratoPrincipal;
	}

	public void setPratoPrincipal(String pratoPrincipal) {
		this.pratoPrincipal = pratoPrincipal;
	}

	public String getSobremesa() {
		return sobremesa;
	}

	public void setSobremesa(String sobremesa) {
		this.sobremesa = sobremesa;
	}
	
	
}
