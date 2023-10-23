package com.apiGrupo2.g2.dto;

import java.util.List;

public class PedidoRequestCadastroDTO {

	private String cpfUsuario;
	List<ProdutoPedidoDTO> produtosPedidos;

	public PedidoRequestCadastroDTO() {
		super();
	}

	public PedidoRequestCadastroDTO(String cpfUsuario, List<ProdutoPedidoDTO> produtosPedidos) {
		super();
		this.cpfUsuario = cpfUsuario;
		this.produtosPedidos = produtosPedidos;
	}

	public String getCpfUsuario() {
		return cpfUsuario;
	}

	public void setCpfUsuario(String cpfUsuario) {
		this.cpfUsuario = cpfUsuario;
	}

	public List<ProdutoPedidoDTO> getProdutosPedidos() {
		return produtosPedidos;
	}

	public void setProdutosPedidos(List<ProdutoPedidoDTO> produtosPedidos) {
		this.produtosPedidos = produtosPedidos;
	}

}
