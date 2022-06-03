package com.pederapido.pederapido.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pederapido.pederapido.data.RestauranteDTO;
import com.pederapido.pederapido.service.RestauranteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/restaurante")
@Api(value = "Restaurante")
public class RestauranteController {
	
	@Autowired
	RestauranteService restauranteService;
	
	@ApiOperation(value = "Lista restaurantes")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso", response = RestauranteDTO[].class),
			@ApiResponse(code = 404, message = "Não encontrado")
	})
	@GetMapping(value = "/restaurantes")
	public ResponseEntity<List<RestauranteDTO>> getRestaurantes() {
		HttpStatus status = HttpStatus.OK;
		List<RestauranteDTO> restaurantes = restauranteService.getRestaurantes();
		
		if (restaurantes.isEmpty() || restaurantes == null) {
			status = HttpStatus.NOT_FOUND;
		}
		
		return new ResponseEntity<List<RestauranteDTO>>(restaurantes, status);
	}
	
	@ApiOperation(value = "Lista restaurantes")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso", response = RestauranteDTO[].class),
			@ApiResponse(code = 404, message = "Não encontrado")
	})
	@GetMapping(value = "/restauranteMesa/{idMesa}")
	public ResponseEntity<RestauranteDTO> getRestauranteMesa(@PathVariable Long idMesa) {
		HttpStatus status = HttpStatus.OK;
		RestauranteDTO restaurante = restauranteService.getRestauranteMesa(idMesa);
		
		if (restaurante == null) {
			status = HttpStatus.NOT_FOUND;
		}
		
		return new ResponseEntity<RestauranteDTO>(restaurante, status);
	}

}
