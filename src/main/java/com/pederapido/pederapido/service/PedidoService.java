package com.pederapido.pederapido.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pederapido.pederapido.data.ItemPedidoDTO;
import com.pederapido.pederapido.data.PedidoDTO;
import com.pederapido.pederapido.model.ItemCardapio;
import com.pederapido.pederapido.model.ItemPedido;
import com.pederapido.pederapido.model.ItemPedidoId;
import com.pederapido.pederapido.model.Mesa;
import com.pederapido.pederapido.model.Pedido;
import com.pederapido.pederapido.model.StatusPedido;
import com.pederapido.pederapido.repository.ItemCardapioRepository;
import com.pederapido.pederapido.repository.ItemPedidoRepository;
import com.pederapido.pederapido.repository.MesaRepository;
import com.pederapido.pederapido.repository.PedidoRepository;

@Service
public class PedidoService {
	
//	@Autowired
//	private SimpMessagingTemplate template;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private MesaRepository mesaRepository;
	
	@Autowired
	private ItemCardapioRepository itemCardapioRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private WebSocketService webSocketService;
	
	public void criarPedido(PedidoDTO pedidoEntrada) {
		List<ItemPedido> itensPedido = new ArrayList<ItemPedido>();
		
		pedidoEntrada.getItensPedido().forEach(item -> {
			Optional<ItemCardapio> itemCardapio = itemCardapioRepository.findById(item.getIdItemCardapio());
			
			if (itemCardapio.isPresent()) {
				ItemPedido itemPedido = new ItemPedido(item.getQuantidade(), 
						item.getObservacao(), itemCardapio.get());
				itensPedido.add(itemPedido);
			}
		});
		
		Optional<Mesa> mesa = mesaRepository.findById(pedidoEntrada.getIdMesa());
		
		if (!itensPedido.isEmpty() && mesa.isPresent()) {
			Pedido pedido = new Pedido(itensPedido, StatusPedido.ABERTO, mesa.get());
			Pedido pedidoSalvo = pedidoRepository.save(pedido);
			
			itensPedido.forEach(item -> {
				item.setId(new ItemPedidoId(pedidoSalvo.getId(), item.getItemCardapio().getId()));
				item.setPedido(pedidoSalvo);
				itemPedidoRepository.save(item);
			});
		}
	}
	
	public boolean verificaSeMesaTemPedidoNaoFechado(Long idMesa) {
		Optional<Pedido> pedido = pedidoRepository.buscarPedidoNaoFechadoPorMesa(idMesa);
		
		if (pedido.isPresent()) {
			return true;
		}
		
		return false;
	}

	public List<PedidoDTO> getPedidoPreparoFinalizadoPorMesa(Long mesaId) {
		Optional<Mesa> mesa = mesaRepository.findById(mesaId);
		List<PedidoDTO> listaPedidos = new ArrayList<PedidoDTO>();
		
		if (mesa.isPresent()) {
			List<Pedido> pedidoRetornado = pedidoRepository.buscarPedidoFinalizadoPorMesa(mesa.get().getId());
			
			if (pedidoRetornado != null) {
				
				pedidoRetornado.forEach(p -> {
					List<ItemPedidoDTO> itens = new ArrayList<ItemPedidoDTO>();
					p.getItensPedido().forEach(i -> {
						ItemPedidoDTO item = new ItemPedidoDTO(i.getItemCardapio().getId(), 
								i.getItemCardapio().getTitulo(), i.getItemCardapio().getPreco(), 
								i.getObservacao(), i.getQuantidade());
						
						itens.add(item);
					});
					
					PedidoDTO pedido = new PedidoDTO(itens, p.getMesa().getId(), 
							p.getId(), p.getStatus().name(), p.getMesa().getRestaurante().getId());
					listaPedidos.add(pedido);
				});				
				
			}
		}
		
		return listaPedidos;
	}

	public void atualizarStatusPedido(Long pedidoId, Integer statusId) {
		Pedido pedidoAlterado = new Pedido();
		Optional<Pedido> pedido = pedidoRepository.findById(pedidoId);
		StatusPedido novoStatus = StatusPedido.values()[statusId];
		
		if (pedido.isPresent()) {
			pedidoAlterado = pedido.get();
			pedidoAlterado.setStatus(novoStatus);
			pedidoRepository.save(pedidoAlterado);
		}
		
	}
	
	public void getPedidosEmAberto() {
		List<PedidoDTO> listaPedidos = new ArrayList<PedidoDTO>();
		List<Pedido> pedidoRetornado = pedidoRepository.buscarPedidosEmAberto();
		
		if (pedidoRetornado != null) {
			
			pedidoRetornado.forEach(p -> {
				List<ItemPedidoDTO> itens = new ArrayList<ItemPedidoDTO>();
				p.getItensPedido().forEach(i -> {
					ItemPedidoDTO item = new ItemPedidoDTO(i.getItemCardapio().getId(), 
							i.getItemCardapio().getTitulo(), i.getItemCardapio().getPreco(), 
							i.getObservacao(), i.getQuantidade());
					
					itens.add(item);
				});
				
				PedidoDTO pedido = new PedidoDTO(itens, p.getMesa().getId(), p.getId(), 
						p.getStatus().name(), p.getMesa().getRestaurante().getId());
				listaPedidos.add(pedido);
			});				
			
		}
		
//		template.convertAndSend("/emAberto", listaPedidos);
		
		webSocketService.atualizacaoPedidos(listaPedidos, "/emAberto");
	}
	
	public void getPedidosEmPreparacao() {
		List<PedidoDTO> listaPedidos = new ArrayList<PedidoDTO>();
		
		List<Pedido> pedidoRetornado = pedidoRepository.buscarPedidosEmPreparacao();
		
		if (pedidoRetornado != null) {
			
			pedidoRetornado.forEach(p -> {
				List<ItemPedidoDTO> itens = new ArrayList<ItemPedidoDTO>();
				p.getItensPedido().forEach(i -> {
					ItemPedidoDTO item = new ItemPedidoDTO(i.getItemCardapio().getId(), 
							i.getItemCardapio().getTitulo(), i.getItemCardapio().getPreco(), 
							i.getObservacao(), i.getQuantidade());
					
					itens.add(item);
				});
				
				PedidoDTO pedido = new PedidoDTO(itens, p.getMesa().getId(), p.getId(), 
						p.getStatus().name(), p.getMesa().getRestaurante().getId());
				listaPedidos.add(pedido);
			});				
			
		}
		
//		template.convertAndSend("/emPreparacao", listaPedidos);
		
		webSocketService.atualizacaoPedidos(listaPedidos, "/emPreparacao");
	}
	
	public void atualizaTelaCozinha() {
		getPedidosEmAberto();
		getPedidosEmPreparacao();
	}

	public void solicitarConta(Long mesaId) {
		Optional<Pedido> pedido = pedidoRepository.buscarPedidoNaoFechadoPorMesa(mesaId);
		
		if (pedido.isPresent()) {
			atualizarStatusPedido(pedido.get().getId(), StatusPedido.CONTA_SOLICITADA.getValue() - 1);
		}
		
		getPedidosContaSolicitada();
	}
	
	public PedidoDTO getPedidoMesa(Long mesaId) {		
		Optional<Pedido> pedidoRetornado = pedidoRepository.buscarPedidoNaoFechadoPorMesa(mesaId);
		PedidoDTO pedido = null;
		
		if (pedidoRetornado.isPresent()) {
			List<ItemPedidoDTO> itens = new ArrayList<ItemPedidoDTO>();
			pedidoRetornado.get().getItensPedido().forEach(i -> {
				ItemPedidoDTO item = new ItemPedidoDTO(i.getItemCardapio().getId(), 
						i.getItemCardapio().getTitulo(), i.getItemCardapio().getPreco(), 
						i.getObservacao(), i.getQuantidade());
				
				itens.add(item);
			});
			
			pedido = new PedidoDTO(itens, pedidoRetornado.get().getMesa().getId(), 
					pedidoRetornado.get().getId(), pedidoRetornado.get().getStatus().name(), 
					pedidoRetornado.get().getMesa().getRestaurante().getId());
		}
		
		return pedido;
	}
	
	public void atualizaTelaGarcom() {
		getPedidosContaSolicitada();
	}
	
	public void getPedidosContaSolicitada() {
		List<PedidoDTO> listaPedidos = new ArrayList<PedidoDTO>();
		
		List<Pedido> pedidoRetornado = pedidoRepository.buscarPedidosContaSolicitada();
		
		if (pedidoRetornado != null) {
			
			pedidoRetornado.forEach(p -> {
				List<ItemPedidoDTO> itens = new ArrayList<ItemPedidoDTO>();
				p.getItensPedido().forEach(i -> {
					ItemPedidoDTO item = new ItemPedidoDTO(i.getItemCardapio().getId(), 
							i.getItemCardapio().getTitulo(), i.getItemCardapio().getPreco(), 
							i.getObservacao(), i.getQuantidade());
					
					itens.add(item);
				});
				
				PedidoDTO pedido = new PedidoDTO(itens, p.getMesa().getId(), p.getId(), 
						p.getStatus().name(), p.getMesa().getRestaurante().getId());
				listaPedidos.add(pedido);
			});				
			
		}
		
//		template.convertAndSend("/contaSolicitada", listaPedidos);
		webSocketService.atualizacaoPedidos(listaPedidos, "/contaSolicitada");
	}
}
