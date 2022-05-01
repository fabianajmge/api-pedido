package com.pederapido.pederapido.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pederapido.pederapido.model.Restaurante;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
	
	Optional<Restaurante> findById(Long id);

}
