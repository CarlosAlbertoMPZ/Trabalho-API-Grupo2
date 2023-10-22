package com.apiGrupo2.g2.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.apiGrupo2.g2.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	@Query(value = "select count(*) from usuario", nativeQuery = true)
	public Integer contar();
	
	@Query(value="select*from usuario where cpf = :cpf",nativeQuery = true)
	public Usuario findByCpf(String cpf);
	
	Optional<Usuario> findByEmail(String email);
}
