package com.apiGrupo2.g2.repositories;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.apiGrupo2.g2.entities.Role;
import com.apiGrupo2.g2.enums.TipoRoleEnum;


@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

	Optional<Role> findByName(TipoRoleEnum roleUser);
	
	@Query(value="select r.* from usuario u \r\n"
			+ "inner join usuario_role ur on u.id_usuario = ur.usuario_id\r\n"//u.id_usuario estava user
			+ "inner join roles r on ur.role_id = r.id\r\n"
			+ "where u.email = :email", nativeQuery=true)
	Set<Role> roles(String email);
	
}