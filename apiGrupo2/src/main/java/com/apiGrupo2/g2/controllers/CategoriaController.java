package com.apiGrupo2.g2.controllers;

import java.util.List;

import javax.mail.MessagingException;

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

import com.apiGrupo2.g2.dto.CategoriaRequestCadastroDTO;
import com.apiGrupo2.g2.dto.CategoriaResponseDTO;
import com.apiGrupo2.g2.entities.Categoria;
import com.apiGrupo2.g2.services.CategoriaService;
import com.apiGrupo2.g2.services.EmailService;

import exceptions.MyEntityNotFoundException;

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
	public Integer getContar() throws MessagingException {
		// emailService.envioEmailCadastro();
		emailService.envioEmailTeste();
		return categoriaService.getContar();
	}

	@PostMapping("/salvar")
	public CategoriaResponseDTO salvar(@RequestBody CategoriaRequestCadastroDTO catReqDTO) {
		return categoriaService.salvar(catReqDTO);
	}

	@GetMapping("/listar")
	public ResponseEntity<List<CategoriaResponseDTO>> listar() {
		return ResponseEntity.ok(categoriaService.listar());
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoriaResponseDTO> acharId(@PathVariable Integer id) {
		CategoriaResponseDTO categoriaDTO = categoriaService.acharId(id);
		if (categoriaDTO == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(categoriaDTO);

	}

//	@DeleteMapping("/delete/{id}")
//	public void apagar(@PathVariable Integer id) {
//		categoriaService.apagar(id);
//	}

	@DeleteMapping("/deletarLogico/{id}")
	public ResponseEntity<Void> deletarLogico(@PathVariable Integer id) throws MyEntityNotFoundException {
		boolean deletarLogico = categoriaService.deletarLogico(id);
		if (deletarLogico) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/atualizar/{id}")
	public ResponseEntity<CategoriaResponseDTO> atualizar(@PathVariable Integer id,
			@RequestBody CategoriaRequestCadastroDTO objetoCategoria) {
		CategoriaResponseDTO categoriaDTO = categoriaService.atualizar(id, objetoCategoria);
		if (categoriaDTO == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(categoriaDTO);
	}

}
