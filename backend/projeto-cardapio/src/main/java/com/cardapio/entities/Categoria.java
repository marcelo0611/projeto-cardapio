package com.cardapio.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome da categoria é obrigatório.")
    @Size(max = 80, message = "O nome deve possuir no máximo 80 caracteres.")
    private String nome;

    @OneToMany(mappedBy = "categoria")
    private List<Prato> pratos = new ArrayList<>();

    public Categoria() {
    }

    public Categoria(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Categoria(Long id, String nome, List<Prato> pratos) {
        this.id = id;
        this.nome = nome;
        this.pratos = pratos;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<Prato> getPratos() {
        return pratos;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPratos(List<Prato> pratos) {
        this.pratos = pratos;
    }
}