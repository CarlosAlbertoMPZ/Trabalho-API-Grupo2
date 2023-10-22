package com.apiGrupo2.g2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiGrupo2.g2.dto.ProdutoRequestCadastroDTO;
import com.apiGrupo2.g2.dto.ProdutoResponseDTO;
import com.apiGrupo2.g2.entities.Categoria;
import com.apiGrupo2.g2.entities.Produto;
import com.apiGrupo2.g2.entities.Usuario;
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
	
	public ProdutoResponseDTO salvar(ProdutoRequestCadastroDTO catReqDTO) {
		Produto produto = new Produto();
		produto.setAtivo(true);
		produto.setNome(catReqDTO.getNome());
		produto.setDescricao(catReqDTO.getDescricao());
		produto.setValorUnitario(catReqDTO.getValorUnitario());
		produto.setQuantidadeEstoque(catReqDTO.getQuantidadeEstoque());
		Usuario usuario = usuarioRepository.findByCpf(catReqDTO.getCpfUsuario());
		Categoria categoria = categoriaRepository.findByNome(catReqDTO.getNomeCategoria());
		
		produto.setCategoria(categoria);
		produto.setUsuario(usuario);
		
		produtoRepository.save(produto);
		
		ProdutoResponseDTO produtoRspDTO = new ProdutoResponseDTO(produto);
		
		return produtoRspDTO;
	}
	
	public Produto acharId(Integer id) {
		return produtoRepository.findById(id).get();
	}
	public List<Produto> listar(){
		return produtoRepository.findAll();
	}
//	public void apagar(Integer id) {
//	 produtoRepository.deleteById(id);
//}
	
	public void deletarLogico(Integer id) {
		Produto objProduto = acharId(id);
		
		if(acharId(id) != null) {
			acharId(id).setAtivo(false);
			produtoRepository.save(objProduto);
		}
	}
	
	public Produto atualizar(Integer id, Produto objetoProduto) {
		Produto registroAntigo = acharId(id);
		
		if (objetoProduto.getAtivo()!=null) {
			registroAntigo.setAtivo(objetoProduto.getAtivo());
		}
						
		if (objetoProduto.getNome()!=null) {
			registroAntigo.setNome(objetoProduto.getNome());
		}
		
		if (objetoProduto.getDescricao()!=null) {
			registroAntigo.setDescricao(objetoProduto.getDescricao());
		}
		
		if (objetoProduto.getDataFabricacao()!=null) {
			registroAntigo.setDataFabricacao(objetoProduto.getDataFabricacao());
		}
		
		if (objetoProduto.getQuantidadeEstoque()!=null) {
			registroAntigo.setQuantidadeEstoque(objetoProduto.getQuantidadeEstoque());
		}
		
		if (objetoProduto.getValorUnitario()!=null) {
			registroAntigo.setValorUnitario(objetoProduto.getValorUnitario());
		}
		
		if (objetoProduto.getCategoria()!=null) {
			registroAntigo.setCategoria(objetoProduto.getCategoria());
		}
		
		if (objetoProduto.getUsuario()!=null) {
			registroAntigo.setUsuario(objetoProduto.getUsuario());
		}
		
						
		registroAntigo.setId(id);
		return produtoRepository.save(registroAntigo);
	}
}
