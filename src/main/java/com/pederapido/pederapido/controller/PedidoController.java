package com.pederapido.pederapido.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	public ResponseEntity<List<PedidoDTO>> getPedidoPreparoFinalizadoPorMesa(@RequestParam(name = "mesaId") Long mesaId) {
		HttpStatus status = HttpStatus.OK;
		List<PedidoDTO> pedido = pedidoService.getPedidoPreparoFinalizadoPorMesa(mesaId);
		
		if (pedido.isEmpty()) {
			status = HttpStatus.NOT_FOUND;
		}
		
		return new ResponseEntity<List<PedidoDTO>>(pedido, status);
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
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PutMapping(value = "/solicitarConta")
	public ResponseEntity<?> solicitarConta(@RequestParam(name = "mesaId") Long mesaId, 
			@RequestParam(name = "statusId") Integer statusId) {
		pedidoService.solicitarConta(mesaId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
