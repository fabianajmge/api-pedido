package com.pederapido.pederapido.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pederapido.pederapido.data.PedidoDTO;
import com.pederapido.pederapido.model.Pedido;
import com.pederapido.pederapido.service.PedidoService;

@RestController
@RequestMapping(value = "/pedido")
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;
	
//	@GetMapping(value = "/{pedidoId}")
//    public ResponseEntity<Pedido> getPedido(@PathVariable Long restauranteId){
//		Pedido pedido = pedidoRepository.findById(restauranteId).get();
//        return new ResponseEntity<Pedido>(pedido, HttpStatus.OK);
//    }
	
	@GetMapping(value = "/{mesaId}")
	public ResponseEntity<Pedido> getPedidoPorMesa(@PathVariable Long mesaId) {
		HttpStatus status = HttpStatus.OK;
		Pedido pedido = pedidoService.getPedidoPorMesa(mesaId);
		
		if (pedido == null) {
			status = HttpStatus.NOT_FOUND;
		}
		
		return new ResponseEntity<Pedido>(pedido, status);
	}
	
	@PostMapping
	public ResponseEntity<?> criarPedido(@RequestBody PedidoDTO pedido){
		pedidoService.criarPedido(pedido);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

}
