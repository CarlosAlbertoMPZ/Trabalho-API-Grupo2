package com.apiGrupo2.g2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.apiGrupo2.g2.entities.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository <Produto, Integer>{

	@Query(value = "select count(*) from produto", nativeQuery = true)
	public Integer contar();
}
