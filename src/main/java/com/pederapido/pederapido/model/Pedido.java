package com.pederapido.pederapido.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Table(name = "tb_pedido")
@Getter
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToMany
	@JoinTable(name = "itens_pedido", joinColumns = @JoinColumn(name = "id_pedido"), inverseJoinColumns = @JoinColumn(name = "id_item_cardapio"))
	private Set<ItemCardapio> itemCardapio;
	
	private StatusPedido status;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_mesa", referencedColumnName = "id")
	private Mesa mesa;

	public Pedido(Set<ItemCardapio> itemCardapio, StatusPedido status, Mesa mesa) {
		this.itemCardapio = itemCardapio;
		this.status = status;
		this.mesa = mesa;
	}

}
