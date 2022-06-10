package com.pederapido.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pederapido.model.ItemCardapio;
import com.pederapido.model.Restaurante;

@Repository
public interface ItemCardapioRepository extends JpaRepository<ItemCardapio, Long> {
	
	List<ItemCardapio> findByRestaurante(Restaurante restaurante);

}
