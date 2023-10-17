package com.apiGrupo2.g2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiGrupo2.g2.entities.User;
import com.apiGrupo2.g2.repositories.UserRepository;


@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User findByEmail(String email){
        return userRepository.findByEmail(email).get();
    }

    public User save(User user){
        return userRepository.save(user);
    }

	public List<User> listarTodos() {
		return userRepository.findAll();
	}
	
}
