package com.cardapio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cardapio.entities.Chef;

public interface ChefRepository extends JpaRepository <Chef, Long>{

}
