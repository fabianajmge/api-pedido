package com.pederapido.pederapido.model;

import lombok.Getter;

@Getter
public enum StatusPedido {
	
	ABERTO(1), EM_PREPARACAO(2), PREPARO_FINALIZADO(3);
	
	private final int value;

	StatusPedido(int i) {
		this.value = i;
	}

}
