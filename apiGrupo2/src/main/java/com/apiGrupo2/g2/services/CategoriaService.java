package com.apiGrupo2.g2.services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiGrupo2.g2.dto.CategoriaRequestCadastroDTO;
import com.apiGrupo2.g2.dto.CategoriaResponseDTO;
import com.apiGrupo2.g2.dto.ProdutoDTO;
import com.apiGrupo2.g2.entities.Categoria;
import com.apiGrupo2.g2.entities.Produto;
import com.apiGrupo2.g2.repositories.CategoriaRepository;


@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;
	
	public Integer getContar() {
		return categoriaRepository.contar();
	}
	
	public CategoriaResponseDTO salvar(CategoriaRequestCadastroDTO catReqDTO) {
		Categoria categoria = new Categoria();
		categoria.setNome(catReqDTO.getNome());
		categoria.setDescricao(catReqDTO.getDescricao());
		categoria.setAtivo(true);
		categoria.setProdutos(new ArrayList<>());
		
		categoria = categoriaRepository.save(categoria);
		
		/*
		CategoriaResponseDTO categoriaRespDTO2 = new CategoriaResponseDTO();
		categoriaRespDTO2.setId(categoria.getId());
		categoriaRespDTO2.setNome(categoria.getNome());
		categoriaRespDTO2.setDescricao(categoria.getDescricao());
		categoriaRespDTO2.setAtivo(categoria.getAtivo());
		for (Produto produto: categoria.getProdutos()) {
			ProdutoDTO produtoDTO = new ProdutoDTO(produto);
			categoriaRespDTO2.getProdutos().add(produtoDTO);
		}
		*/
		CategoriaResponseDTO categoriaRespDTO = new CategoriaResponseDTO(categoria);
		return categoriaRespDTO;
	}
	
	public Categoria acharId(Integer id) {
		return categoriaRepository.findById(id).get();
	}
	
	public List<Categoria> listar(){
		return categoriaRepository.findAll();
	}
//	public void apagar(Integer id) {
//	 pedidoRepository.deleteById(id);
//}
	
	public void deletarLogico(Integer id) {
		Categoria objCategoria = acharId(id);
		
		if(acharId(id) != null) {
			acharId(id).setAtivo(false);
			categoriaRepository.save(objCategoria);
		}
	}
	public Categoria atualizar(Integer id, Categoria objetoCategoria) {
		Categoria registroAntigo = acharId(id);
		
		if (objetoCategoria.getAtivo()!=null) {
			registroAntigo.setAtivo(objetoCategoria.getAtivo());
		}
						
		if (objetoCategoria.getNome()!=null) {
			registroAntigo.setNome(objetoCategoria.getNome());
		}
		
		if (objetoCategoria.getDescricao()!=null) {
			registroAntigo.setDescricao(objetoCategoria.getDescricao());
		}
		
		if (objetoCategoria.getProdutos()!=null) {
			registroAntigo.setProdutos(objetoCategoria.getProdutos());
		}
								
		registroAntigo.setId(id);
		return categoriaRepository.save(registroAntigo);
	}
}
