package com.apiGrupo2.g2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apiGrupo2.g2.dto.EnderecoDTO;
import com.apiGrupo2.g2.entities.Endereco;
import com.apiGrupo2.g2.exceptions.MyEntityNotFoundException;
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
	public Endereco salvar (@RequestBody EnderecoDTO objetoEndereco) {
		return enderecoService.salvar(objetoEndereco);
	}
	
	@GetMapping("/listar")
	public ResponseEntity<List<Endereco>> listar() throws MyEntityNotFoundException {
		throw new MyEntityNotFoundException("teste");
		//return ResponseEntity.ok(enderecoService.listar());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Endereco> acharId(@PathVariable Integer id) {
		Endereco endereco = enderecoService.acharId(id);
		if (endereco == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(enderecoService.acharId(id));
	}
	
	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<Endereco>> buscarPorUsuarioId(@PathVariable Integer usuarioId) {
		List<Endereco> enderecos = enderecoService.buscarPorUsuarioId(usuarioId);
		return ResponseEntity.ok(enderecos);
	}
	
	@DeleteMapping("/deletarLogico/{id}")//perguntar......
	public ResponseEntity<Void> deletarLogico(@PathVariable Integer id) {//alterando o apagar para deletarLogico
		boolean deletarLogico = enderecoService.deletarLogico(id);
		if (deletarLogico) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Endereco> atualizar(@PathVariable Integer id, @RequestBody EnderecoDTO enderecoDTO) { 
		 Endereco endereco = enderecoService.atualizar(id, enderecoDTO);
		 if (endereco == null) {
			 return ResponseEntity.notFound().build(); 
		 }
		 return ResponseEntity.ok(endereco);
	}
	
	@PutMapping("/status/{id}")
	public ResponseEntity<Endereco> atualizarStatus(@PathVariable Integer id, @RequestBody boolean status) { 
		 Endereco endereco = enderecoService.atualizarStatus(id, status);
		 if (endereco == null) {
			 return ResponseEntity.notFound().build(); 
		 }
		 return ResponseEntity.ok(endereco);
	}
	
}
