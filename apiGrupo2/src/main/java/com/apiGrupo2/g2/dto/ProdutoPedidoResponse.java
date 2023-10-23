package com.apiGrupo2.g2.dto;

public class ProdutoPedidoResponse {

	private Integer idProduto;

	private Integer quantidade;

	private Double valorUnitario;

	public ProdutoPedidoResponse() {
		super();
	}

	public ProdutoPedidoResponse(Integer idProduto, Integer quantidade, Double valorUnitario) {
		super();
		this.idProduto = idProduto;
		this.quantidade = quantidade;
		this.valorUnitario = valorUnitario;
	}

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

}
