package com.apiGrupo2.g2.dto;

public class CategoriaRequestCadastroDTO {

	private String nome;

	private String descricao;

	public CategoriaRequestCadastroDTO(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
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

}
