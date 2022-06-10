package com.pederapido.data;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemCardapioDTO {
	
	private Long id;
	private String titulo;
	private String detalhe;
	private BigDecimal preco;
	private Integer tipo;

}
