package com.pederapido.pederapido.data;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PedidoDTO {
	
	private List<ItemPedidoDTO> itensPedido;
	private Long idMesa;
	private Long idPedido;

}
