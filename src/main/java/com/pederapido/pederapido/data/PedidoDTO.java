package com.pederapido.pederapido.data;

import java.util.Set;

import lombok.Data;

@Data
public class PedidoDTO {
	
	private Set<ItemPedidoDTO> itemCardapio;
	
	private Long idMesa;

}
