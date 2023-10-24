package com.apiGrupo2.g2.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiGrupo2.g2.dto.ProdutoDTO;
import com.apiGrupo2.g2.dto.ProdutoRequestAtualizarDTO;
import com.apiGrupo2.g2.dto.ProdutoRequestCadastroDTO;
import com.apiGrupo2.g2.dto.ProdutoResponseDTO;
import com.apiGrupo2.g2.entities.Categoria;
import com.apiGrupo2.g2.entities.Produto;
import com.apiGrupo2.g2.entities.Usuario;
import com.apiGrupo2.g2.exceptions.MyEntityNotFoundException;
import com.apiGrupo2.g2.repositories.CategoriaRepository;
import com.apiGrupo2.g2.repositories.ProdutoRepository;
import com.apiGrupo2.g2.repositories.UsuarioRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	
	public Integer getContar() {
		return produtoRepository.contar();
	}
	
	public ProdutoResponseDTO salvar(ProdutoRequestCadastroDTO catReqDTO) throws MyEntityNotFoundException {
		Usuario usuario = usuarioRepository.findByCpf(catReqDTO.getCpfUsuario());
		if (usuario == null) {
			throw new MyEntityNotFoundException("usuario não cadastrado");
		}
		
		Produto produto = new Produto();
		produto.setAtivo(true);
		produto.setNome(catReqDTO.getNome());
		produto.setDescricao(catReqDTO.getDescricao());
		produto.setValorUnitario(catReqDTO.getValorUnitario());
		produto.setQuantidadeEstoque(catReqDTO.getQuantidadeEstoque());
		produto.setDataFabricacao(catReqDTO.getDataFabricacao());
		Categoria categoria = categoriaRepository.findByNome(catReqDTO.getNomeCategoria());
		
		produto.setCategoria(categoria);
		produto.setUsuario(usuario);
		//produto.setDataFabricacao(LocalDateTime.now());
		
		produtoRepository.save(produto);
		
		ProdutoResponseDTO produtoRspDTO = new ProdutoResponseDTO(produto);
		
		return produtoRspDTO;
	}
	
	public ProdutoDTO acharId(Integer id) throws MyEntityNotFoundException {
		Produto produto = verificarSeProdutoExiste(id);
		ProdutoDTO produtoDTO = new ProdutoDTO(produto);
		return produtoDTO;
	}
	public List<ProdutoDTO> listar(){
		List<Produto> produtos = produtoRepository.findAll();
		List<ProdutoDTO> produtosDTO = produtos.stream().map(produto -> new ProdutoDTO(produto)).collect(Collectors.toList());
		return produtosDTO;
	}
//	public void apagar(Integer id) {
//	 produtoRepository.deleteById(id);
//}
	
	public void deletarLogico(Integer id) {
		Produto produto = verificarSeProdutoExiste(id);
		produto.setAtivo(false);
		produtoRepository.save(produto);
	}

	private Produto verificarSeProdutoExiste(Integer id) throws MyEntityNotFoundException {
		Optional<Produto> produtoOpt = produtoRepository.findById(id);
		if (produtoOpt.isEmpty()) {
			throw new MyEntityNotFoundException("Produto não cadastrado");
		}
		Produto produto = produtoOpt.get();
		return produto;
	}
	
	public ProdutoDTO atualizar(Integer id, ProdutoRequestAtualizarDTO objetoProduto) throws MyEntityNotFoundException {
		Produto produto = verificarSeProdutoExiste(id);
		
		if (objetoProduto.isAtivo() != null) {
			produto.setAtivo(objetoProduto.isAtivo());
		}
						
		if (objetoProduto.getNome()!=null) {
			produto.setNome(objetoProduto.getNome());
		}
		
		if (objetoProduto.getDescricao()!=null) {
			produto.setDescricao(objetoProduto.getDescricao());
		}
		
		if (objetoProduto.getDataFabricacao()!=null) {
			produto.setDataFabricacao(objetoProduto.getDataFabricacao());
		}
		
		if (objetoProduto.getQuantidadeEstoque()!=null) {
			produto.setQuantidadeEstoque(objetoProduto.getQuantidadeEstoque());
		}
		
		if (objetoProduto.getValorUnitario()!=null) {
			produto.setValorUnitario(objetoProduto.getValorUnitario());
		}
		
		if (objetoProduto.getNomeCategoria()!=null) {
			Categoria categoria = categoriaRepository.findByNome(objetoProduto.getNomeCategoria());
			if (categoria == null) {
				throw new MyEntityNotFoundException("Categoria " + objetoProduto.getNomeCategoria() + "nao cadastrada");
			}
			produto.setCategoria(categoria);
		}
		
		produto = produtoRepository.save(produto);
		
		return new ProdutoDTO(produto);
	}

	public List<ProdutoDTO> listarProdutosPorUsuario(Integer usuarioId) {
		List<Produto> produtos = produtoRepository.findByUsuarioId(usuarioId);
		List<ProdutoDTO> produtosDTO = produtos.stream().map(produto -> new ProdutoDTO(produto)).collect(Collectors.toList());
		return produtosDTO;
	}
}
