package com.pederapido.pederapido.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pederapido.pederapido.model.Mesa;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long> {
	

}
