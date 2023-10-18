package com.apiGrupo2.g2.controllers;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apiGrupo2.g2.entities.Categoria;
import com.apiGrupo2.g2.services.CategoriaService;
import com.apiGrupo2.g2.services.EmailService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	private EmailService emailService;
    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }
	
	@Autowired
	CategoriaService categoriaService;
	
	@GetMapping("/contar")
	public Integer getContar() throws MessagingException{
		emailService.envioEmailCadastro();
		return categoriaService.getContar();
	}
	
	@PostMapping("/salvar")
	public Categoria salvar (@RequestBody Categoria objetoCategoria) {
		return categoriaService.salvar(objetoCategoria);
	}
	
	@GetMapping("/listar")
	public List <Categoria> listar(){
		return categoriaService.listar();
	}
	
	@GetMapping("/{id}")
	public Categoria acharId(@PathVariable Integer id) {
		return categoriaService.acharId(id);
	}
	
//	@DeleteMapping("/delete/{id}")
//	public void apagar(@PathVariable Integer id) {
//		categoriaService.apagar(id);
//	}
	
	@DeleteMapping("/deletarLogico/{id}")
	public void deletarLogico(@PathVariable Integer id) {
		categoriaService.deletarLogico(id);
	}
	
	@PutMapping("/atualizar/{id}")
	public Categoria atualizar(@PathVariable Integer id, @RequestBody Categoria objetoCategoria) { 
		 return categoriaService.atualizar(id, objetoCategoria);
	
	}
}
