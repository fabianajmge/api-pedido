package com.pederapido.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_item_pedido")
@Getter
@Setter
public class ItemPedido {

	@EmbeddedId
	private ItemPedidoId id;

	@NotNull
	@MapsId("pedidoId")
	@ManyToOne(optional = false)
	@JoinColumn(name = "id_pedido", nullable = false)
	private Pedido pedido;

	private Integer quantidade;

	private String observacao;

	@NotNull
	@MapsId("itemCardapioId")
	@ManyToOne(optional = false)
	@JoinColumn(name = "id_item_cardapio", nullable = false)
	private ItemCardapio itemCardapio;

	public ItemPedido() {

	}

	public ItemPedido(Integer quantidade, String observacao, ItemCardapio itemCardapio) {
		this.quantidade = quantidade;
		this.observacao = observacao;
		this.itemCardapio = itemCardapio;
	}

	
	
}
