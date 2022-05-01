package com.pederapido.pederapido.model;

import lombok.Getter;

@Getter
public enum TipoItem {
	
	REFEICAO(1), BEBIDA(2), SOBREMESA(3);
	
	private final int value;

	TipoItem(int i) {
		this.value = i;
	}

}
