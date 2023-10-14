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

import com.apiGrupo2.g2.entities.Endereco;
import com.apiGrupo2.g2.services.EnderecoService;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

	@Autowired
	EnderecoService enderecoService;
	
	@GetMapping("/contar")
	public Integer getContar() {
		return enderecoService.getContar();
	}
	
	@PostMapping("/salvar")
	public Endereco salvar (@RequestBody Endereco objetoEndereco) {
		return enderecoService.salvar(objetoEndereco);
	}
	
	@GetMapping("/listar")
	public List <Endereco> listar(){
		return enderecoService.listar();
	}
	
	@GetMapping("/{id}")
	public Endereco acharId(@PathVariable Integer id) {
		return enderecoService.acharId(id);
	}
	
	@DeleteMapping("/deletarLogico/{id}")//perguntar......
	public void deletarLogico(@PathVariable Integer id) {//alterando o apagar para deletarLogico
		enderecoService.deletarLogico(id);
	}
	
	@PutMapping("/atualizar/{id}")
	public Endereco atualizar(@PathVariable Integer id, @RequestBody Endereco objetoEndereco) { 
		 return enderecoService.atualizar(id, objetoEndereco);
	
	}
	
}
