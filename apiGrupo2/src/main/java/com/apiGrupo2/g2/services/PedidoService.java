package com.apiGrupo2.g2.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiGrupo2.g2.dto.PedidoDTO;
import com.apiGrupo2.g2.dto.ProdutoPedidoDTO;
import com.apiGrupo2.g2.entities.Pedido;
import com.apiGrupo2.g2.entities.Produto;
import com.apiGrupo2.g2.entities.Usuario;
import com.apiGrupo2.g2.repositories.PedidoRepository;
import com.apiGrupo2.g2.repositories.ProdutoRepository;
import com.apiGrupo2.g2.repositories.UsuarioRepository;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	private EmailService emailService;
    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }
	
	public Integer getContar() {
		return pedidoRepository.contar();
	}
	public Pedido salvar(PedidoDTO pedidoDTO) throws Exception {
		// verificando se o usuario existe
		Optional<Usuario> usuarioOpt = usuarioRepository.findById(pedidoDTO.getIdUsuario());
		if (usuarioOpt.isEmpty()) {
			throw new Exception("Usuario não cadastrado"); // criar exceção
		}
		
		Pedido pedido = new Pedido();
		pedido.setUsuario(usuarioOpt.get());
		pedido.setAtivo(true);
		pedido.setDataPedido(LocalDateTime.now());
		
		Double valorTotalPedido = 0.0;
		for(ProdutoPedidoDTO produtoPedidoDTO: pedidoDTO.getProdutoPedidoDTO()) {
			// verificando se o produto existe
			Optional<Produto> produtoOpt = produtoRepository.findById(produtoPedidoDTO.getIdProduto());
			if (produtoOpt.isEmpty()) {
				throw new Exception("Produto não cadastrado"); // criar exceção
			}
			Produto produto = produtoOpt.get();
			
			pedido.getProdutos().add(produto);
			// calculando o valor do produto * quantidade e somando no total
			valorTotalPedido += produto.getValorUnitario() * produtoPedidoDTO.getQuantidade();
		}
		pedido.setValorPedido(valorTotalPedido);
		
		pedidoRepository.save(pedido);
		
		emailService.envioEmailPedido(pedido);
		
		return pedido;
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
		
		if (objetoPedido.getValorPedido()!=null) {
			registroAntigo.setValorPedido(objetoPedido.getValorPedido());
		}
		
		if (objetoPedido.getUsuario()!=null) {
		registroAntigo.setUsuario(objetoPedido.getUsuario());
		}
								
		registroAntigo.setId(id);
		return pedidoRepository.save(registroAntigo);
	}
}
