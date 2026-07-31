package com.cardapio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cardapio.entities.Fornecedor;

public interface FornecedorRepository extends JpaRepository <Fornecedor, Long>{

}
