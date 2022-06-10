package com.pederapido.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class PedidoProperties {
	
	 private String[] origensPermitidas;
	 
	 @Autowired
	    public PedidoProperties(@Value("${pederapido.origenspermitidas}") String[] origens) {
	        this.origensPermitidas = origens;
	    }

}
