package com.pederapido.pederapido.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pederapido.pederapido.data.PedidoDTO;
import com.pederapido.pederapido.model.Pedido;
import com.pederapido.pederapido.repository.PedidoRepository;
import com.pederapido.pederapido.service.PedidoService;

@RestController
@RequestMapping(value = "/pedido")
public class PedidoController {
	
//	@Autowired
//	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PedidoService pedidoService;
	
//	@GetMapping(value = "/{pedidoId}")
//    public ResponseEntity<Pedido> getPedido(@PathVariable Long restauranteId){
//		Pedido pedido = pedidoRepository.findById(restauranteId).get();
//        return new ResponseEntity<Pedido>(pedido, HttpStatus.OK);
//    }
	
	@PostMapping
	public ResponseEntity<Pedido> enviarPedido(@RequestBody PedidoDTO pedido){
		pedidoService.enviarPedido(pedido);
        return new ResponseEntity<Pedido>(HttpStatus.CREATED);
    }

}
