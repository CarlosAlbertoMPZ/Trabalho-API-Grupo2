package com.apiGrupo2.g2.entities;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="categoria")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_categoria")
	private Integer id;
	
	@NotNull
	@Size(max=100)
	@Column(name="nome")
	private String nome;
	
	@Size(max=100)
	@Column(name="descricao")
	private String descricao;
		
	@Column(name="ativo")
	private Boolean ativo;
	
	@OneToMany(mappedBy = "categoria")//como a classe que eu estou vai se referenciar dentro da classe produto
	private List<Produto> produtos;
	
	public Categoria() {
		super();
	}

	
	public Categoria(Integer id, @NotNull @Size(max = 100) String nome, @Size(max = 100) String descricao,
			Boolean ativo, List<Produto> produtos) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.ativo = ativo;
		this.produtos = produtos;
	}
	

	public List<Produto> getProdutos() {
		return produtos;
	}


	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
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



	public Boolean getAtivo() {
		return ativo;
	}


	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}


	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", ativo=" + ativo
				+ ", produtos=" + produtos + "]";
	}
		
	
}

