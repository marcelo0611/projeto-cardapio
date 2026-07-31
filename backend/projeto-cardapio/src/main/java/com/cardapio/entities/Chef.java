package com.cardapio.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "chef")
public class Chef {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do chef é obrigatório.")
    @Size(max = 120, message = "O nome deve possuir no máximo 120 caracteres.")
    private String nome;

    @NotBlank(message = "A especialidade é obrigatória.")
    @Size(max = 120, message = "A especialidade deve possuir no máximo 120 caracteres.")
    private String especialidade;

    @Size(max = 20, message = "O telefone deve possuir no máximo 20 caracteres.")
    private String telefone;

    @Size(max = 120, message = "O e-mail deve possuir no máximo 120 caracteres.")
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "chef")
    private List<Prato> pratos = new ArrayList<>();

    public Chef() {
    }

    public Chef(Long id, String nome, String especialidade, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.especialidade = especialidade;
        this.telefone = telefone;
        this.email = email;
    }


    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
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

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPratos(List<Prato> pratos) {
        this.pratos = pratos;
    }
}