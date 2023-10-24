package com.apiGrupo2.g2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apiGrupo2.g2.dto.PedidoRequestCadastroDTO;
import com.apiGrupo2.g2.dto.PedidoResponseCadastroDTO;
import com.apiGrupo2.g2.exceptions.MyEntityNotFoundException;
import com.apiGrupo2.g2.services.PedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
	
	@Autowired
	PedidoService pedidoService;
	
	@GetMapping("/contar")
	public Integer getContar() {
		return pedidoService.getContar();
	}
	
	@PostMapping("/salvar")
	public ResponseEntity<PedidoResponseCadastroDTO> salvar (@RequestBody PedidoRequestCadastroDTO objetoPedido) throws Exception {//PedidoDTO
		PedidoResponseCadastroDTO pedido = pedidoService.salvar(objetoPedido);
		return ResponseEntity.ok(pedido);
	}
	@GetMapping("/listar")
	public ResponseEntity<List <PedidoResponseCadastroDTO>> listar(){
		return ResponseEntity.ok(pedidoService.listar());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PedidoResponseCadastroDTO> acharId(@PathVariable Integer id) {
		PedidoResponseCadastroDTO pedidoDTO = pedidoService.acharId(id);
		if (pedidoDTO == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(pedidoDTO);
	}
	
	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<PedidoResponseCadastroDTO>> listarPedidosPorUsuario(@PathVariable Integer usuarioId) {
		List<PedidoResponseCadastroDTO> pedidosDTO = pedidoService.listarPedidosPorUsuario(usuarioId);
		return ResponseEntity.ok(pedidosDTO);
	}
	
//	@DeleteMapping("/delete/{id}")
//	public void apagar(@PathVariable Integer id) {
//		pedidoService.apagar(id);
//	}
	@DeleteMapping("/deletarLogico/{id}")
	public ResponseEntity<Void> deletarLogico(@PathVariable Integer id)throws MyEntityNotFoundException {
		pedidoService.deletarLogico(id);
		return ResponseEntity.noContent().build();
	}
}
