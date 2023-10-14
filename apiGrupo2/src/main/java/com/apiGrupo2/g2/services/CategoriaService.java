package com.apiGrupo2.g2.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiGrupo2.g2.entities.Categoria;
import com.apiGrupo2.g2.repositories.CategoriaRepository;


@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;
	
	public Integer getContar() {
		return categoriaRepository.contar();
	}
	
	public Categoria salvar(Categoria objetoCategoria) {
		return categoriaRepository.save(objetoCategoria);
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
