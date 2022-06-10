package com.pederapido.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pederapido.model.Mesa;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long> {
	
	Optional<Mesa> findById(Long id);	

}
