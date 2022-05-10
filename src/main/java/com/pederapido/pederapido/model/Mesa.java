package com.pederapido.pederapido.model;

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
@Table(name = "tb_mesa")
@Getter
public class Mesa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_restaurante")
	@JsonIgnore
	private Restaurante restaurante;
	
	@Column(name="qrcode")
	private String qrCode;

	public Mesa(Long id) {
		this.id = id;
	}
	
	public Mesa() {
		
	}
}
