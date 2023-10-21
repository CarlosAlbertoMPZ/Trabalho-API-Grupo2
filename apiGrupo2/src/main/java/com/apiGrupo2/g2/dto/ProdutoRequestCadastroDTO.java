package com.apiGrupo2.g2.dto;


public class ProdutoRequestCadastroDTO {

	private String nome;

	private String descricao;

	private Integer quantidadeEstoque;

	private Double valorUnitario;

	public ProdutoRequestCadastroDTO() {
		super();
	}

	public ProdutoRequestCadastroDTO(String nome, String descricao, Integer quantidadeEstoque, Double valorUnitario) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.quantidadeEstoque = quantidadeEstoque;
		this.valorUnitario = valorUnitario;
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

}
