package com.pederapido.pederapido.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pederapido.pederapido.model.ItemCardapio;
import com.pederapido.pederapido.model.Restaurante;
import com.pederapido.pederapido.repository.ItemCardapioRepository;
import com.pederapido.pederapido.repository.RestauranteRepository;

@RestController
@RequestMapping(value = "/cardapio1")
public class CardapioController {
	
	@Autowired
	private ItemCardapioRepository cardapioRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;

	@GetMapping(value = "/{restauranteId}")
    public List<ItemCardapio> getCardapio(@PathVariable Long restauranteId){
		// colocar tratamento de exceção
		Restaurante restaurante = restauranteRepository.findById(restauranteId).get();
        return cardapioRepository.findByRestaurante(restaurante);
    }
}
