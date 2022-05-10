package com.pederapido.pederapido.data;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemPedidoDTO {
	
	private Long idItemCardapio;
	private String titulo;
	private BigDecimal preco;
	private String observacao;
	private Integer quantidade;

}
