package com.apiGrupo2.g2.dto;

import java.time.LocalDateTime;

public class ProdutoRequestCadastroDTO {

	private String nome;

	private String descricao;

	private Integer quantidadeEstoque;

	private Double valorUnitario;

	private String nomeCategoria;// idCategoria

	private String cpfUsuario;// idUsuario

	private LocalDateTime dataFabricacao;

	public ProdutoRequestCadastroDTO(String nome, String descricao, Integer quantidadeEstoque, Double valorUnitario,
			String nomeCategoria, String cpfUsuario, LocalDateTime dataFabricacao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.quantidadeEstoque = quantidadeEstoque;
		this.valorUnitario = valorUnitario;
		this.nomeCategoria = nomeCategoria;
		this.cpfUsuario = cpfUsuario;
		this.dataFabricacao = dataFabricacao;
	}

	public LocalDateTime getDataFabricacao() {
		return dataFabricacao;
	}

	public void setDataFabricacao(LocalDateTime dataFabricacao) {
		this.dataFabricacao = dataFabricacao;
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

	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public String getCpfUsuario() {
		return cpfUsuario;
	}

	public void setCpfUsuario(String cpfUsuario) {
		this.cpfUsuario = cpfUsuario;
	}

}
