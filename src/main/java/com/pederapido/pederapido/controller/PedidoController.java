package com.pederapido.pederapido.controller;

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
import com.pederapido.pederapido.service.PedidoService;

@RestController
@RequestMapping(value = "/pedido")
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping
	public ResponseEntity<List<PedidoDTO>> getPedidoPorMesa(@RequestParam(name = "mesaId") Long mesaId) {
		HttpStatus status = HttpStatus.OK;
		List<PedidoDTO> pedido = pedidoService.getPedidoPorMesa(mesaId);
		
		if (pedido.isEmpty()) {
			status = HttpStatus.NOT_FOUND;
		}
		
		return new ResponseEntity<List<PedidoDTO>>(pedido, status);
	}
	
	@PostMapping
	public ResponseEntity<?> criarPedido(@RequestBody PedidoDTO pedido){
		pedidoService.criarPedido(pedido);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
	
	@PutMapping
	public ResponseEntity<?> atualizarStatusPedido(@RequestParam(name = "pedidoId") Long pedidoId, 
			@RequestParam(name = "statusId") Integer statusId) {
		pedidoService.atualizarStatusPedido(pedidoId, statusId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
