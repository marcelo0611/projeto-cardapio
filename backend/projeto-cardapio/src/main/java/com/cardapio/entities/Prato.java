package com.cardapio.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_prato")
public class Prato {
	
	@Id
	@GeneratedValue(strategy = Generated)

}
