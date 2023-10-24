package com.apiGrupo2.g2.services;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiGrupo2.g2.dto.CategoriaRequestCadastroDTO;
import com.apiGrupo2.g2.dto.CategoriaResponseDTO;
import com.apiGrupo2.g2.dto.ProdutoDTO;
import com.apiGrupo2.g2.dto.UsuarioResponseDTO;
import com.apiGrupo2.g2.entities.Categoria;
import com.apiGrupo2.g2.entities.Produto;
import com.apiGrupo2.g2.entities.Usuario;
import com.apiGrupo2.g2.repositories.CategoriaRepository;

import exceptions.MyEntityNotFoundException;


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
	
	public CategoriaResponseDTO acharId(Integer id) throws MyEntityNotFoundException{
		Optional<Categoria> categoriaOpt = categoriaRepository.findById(id);
				if (categoriaOpt.isEmpty()) {
					return null;
				}
				Categoria categoria = categoriaOpt.get();
				return new CategoriaResponseDTO(categoria);
	}
	
	public List<CategoriaResponseDTO> listar(){
		List<Categoria> categorias = categoriaRepository.findAll();
		List<CategoriaResponseDTO> categoriasDTO = new ArrayList<>();
		for(Categoria categoria: categorias) {
			CategoriaResponseDTO categoriaDTO = new CategoriaResponseDTO(categoria);
			categoriasDTO.add(categoriaDTO);
		}
		return categoriasDTO;
	}
//	public void apagar(Integer id) {
//	 pedidoRepository.deleteById(id);
//}
	
	public boolean deletarLogico(Integer id) {
		Optional<Categoria> categoriaOpt = categoriaRepository.findById(id);
		if (categoriaOpt.isPresent()) {
			Categoria categoria = categoriaOpt.get();
			categoria.setAtivo(false);
			categoriaRepository.save(categoria);
			return true;
		} else {
			return false;
		}
	}
	public CategoriaResponseDTO atualizar(Integer id, CategoriaRequestCadastroDTO objetoCategoria) {
		Optional<Categoria> categoriaOpt = categoriaRepository.findById(id);
		if (categoriaOpt.isEmpty()) {
			return null;
		}
		
		Categoria categoriaBD = categoriaOpt.get();
		
								
		if (objetoCategoria.getNome()!=null) {
			categoriaBD.setNome(objetoCategoria.getNome());
		}
		
		if (objetoCategoria.getDescricao()!=null) {
			categoriaBD.setDescricao(objetoCategoria.getDescricao());
		}
												
		categoriaBD = categoriaRepository.save(categoriaBD);
		return new CategoriaResponseDTO(categoriaBD);
	}
}
