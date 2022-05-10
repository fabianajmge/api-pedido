package com.pederapido.pederapido.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pederapido.pederapido.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
	Optional<Pedido> findById(Long id);
	
	@Query("SELECT p FROM Pedido p WHERE p.mesa.id = ?1 AND p.status = 2")
	List<Pedido> buscarPedidoFinalizadoPorMesa(Long idMesa);

}
