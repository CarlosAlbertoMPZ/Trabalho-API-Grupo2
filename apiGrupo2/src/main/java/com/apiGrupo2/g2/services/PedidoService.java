package com.apiGrupo2.g2.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiGrupo2.g2.dto.PedidoDTO;
import com.apiGrupo2.g2.dto.PedidoRequestCadastroDTO;
import com.apiGrupo2.g2.dto.PedidoResponseCadastroDTO;
import com.apiGrupo2.g2.dto.ProdutoPedidoDTO;
import com.apiGrupo2.g2.dto.ProdutoPedidoResponse;
import com.apiGrupo2.g2.entities.Pedido;
import com.apiGrupo2.g2.entities.PedidoProduto;
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
	public PedidoResponseCadastroDTO salvar(PedidoRequestCadastroDTO pedidoDTO) throws Exception {
		// verificando se o usuario existe
		Usuario usuario = usuarioRepository.findByCpf(pedidoDTO.getCpfUsuario());
		if (usuario == null) {
			throw new Exception("Usuario não cadastrado"); // criar exceção
		}
		
		Pedido pedido = new Pedido();
		pedido.setUsuario(usuario);
		pedido.setAtivo(true);
		pedido.setDataPedido(LocalDateTime.now());
		
		Double valorTotalPedido = 0.0;
		List<PedidoProduto> pedidosProdutos = new ArrayList<>();
		for(ProdutoPedidoDTO produtoPedidoDTO: pedidoDTO.getProdutosPedidos()) {
			// verificando se o produto existe
			Optional<Produto> produtoOpt = produtoRepository.findById(produtoPedidoDTO.getIdProduto());
			if (produtoOpt.isEmpty()) {
				throw new Exception("Produto não cadastrado"); // criar exceção
			}
			Produto produto = produtoOpt.get();
			
			// calculando o valor do produto * quantidade e somando no total
			valorTotalPedido += produto.getValorUnitario() * produtoPedidoDTO.getQuantidade();
			
			PedidoProduto pedidoProduto = new PedidoProduto(pedido, produto, produtoPedidoDTO.getQuantidade());
			pedidosProdutos.add(pedidoProduto);
		}
		pedido.setValorPedido(valorTotalPedido);
		pedido.setPedidoProduto(pedidosProdutos);
		
		pedido = pedidoRepository.save(pedido);
		
		emailService.envioEmailPedido(pedido);
		
		PedidoResponseCadastroDTO pedidoResponseCadastroDTO = new PedidoResponseCadastroDTO(pedido);
		for (PedidoProduto pedidoProduto: pedido.getPedidoProduto()) {
			ProdutoPedidoResponse produtoPedidoResponse = new ProdutoPedidoResponse();
			produtoPedidoResponse.setIdProduto(pedidoProduto.getId().getProduto().getId());
			produtoPedidoResponse.setQuantidade(pedidoProduto.getQuantidadeProduto());
			produtoPedidoResponse.setValorUnitario(pedidoProduto.getId().getProduto().getValorUnitario());
			
			pedidoResponseCadastroDTO.getProdutosPedidosResponse().add(produtoPedidoResponse);
		}
		
		return pedidoResponseCadastroDTO;
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
		
		/*
		if (objetoPedido.getProdutos()!=null) {
			registroAntigo.setProdutos(objetoPedido.getProdutos());
		}
		*/
		
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
