package com.pederapido.pederapido.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pederapido.pederapido.data.RestauranteDTO;
import com.pederapido.pederapido.model.Restaurante;
import com.pederapido.pederapido.repository.RestauranteRepository;

@Service
public class RestauranteService {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	public List<RestauranteDTO> getRestaurantes() {
		List<Restaurante> restaurantesPesquisa = restauranteRepository.findAll();
		List<RestauranteDTO> restaurantes = new ArrayList<RestauranteDTO>();
		
		if (!restaurantesPesquisa.isEmpty()) {
			restaurantesPesquisa.forEach(r -> {
				RestauranteDTO restaurante = new RestauranteDTO(r.getId(), r.getNome());
				restaurantes.add(restaurante);
			});
		}
		
		return restaurantes;
	}

}
