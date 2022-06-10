package com.pederapido.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusPedido {
	
	ABERTO(1), EM_PREPARACAO(2), PREPARO_FINALIZADO(3), CONTA_SOLICITADA(4), FECHADO(5);
	
	private final int value;
}
