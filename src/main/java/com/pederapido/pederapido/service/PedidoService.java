package com.pederapido.pederapido.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pederapido.pederapido.data.PedidoDTO;
import com.pederapido.pederapido.model.ItemCardapio;
import com.pederapido.pederapido.model.Mesa;
import com.pederapido.pederapido.model.Pedido;
import com.pederapido.pederapido.model.StatusPedido;
import com.pederapido.pederapido.repository.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido enviarPedido(PedidoDTO pedidoEntrada) {
		Set<ItemCardapio> itensCardapio = new HashSet<ItemCardapio>();
		
		pedidoEntrada.getItemCardapio().forEach(item -> {
			ItemCardapio itemCardapio = new ItemCardapio(item.getIdItemCardapio());
			itensCardapio.add(itemCardapio);
		});
		Mesa mesa = new Mesa(pedidoEntrada.getIdMesa());
		Pedido pedido = new Pedido(itensCardapio, StatusPedido.ABERTO, mesa);
		pedidoRepository.save(pedido);
		return pedido;
	}

}
