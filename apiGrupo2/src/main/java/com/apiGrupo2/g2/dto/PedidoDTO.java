package com.apiGrupo2.g2.dto;

import java.time.LocalDateTime;

public class PedidoDTO {

	private LocalDateTime dataPedido;
	private Double valorPedido;
	
	public PedidoDTO() {
		super();
	}

	public PedidoDTO(LocalDateTime dataPedido, Double valorPedido) {
		super();
		this.dataPedido = dataPedido;
		this.valorPedido = valorPedido;
	}

	public LocalDateTime getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDateTime dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Double getValorPedido() {
		return valorPedido;
	}

	public void setValorPedido(Double valorPedido) {
		this.valorPedido = valorPedido;
	}
		
}
