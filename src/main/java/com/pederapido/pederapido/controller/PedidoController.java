package com.pederapido.pederapido.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	
	@CrossOrigin(origins = "http://ec2-100-26-208-1.compute-1.amazonaws.com:8081")
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
		pedidoService.criarPedido(pedido);
		pedidoService.atualizaTelaCozinha();
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
	
	@PutMapping
	public ResponseEntity<?> atualizarStatusPedido(@RequestParam(name = "pedidoId") Long pedidoId, 
			@RequestParam(name = "statusId") Integer statusId) {
		pedidoService.atualizarStatusPedido(pedidoId, statusId);
		pedidoService.atualizaTelaCozinha();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
