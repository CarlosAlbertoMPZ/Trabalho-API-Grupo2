package com.apiGrupo2.g2.dto;

import java.util.List;


import com.apiGrupo2.g2.entities.Produto;

public class CategoriaRequestCadastroDTO {

	private String nome;

	private String descricao;

	private Boolean ativo;

	private List<Produto> produtos;

	public CategoriaRequestCadastroDTO() {
		super();
	}

	public CategoriaRequestCadastroDTO(String nome, String descricao, Boolean ativo, List<Produto> produtos) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.ativo = ativo;
		this.produtos = produtos;
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

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

}
