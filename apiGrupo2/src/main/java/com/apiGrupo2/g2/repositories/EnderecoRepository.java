package com.apiGrupo2.g2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.apiGrupo2.g2.entities.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer>{

	@Query(value = "select count(*) from endereco", nativeQuery = true)
	public Integer contar();
	
	@Query(value = "select * from endereco e where e.usuario_id = :usuarioId", nativeQuery = true)
	List<Endereco> buscarPorUsuarioId(Integer usuarioId);
}
