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

import com.apiGrupo2.g2.entities.Pedido;
import com.apiGrupo2.g2.services.EmailService;
import com.apiGrupo2.g2.services.PedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
	
	private EmailService emailService;
    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

	@Autowired
	PedidoService pedidoService;
	
	@GetMapping("/contar")
	public Integer getContar() {
		return pedidoService.getContar();
	}
	
	@PostMapping("/salvar")
	public Pedido salvar (@RequestBody Pedido objetoPedido) {//PedidoDTO
		emailService.envioEmailPedido(objetoPedido);
		return pedidoService.salvar(objetoPedido);
	}
	@GetMapping("/listar")
	public List <Pedido> listar(){
		return pedidoService.listar();
	}
	
	@GetMapping("/{id}")
	public Pedido acharId(@PathVariable Integer id) {
		return pedidoService.acharId(id);
	}
	
//	@DeleteMapping("/delete/{id}")
//	public void apagar(@PathVariable Integer id) {
//		pedidoService.apagar(id);
//	}
	@DeleteMapping("/deletarLogico/{id}")
	public void deletarLogico(@PathVariable Integer id) {
		pedidoService.deletarLogico(id);
	}
	@PutMapping("/atualizar/{id}")
	public Pedido atualizar(@PathVariable Integer id, @RequestBody Pedido objetoPedido) { 
		 return pedidoService.atualizar(id, objetoPedido);
	
	}
}
