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

import com.apiGrupo2.g2.dto.ProdutoRequestCadastroDTO;
import com.apiGrupo2.g2.dto.ProdutoResponseDTO;
import com.apiGrupo2.g2.entities.Produto;
import com.apiGrupo2.g2.services.EmailService;
import com.apiGrupo2.g2.services.ProdutoService;


@RestController
@RequestMapping("/produto")
public class ProdutoController {
	
	private EmailService emailService;
    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

	@Autowired
	ProdutoService produtoService;
	
	@GetMapping("/contar")
	public Integer getContar() {
		return produtoService.getContar();
	}
	
	@PostMapping("/salvar")
	public ProdutoResponseDTO salvar (@RequestBody ProdutoRequestCadastroDTO pdtReqDTO) {
		emailService.envioEmailProduto(pdtReqDTO);
		return produtoService.salvar(pdtReqDTO);
	}
	
	@GetMapping("/listar")
	public List <Produto> listar(){
		return produtoService.listar();
	}
	
	@GetMapping("/{id}")
	public Produto acharId(@PathVariable Integer id) {
		return produtoService.acharId(id);
	}
	
//	@DeleteMapping("/delete/{id}")
//	public void apagar(@PathVariable Integer id) {
//		produtoService.apagar(id);
//	}
	
	@DeleteMapping("/deletarLogico/{id}")
	public void deletarLogico(@PathVariable Integer id) {
		produtoService.deletarLogico(id);
	}
	
	@PutMapping("/atualizar/{id}")
	public Produto atualizar(@PathVariable Integer id, @RequestBody Produto objetoProduto) { 
		 return produtoService.atualizar(id, objetoProduto);
	
	}
}
