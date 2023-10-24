package com.apiGrupo2.g2.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiGrupo2.g2.dto.PedidoRequestCadastroDTO;
import com.apiGrupo2.g2.dto.PedidoResponseCadastroDTO;
import com.apiGrupo2.g2.dto.ProdutoPedidoDTO;
import com.apiGrupo2.g2.dto.ProdutoPedidoResponse;
import com.apiGrupo2.g2.entities.Pedido;
import com.apiGrupo2.g2.entities.PedidoProduto;
import com.apiGrupo2.g2.entities.Produto;
import com.apiGrupo2.g2.entities.Usuario;
import com.apiGrupo2.g2.exceptions.MyEntityNotFoundException;
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
			throw new MyEntityNotFoundException("Usuario não cadastrado");
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
				throw new MyEntityNotFoundException("Produto não cadastrado");
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
		
		return pedidoResponseCadastroDTO;
	}
	public PedidoResponseCadastroDTO acharId(Integer id) throws MyEntityNotFoundException {
		Pedido pedido = verificarSePedidoExiste(id);
		return new PedidoResponseCadastroDTO(pedido);
	}

	private Pedido verificarSePedidoExiste(Integer id) {
		Optional<Pedido> pedidoOpt = pedidoRepository.findById(id);
		if (pedidoOpt.isEmpty()) {
			throw new MyEntityNotFoundException("Pedido não cadastrado");
		}
		return pedidoOpt.get();
	}
	
	public List<PedidoResponseCadastroDTO> listar(){
		List<Pedido> pedidos = pedidoRepository.findAll();
		List<PedidoResponseCadastroDTO> pedidosDTO = new ArrayList<>();
		for(Pedido pedido: pedidos) {
			PedidoResponseCadastroDTO pedidoDTO = new PedidoResponseCadastroDTO(pedido);
			pedidosDTO.add(pedidoDTO);
		}
		return pedidosDTO;
	}
//	public void apagar(Integer id) {
//	 pedidoRepository.deleteById(id);
//}
	public void deletarLogico(Integer id) {
		Pedido pedido = verificarSePedidoExiste(id);
		pedido.setAtivo(false);
		pedidoRepository.save(pedido);
	}

	public List<PedidoResponseCadastroDTO> listarPedidosPorUsuario(Integer usuarioId) {
		List<Pedido> pedidos = pedidoRepository.findByUsuarioId(usuarioId);
		List<PedidoResponseCadastroDTO> pedidosDTO = new ArrayList<>();
		for(Pedido pedido: pedidos) {
			PedidoResponseCadastroDTO pedidoDTO = new PedidoResponseCadastroDTO(pedido);
			pedidosDTO.add(pedidoDTO);
		}
		return pedidosDTO;
	}

}
