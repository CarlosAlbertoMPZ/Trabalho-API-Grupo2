package com.apiGrupo2.g2.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="pedido_produto")
public class PedidoProduto {
	
	@EmbeddedId
	private ProdutoPedidoPK id = new ProdutoPedidoPK();
	
	@Column(name = "quantidade_produto")
	private Integer quantidadeProduto;
	
	public PedidoProduto() {}
	
	public PedidoProduto (Pedido pedido, Produto produto, Integer quantidadeProduto) {
		this.id.setPedido(pedido);
		this.id.setProduto(produto);
		this.quantidadeProduto = quantidadeProduto;
	}

	public ProdutoPedidoPK getId() {
		return id;
	}

	public void setId(ProdutoPedidoPK id) {
		this.id = id;
	}

	public Integer getQuantidadeProduto() {
		return quantidadeProduto;
	}

	public void setQuantidadeProduto(Integer quantidadeProduto) {
		this.quantidadeProduto = quantidadeProduto;
	}
	
}
