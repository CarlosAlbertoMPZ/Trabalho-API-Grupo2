package com.apiGrupo2.g2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.apiGrupo2.g2.entities.Categoria;
import com.apiGrupo2.g2.entities.Usuario;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

	@Query(value = "select count(*) from categoria", nativeQuery = true)
	public Integer contar();
	
	@Query(value="select* from categoria where nome = :nome",nativeQuery = true)
	public Categoria findByNome(String nome);
	
}
