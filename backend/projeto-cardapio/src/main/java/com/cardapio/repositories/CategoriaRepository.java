package com.cardapio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cardapio.entities.Categoria;

public interface CategoriaRepository extends JpaRepository <Categoria, Long>{

}
