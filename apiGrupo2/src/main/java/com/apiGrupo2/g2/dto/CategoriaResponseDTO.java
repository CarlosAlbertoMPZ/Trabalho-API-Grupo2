package com.apiGrupo2.g2.dto;

import java.util.ArrayList;
import java.util.List;

import com.apiGrupo2.g2.entities.Categoria;
import com.apiGrupo2.g2.entities.Produto;

public class CategoriaResponseDTO {

	private Integer id;
	
	private String nome;
	
	private String descricao;
	
	private Boolean ativo;
	
	private List<ProdutoDTO> produtos = new ArrayList<>();
	
	public CategoriaResponseDTO() {}
	
	public CategoriaResponseDTO(Categoria categoria) {
		this.id = categoria.getId();
		this.nome = categoria.getNome();
		this.descricao = categoria.getDescricao();
		this.ativo = categoria.getAtivo();
		
		for (Produto produto: categoria.getProdutos()) {//
			produtos.add(new ProdutoDTO(produto));
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public List<ProdutoDTO> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoDTO> produtos) {
		this.produtos = produtos;
	}
	
}
