package com.apiGrupo2.g2.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.apiGrupo2.g2.entities.Pedido;
import com.apiGrupo2.g2.entities.PedidoProduto;

public class PedidoResponseCadastroDTO {

	private Integer idPedido;
	private LocalDateTime dataPedido;
	private Boolean ativo;
	private String cpfUsuario;
	private Double valorTotalPedido;
	List<ProdutoPedidoResponse> produtosPedidosResponse = new ArrayList<>();

	public PedidoResponseCadastroDTO() {
		super();
	}

	public PedidoResponseCadastroDTO(Pedido pedido) {
		this.idPedido = pedido.getId();
		this.dataPedido = pedido.getDataPedido();
		this.ativo = pedido.getAtivo();
		this.cpfUsuario = pedido.getUsuario().getCpf();
		this.valorTotalPedido = pedido.getValorPedido();
		
		for (PedidoProduto pedidoProduto: pedido.getPedidoProduto()) {
			ProdutoPedidoResponse produtoPedidoResponse = new ProdutoPedidoResponse();
			produtoPedidoResponse.setIdProduto(pedidoProduto.getId().getProduto().getId());
			produtoPedidoResponse.setQuantidade(pedidoProduto.getQuantidadeProduto());
			produtoPedidoResponse.setValorUnitario(pedidoProduto.getId().getProduto().getValorUnitario());
			produtosPedidosResponse.add(produtoPedidoResponse);
		}
		
	}

	public Double getValorTotalPedido() {
		return valorTotalPedido;
	}

	public void setValorTotalPedido(Double valorTotalPedido) {
		this.valorTotalPedido = valorTotalPedido;
	}

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public LocalDateTime getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDateTime dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getCpfUsuario() {
		return cpfUsuario;
	}

	public void setCpfUsuario(String cpfUsuario) {
		this.cpfUsuario = cpfUsuario;
	}

	public List<ProdutoPedidoResponse> getProdutosPedidosResponse() {
		return produtosPedidosResponse;
	}

	public void setProdutosPedidosResponse(List<ProdutoPedidoResponse> produtosPedidosResponse) {
		this.produtosPedidosResponse = produtosPedidosResponse;
	}

}
