package com.pederapido.pederapido.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pederapido.pederapido.model.Mesa;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long> {
	
	Optional<Mesa> findById(Long id);	

}
