package com.pederapido.pederapido.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pederapido.pederapido.data.PedidoDTO;
import com.pederapido.pederapido.model.ItemCardapio;
import com.pederapido.pederapido.model.Mesa;
import com.pederapido.pederapido.model.Pedido;
import com.pederapido.pederapido.model.StatusPedido;
import com.pederapido.pederapido.repository.MesaRepository;
import com.pederapido.pederapido.repository.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private MesaRepository mesaRepository;
	
	public void criarPedido(PedidoDTO pedidoEntrada) {
		Set<ItemCardapio> itensCardapio = new HashSet<ItemCardapio>();
		
		pedidoEntrada.getItemCardapio().forEach(item -> {
			ItemCardapio itemCardapio = new ItemCardapio(item.getIdItemCardapio());
			itensCardapio.add(itemCardapio);
		});
		Mesa mesa = new Mesa(pedidoEntrada.getIdMesa());
		Pedido pedido = new Pedido(itensCardapio, StatusPedido.ABERTO, mesa);
		pedidoRepository.save(pedido);
	}

	public Pedido getPedidoPorMesa(Long mesaId) {
		Optional<Mesa> mesa = mesaRepository.findById(mesaId);
		Pedido pedido = null;
		
		if (mesa.isPresent()) {
			pedido = pedidoRepository.findByMesa(mesa.get());
		}
		
		return pedido;
	}

}
