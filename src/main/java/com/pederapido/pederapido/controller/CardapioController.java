package com.pederapido.pederapido.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pederapido.pederapido.data.ItemCardapioDTO;
import com.pederapido.pederapido.service.CardapioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/cardapio")
@Api(value = "Cardapio")
public class CardapioController {
	
	@Autowired
	private CardapioService cardapioService;

	@ApiOperation(value = "Lista itens do cardapio")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso", response = ItemCardapioDTO[].class),
			@ApiResponse(code = 404, message = "Não encontrado")
	})
	@GetMapping(value = "/{restauranteId}")
    public ResponseEntity<List<ItemCardapioDTO>> getCardapio(@PathVariable Long restauranteId){
		HttpStatus status = HttpStatus.OK;
		List<ItemCardapioDTO> itens = cardapioService.getCardapio(restauranteId);
		
		if (itens.isEmpty()) {
			status = HttpStatus.NOT_FOUND;
		}
		
        return new ResponseEntity<List<ItemCardapioDTO>>(itens, status);
    }
}
