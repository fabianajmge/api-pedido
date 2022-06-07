package com.pederapido.pederapido.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_pedido")
@Getter
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@JsonIgnore
    @OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER)
	private List<ItemPedido> itensPedido;
	
	@Enumerated(EnumType.ORDINAL)
	@Setter
	private StatusPedido status;
	
	@NotNull
    @ManyToOne(optional = false)
	@JoinColumn(name = "id_mesa", nullable = false, referencedColumnName = "id")
	@JsonIgnore
	private Mesa mesa;
	
    @NotNull
    @Column(name = "data_criacao", updatable = false, nullable = false)
    private LocalDateTime dataCriacao;
    
    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;

	public Pedido(List<ItemPedido> itensPedido, StatusPedido status, Mesa mesa) {
		this.itensPedido = itensPedido;
		this.status = status;
		this.mesa = mesa;
		this.dataCriacao = LocalDateTime.now();
	}
	
	public Pedido() {
		
	}

}
