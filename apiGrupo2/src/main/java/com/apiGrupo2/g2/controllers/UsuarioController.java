package com.apiGrupo2.g2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apiGrupo2.g2.entities.Usuario;




@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping("/contar")
	public Integer getContar() {
		return usuarioService.getContar();
	}
	
	@PostMapping("/salvar")
	public Usuario salvar (@RequestBody Usuario objetoUsuario) {
		return usuarioService.salvar(objetoUsuario);
	}
	@GetMapping("/listar")
	public List <Usuario> listar(){
		return usuarioService.listar();
	}
	
	@GetMapping("/{id}")
	public Usuario acharId(@PathVariable Integer id) {
		return usuarioService.acharId(id);
	}
//	@DeleteMapping("/delete/{id}")
//	public void apagar(@PathVariable Integer id) {
//		usuarioService.apagar(id);
//	}
	@DeleteMapping("/deletarLogico/{id}")//perguntar......
	public void deletarLogico(@PathVariable Integer id) {//alterando o apagar para deletarLogico
		usuarioService.deletarLogico(id);
	}
	@PutMapping("/atualizar/{id}")
	public Usuario atualizar(@PathVariable Integer id, @RequestBody Usuario objetoUsuario) { 
		 return usuarioService.atualizar(id, objetoUsuario);
	
	}
}
