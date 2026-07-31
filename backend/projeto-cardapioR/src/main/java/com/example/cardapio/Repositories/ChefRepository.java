package com.example.cardapio.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cardapio.Entities.Chef;

public interface ChefRepository extends JpaRepository <Chef, Long>{

}
