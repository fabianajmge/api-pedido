package com.pederapido.pederapido.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
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

	private String title;
	
	private String detail;
	
	private BigDecimal price;
	
	private TipoItem type;
	
	@ManyToOne
	@JoinColumn(name = "id_restaurante")
	@JsonIgnore
	private Restaurante restaurante;

}
