package com.apiGrupo2.g2.dto;

import java.time.LocalDateTime;

import com.apiGrupo2.g2.entities.Produto;

public class ProdutoResponseDTO {

	private Integer id;

	private String nome;

	private String descricao;

	private Integer quantidadeEstoque;

	private Double valorUnitario;

	private Boolean ativo;

	private String nomeCategoria;// idCategoria

	private String cpfUsuario;// idUsuario

	private LocalDateTime dataFabricacao;

	public ProdutoResponseDTO() {
		super();
	}

	public ProdutoResponseDTO(Integer id, String nome, String descricao, Integer quantidadeEstoque,
			Double valorUnitario, Boolean ativo, String nomeCategoria, String cpfUsuario,
			LocalDateTime dataFabricacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.quantidadeEstoque = quantidadeEstoque;
		this.valorUnitario = valorUnitario;
		this.ativo = ativo;
		this.nomeCategoria = nomeCategoria;
		this.cpfUsuario = cpfUsuario;
		this.dataFabricacao = dataFabricacao;
	}

	public ProdutoResponseDTO(Produto produto) {
		super();
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.quantidadeEstoque = produto.getQuantidadeEstoque();
		this.valorUnitario = produto.getValorUnitario();
		this.ativo = produto.getAtivo();
		this.nomeCategoria = produto.getCategoria().getNome();
		this.cpfUsuario = produto.getUsuario().getCpf();
		this.dataFabricacao = produto.getDataFabricacao();
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

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

}
