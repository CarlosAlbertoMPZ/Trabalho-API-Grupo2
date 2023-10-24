package com.apiGrupo2.g2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.apiGrupo2.g2.entities.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository <Pedido, Integer >{

	@Query(value = "select count(*) from pedido", nativeQuery = true)
	public Integer contar();
	
	List<Pedido> findByUsuarioId(Integer usuarioId);
}
