package com.apiGrupo2.g2.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_produto")
	private Integer id;
	
	@NotNull
	@Size(max=100)
	@Column(name="nome")
	private String nome;
		
	@Size(max=100)
	@Column(name="descricao")
	private String descricao;
	
	@NotNull
	@Column(name="dataFabricacao_produto")
	private LocalDateTime dataFabricacao;
	
	@NotNull
	@Column(name="quantidade_estoque")
	private Integer quantidadeEstoque;
	
	@NotNull
	@Column(name="valor_unitario")
	private Double valorUnitario;
	
	@Column(name="ativo")
	private Boolean ativo;
	
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name="id_categoria")// ref nome da FK vinda de categoria dentro da tabela Produto no BD
	private Categoria categoria;
	
	public Produto() {
		super();
	}

	
	public Produto(Integer id, @NotNull @Size(max = 100) String nome, @Size(max = 100) String descricao,
			@NotNull LocalDateTime dataFabricacao, @NotNull @Size(max = 20) Integer quantidadeEstoque,
			@NotNull @Size(max = 20) Double valorUnitario, Boolean ativo, Usuario usuario, Categoria categoria) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.dataFabricacao = dataFabricacao;
		this.quantidadeEstoque = quantidadeEstoque;
		this.valorUnitario = valorUnitario;
		this.ativo = ativo;
		this.usuario = usuario;
		this.categoria = categoria;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public Categoria getCategoria() {
		return categoria;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
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

	
	public LocalDateTime getDataFabricacao() {
		return dataFabricacao;
	}

	public void setDataFabricacao(LocalDateTime dataFabricacao) {
		this.dataFabricacao = dataFabricacao;
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


	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", dataFabricacao="
				+ dataFabricacao + ", quantidadeEstoque=" + quantidadeEstoque + ", valorUnitario=" + valorUnitario
				+ ", ativo=" + ativo + ", usuario=" + usuario + ", categoria=" + categoria + "]";
	}

		
}
