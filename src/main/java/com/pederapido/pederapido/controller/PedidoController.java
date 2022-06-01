package com.pederapido.pederapido.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pederapido.pederapido.data.PedidoDTO;
import com.pederapido.pederapido.model.CriacaoProcesso;
import com.pederapido.pederapido.service.PedidoService;

@RestController
@RequestMapping(value = "/pedido")
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping(value = "/porMesa")
	public ResponseEntity<PedidoDTO> getPedidoPreparoFinalizadoPorMesa(@RequestParam(name = "mesaId") Long mesaId) {
		HttpStatus status = HttpStatus.OK;
		PedidoDTO pedido = pedidoService.getPedidoMesa(mesaId);
		
		if (pedido == null) {
			status = HttpStatus.NOT_FOUND;
		}
		
		return new ResponseEntity<PedidoDTO>(pedido, status);
	}
	
	@GetMapping(value = "/emAberto")
	public ResponseEntity<CriacaoProcesso> getPedidosEmAberto() {
		pedidoService.getPedidosEmAberto();
		return new ResponseEntity<CriacaoProcesso>(new CriacaoProcesso(LocalDateTime.now()), HttpStatus.OK);
	}
	
	@GetMapping(value = "/emPreparacao")
	public ResponseEntity<CriacaoProcesso> getPedidosEmPreparacao() {
		pedidoService.getPedidosEmPreparacao();
		return new ResponseEntity<CriacaoProcesso>(new CriacaoProcesso(LocalDateTime.now()), HttpStatus.OK);
	}
	
	@GetMapping(value = "/contaSolicitada")
	public ResponseEntity<CriacaoProcesso> getPedidosContaSolicitada() {
		pedidoService.getPedidosContaSolicitada();
		return new ResponseEntity<CriacaoProcesso>(new CriacaoProcesso(LocalDateTime.now()), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> criarPedido(@RequestBody PedidoDTO pedido){
		boolean mesaJaPossuiPedidoEmAtendimento = pedidoService.verificaSeMesaTemPedidoNaoFechado(pedido.getIdMesa());
		HttpStatus status = HttpStatus.CREATED;
		
		if (!mesaJaPossuiPedidoEmAtendimento) {
			pedidoService.criarPedido(pedido);
			pedidoService.atualizaTelaCozinha();
		} else {
			status = HttpStatus.BAD_REQUEST;
		}
		
        return new ResponseEntity<Void>(status);
    }
	
	@PutMapping
	public ResponseEntity<?> atualizarStatusPedido(@RequestParam(name = "pedidoId") Long pedidoId, 
			@RequestParam(name = "statusId") Integer statusId) {
		pedidoService.atualizarStatusPedido(pedidoId, statusId);
		pedidoService.atualizaTelaCozinha();
		pedidoService.atualizaTelaGarcom();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PutMapping(value = "/solicitarConta")
	public ResponseEntity<?> solicitarConta(@RequestParam(name = "mesaId") Long mesaId) {
		pedidoService.solicitarConta(mesaId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping(value = "/pedidoAbertoMesa/{mesaId}")
	public ResponseEntity<?> pedidoAbertoMesa(@PathVariable Long mesaId) {
		boolean pedidoAbertoMesa = pedidoService.pedidoAbertoMesa(mesaId);
		HttpStatus status = HttpStatus.OK;

		if (!pedidoAbertoMesa) {
			status = HttpStatus.NOT_FOUND;
		}
		
		return new ResponseEntity<Void>(status);
	}

}
