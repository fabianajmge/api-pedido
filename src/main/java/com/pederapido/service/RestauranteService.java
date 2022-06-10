package com.pederapido.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pederapido.data.RestauranteDTO;
import com.pederapido.model.Mesa;
import com.pederapido.model.Restaurante;
import com.pederapido.repository.MesaRepository;
import com.pederapido.repository.RestauranteRepository;

@Service
public class RestauranteService {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private MesaRepository mesaRepository;
	
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
	
	public RestauranteDTO getRestauranteMesa(Long idMesa) {
		Optional<Mesa> mesa = mesaRepository.findById(idMesa);
		RestauranteDTO restaurante = null;
		
		if (mesa.isPresent()) {
			restaurante = new RestauranteDTO(mesa.get().getRestaurante().getId()
					, mesa.get().getRestaurante().getNome());
		}
		
		return restaurante;
	}

}
