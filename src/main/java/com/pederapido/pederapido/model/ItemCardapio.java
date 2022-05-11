package com.pederapido.pederapido.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;

@Entity
@Table(name="tb_item_cardapio")
@Getter
public class ItemCardapio {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

	private String titulo;
	
	private String detalhe;
	
	private BigDecimal preco;
	
	@Enumerated(EnumType.ORDINAL)
	private TipoItem tipo;
	
	@ManyToOne
	@JoinColumn(name = "id_restaurante")
	private Restaurante restaurante;

	public ItemCardapio(Long id) {
		this.id = id;
	}
	
	public ItemCardapio() {
		
	}
}
