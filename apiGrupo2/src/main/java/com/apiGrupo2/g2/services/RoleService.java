package com.apiGrupo2.g2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiGrupo2.g2.entities.Role;
import com.apiGrupo2.g2.repositories.RoleRepository;

@Service
public class RoleService {
	@Autowired
	RoleRepository roleRepository;
	
	public Role save(Role role) {
		return roleRepository.save(role);
	}
}
