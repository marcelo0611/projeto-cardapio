package com.example.cardapio.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cardapio.Entities.Categoria;

public interface CategoriaRepository extends JpaRepository <Categoria, Long>{

}
