package com.cardapio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cardapio.entities.Prato;

public interface PratoRepository extends JpaRepository <Prato, Long>{

}
