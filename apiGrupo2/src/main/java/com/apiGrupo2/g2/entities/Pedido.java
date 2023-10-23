package com.apiGrupo2.g2.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pedido")
	private Integer id;

	@NotNull
	@Column(name = "data_hora_pedido")
	// @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataPedido;

	@Column(name = "ativo")
	private Boolean ativo;

	@Column(name = "Valor_Pedido")
	private Double valorPedido;

	@OneToMany(mappedBy = "id.pedido", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<PedidoProduto> pedidoProduto;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	public Pedido() {
		super();
	}

	public Pedido(Integer id, @NotNull LocalDateTime dataPedido, Boolean ativo, Double valorPedido,
			List<PedidoProduto> pedidoProduto, Usuario usuario) {
		super();
		this.id = id;
		this.dataPedido = dataPedido;
		this.ativo = ativo;
		this.valorPedido = valorPedido;
		this.pedidoProduto = pedidoProduto;
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Double getValorPedido() {
		return valorPedido;
	}

	public void setValorPedido(Double valorPedido) {
		this.valorPedido = valorPedido;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public List<PedidoProduto> getPedidoProduto() {
		return pedidoProduto;
	}

	public void setPedidoProduto(List<PedidoProduto> pedidoProduto) {
		this.pedidoProduto = pedidoProduto;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", dataPedido=" + dataPedido + ", ativo=" + ativo + ", valorPedido=" + valorPedido
				+ ", pedidoProduto=" + pedidoProduto + ", usuario=" + usuario + "]";
	}

}
