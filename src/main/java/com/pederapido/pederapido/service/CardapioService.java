package com.pederapido.pederapido.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pederapido.pederapido.data.ItemCardapioDTO;
import com.pederapido.pederapido.model.ItemCardapio;
import com.pederapido.pederapido.model.Restaurante;
import com.pederapido.pederapido.repository.ItemCardapioRepository;
import com.pederapido.pederapido.repository.RestauranteRepository;

@Service
public class CardapioService {
	
	@Autowired
	private ItemCardapioRepository cardapioRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	public List<ItemCardapioDTO> getCardapio(Long restauranteId){
		List<ItemCardapioDTO> itensRetorno = new ArrayList<ItemCardapioDTO>();
		Optional<Restaurante> res = restauranteRepository.findById(restauranteId);
		
		if (res.isPresent()) {
			Restaurante restaurante = restauranteRepository.findById(restauranteId).get();
			List<ItemCardapio> itens = cardapioRepository.findByRestaurante(restaurante);
			
			itens.forEach(i -> {
				ItemCardapioDTO item = new ItemCardapioDTO(i.getId(), i.getTitulo(), i.getDetalhe(), 
						i.getPreco(), i.getTipo().getValue());
				
				itensRetorno.add(item);
			});
		}
		
        return itensRetorno;
    }

}
