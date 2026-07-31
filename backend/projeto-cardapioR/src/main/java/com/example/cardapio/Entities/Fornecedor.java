package com.example.cardapio.Entities;

import org.hibernate.validator.constraints.br.CNPJ;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_fornecedor")
public class Fornecedor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O nome do fornecedor é obrigatório")
	private String nome;
	
	@NotBlank(message = "O cnpj é obrigatório")
	@CNPJ
	@Column(unique = true)
	private String cnpj;
	
	@NotBlank(message = "O telefone é obrigatório")
	@Column(unique = true)
	private String telefone;
	
	public Fornecedor() {
		
	}
	
	public Fornecedor(String nome, String cnpj, String telefone) {
		this.nome = nome;
		this.cnpj = cnpj;
		this.telefone = telefone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	
}
