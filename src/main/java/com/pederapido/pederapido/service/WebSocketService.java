package com.pederapido.pederapido.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pederapido.pederapido.data.PedidoDTO;

@Service
public class WebSocketService {
	
	@Value("${url-websocket}")
	private String url;
	
	public void atualizacaoPedidos(List<PedidoDTO> listaPedido, String url) {
		final String uri = this.url + url;

	    RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.postForObject(uri, listaPedido, String.class);
	    System.out.println(result);
	}

}
