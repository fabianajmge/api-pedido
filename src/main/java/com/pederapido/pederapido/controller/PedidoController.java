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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/pedido")
@Api(value = "Pedido")
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;
	
	@ApiOperation(value = "Busca pedido onde o preparo foi finalizado por mesa")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso", response = PedidoDTO.class),
			@ApiResponse(code = 404, message = "Não encontrado")
	})
	@GetMapping(value = "/porMesa")
	public ResponseEntity<PedidoDTO> getPedidoPreparoFinalizadoPorMesa(@RequestParam(name = "mesaId") Long mesaId) {
		HttpStatus status = HttpStatus.OK;
		PedidoDTO pedido = pedidoService.getPedidoMesa(mesaId);
		
		if (pedido == null) {
			status = HttpStatus.NOT_FOUND;
		}
		
		return new ResponseEntity<PedidoDTO>(pedido, status);
	}
	
	@ApiOperation(value = "Envia solicitacao para o websocket atualizar lista a cozinha")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso", response = CriacaoProcesso.class)
	})
	@GetMapping(value = "/emAberto")
	public ResponseEntity<CriacaoProcesso> getPedidosEmAberto() {
		pedidoService.getPedidosEmAberto();
		return new ResponseEntity<CriacaoProcesso>(new CriacaoProcesso(LocalDateTime.now()), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Envia solicitacao para o websocket atualizar lista a cozinha")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso", response = CriacaoProcesso.class)
	})
	@GetMapping(value = "/emPreparacao")
	public ResponseEntity<CriacaoProcesso> getPedidosEmPreparacao() {
		pedidoService.getPedidosEmPreparacao();
		return new ResponseEntity<CriacaoProcesso>(new CriacaoProcesso(LocalDateTime.now()), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Envia solicitacao para o websocket atualizar lista dos garcons")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso", response = CriacaoProcesso.class)
	})
	@GetMapping(value = "/contaSolicitada")
	public ResponseEntity<CriacaoProcesso> getPedidosContaSolicitada() {
		pedidoService.getPedidosContaSolicitada();
		return new ResponseEntity<CriacaoProcesso>(new CriacaoProcesso(LocalDateTime.now()), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Criar pedido")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Pedido criado"),
			@ApiResponse(code = 400, message = "Já existe um pedido aberto na mesa")
	})
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
	
	@ApiOperation(value = "Atualizar status do pedido")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso"),
	})
	@PutMapping
	public ResponseEntity<?> atualizarStatusPedido(@RequestParam(name = "pedidoId") Long pedidoId, 
			@RequestParam(name = "statusId") Integer statusId) {
		pedidoService.atualizarStatusPedido(pedidoId, statusId);
		pedidoService.atualizaTelaCozinha();
		pedidoService.atualizaTelaGarcom();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "Solicitar a conta")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso"),
	})
	@PutMapping(value = "/solicitarConta")
	public ResponseEntity<?> solicitarConta(@RequestParam(name = "mesaId") Long mesaId) {
		pedidoService.solicitarConta(mesaId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "Busca pedido aberto por mesa")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso"),
			@ApiResponse(code = 404, message = "Não encontrado")
	})
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
