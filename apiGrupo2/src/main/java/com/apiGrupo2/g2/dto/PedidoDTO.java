package com.apiGrupo2.g2.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PedidoDTO {

	private Integer idUsuario;
	private List<ProdutoPedidoDTO> produtoPedidoDTO;
	private LocalDateTime dataPedido;

	public PedidoDTO() {
		super();
	}

	public PedidoDTO(Integer idUsuario, List<ProdutoPedidoDTO> produtoPedidoDTO, LocalDateTime dataPedido) {
		this.idUsuario = idUsuario;
		this.produtoPedidoDTO = produtoPedidoDTO;
		this.dataPedido = dataPedido;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public List<ProdutoPedidoDTO> getProdutoPedidoDTO() {
		return produtoPedidoDTO;
	}

	public void setProdutoPedidoDTO(List<ProdutoPedidoDTO> produtoPedidoDTO) {
		this.produtoPedidoDTO = produtoPedidoDTO;
	}

	public LocalDateTime getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDateTime dataPedido) {
		this.dataPedido = dataPedido;
	}

}
