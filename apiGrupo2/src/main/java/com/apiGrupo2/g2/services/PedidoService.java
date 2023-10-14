package com.apiGrupo2.g2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiGrupo2.g2.entities.Pedido;
import com.apiGrupo2.g2.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;
	
	public Integer getContar() {
		return pedidoRepository.contar();
	}
	public Pedido salvar(Pedido objetoPedido) {
		return pedidoRepository.save(objetoPedido);
	}
	public Pedido acharId(Integer id) {
		return pedidoRepository.findById(id).get();
	}
	public List<Pedido> listar(){
		return pedidoRepository.findAll();
	}
//	public void apagar(Integer id) {
//	 pedidoRepository.deleteById(id);
//}
	public void deletarLogico(Integer id) {
		Pedido objPedido = acharId(id);
		
		if(acharId(id) != null) {
			acharId(id).setAtivo(false);
			pedidoRepository.save(objPedido);
		}
	}
	
	public Pedido atualizar(Integer id, Pedido objetoPedido) {
		Pedido registroAntigo = acharId(id);
		
		if (objetoPedido.getAtivo()!=null) {
			registroAntigo.setAtivo(objetoPedido.getAtivo());
		}
						
		if (objetoPedido.getDataPedido()!=null) {
			registroAntigo.setDataPedido(objetoPedido.getDataPedido());
		}
		
		if (objetoPedido.getProdutos()!=null) {
			registroAntigo.setProdutos(objetoPedido.getProdutos());
		}
		
		if (objetoPedido.getUsuario()!=null) {
			registroAntigo.setUsuario(objetoPedido.getUsuario());
		}
								
		registroAntigo.setId(id);
		return pedidoRepository.save(registroAntigo);
	}
}
