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

import com.apiGrupo2.g2.dto.ProdutoDTO;
import com.apiGrupo2.g2.dto.ProdutoRequestAtualizarDTO;
import com.apiGrupo2.g2.dto.ProdutoRequestCadastroDTO;
import com.apiGrupo2.g2.dto.ProdutoResponseDTO;
import com.apiGrupo2.g2.exceptions.MyEntityNotFoundException;
import com.apiGrupo2.g2.services.ProdutoService;


@RestController
@RequestMapping("/produto")
public class ProdutoController {
	
	@Autowired
	ProdutoService produtoService;
	
	@GetMapping("/contar")
	public Integer getContar() {
		return produtoService.getContar();
	}
	
	@PostMapping("/salvar")
	public ResponseEntity<ProdutoResponseDTO> salvar (@RequestBody ProdutoRequestCadastroDTO pdtReqDTO) throws MyEntityNotFoundException {
		return ResponseEntity.ok(produtoService.salvar(pdtReqDTO));
	}
	
	@GetMapping("/listar")
	public ResponseEntity<List<ProdutoDTO>> listar(){
		return ResponseEntity.ok(produtoService.listar());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoDTO> acharId(@PathVariable Integer id) throws MyEntityNotFoundException {
		return ResponseEntity.ok(produtoService.acharId(id));
	}
	
	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<ProdutoDTO>> listarProdutosPorUsuario(@PathVariable Integer usuarioId) throws MyEntityNotFoundException {
		return ResponseEntity.ok(produtoService.listarProdutosPorUsuario(usuarioId));
	}
	
//	@DeleteMapping("/delete/{id}")
//	public void apagar(@PathVariable Integer id) {
//		produtoService.apagar(id);
//	}
	
	@DeleteMapping("/deletarLogico/{id}")
	public ResponseEntity<Void> deletarLogico(@PathVariable Integer id) throws MyEntityNotFoundException {
		produtoService.deletarLogico(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<ProdutoDTO> atualizar(@PathVariable Integer id, @RequestBody ProdutoRequestAtualizarDTO objetoProduto) throws MyEntityNotFoundException { 
		 return ResponseEntity.ok(produtoService.atualizar(id, objetoProduto));
	
	}
}
