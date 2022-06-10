package com.pederapido.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
	
	ATIVO(1), DESATIVADO(2);
	
	private final int value;

}
