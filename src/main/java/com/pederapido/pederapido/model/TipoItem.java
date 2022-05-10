package com.pederapido.pederapido.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoItem {
	
	REFEICAO(1), BEBIDA(2), SOBREMESA(3);
	
	private final int value;
}
