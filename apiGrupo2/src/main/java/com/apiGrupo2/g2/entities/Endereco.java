package com.apiGrupo2.g2.entities;

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

import com.fasterxml.jackson.annotation.JsonProperty;

//import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "endereco")
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_endereco")
	private Integer id;

	@Column(name = "ativo")
	private Boolean ativo;

	@NotNull
	@Size(max = 20)
	@Column(name = "cep")
	private String cep;

	@Size(max = 100)
	@Column(name = "logradouro")
	private String logradouro;// logradouro

	@Size(max = 100)
	@Column(name = "complemento")
	private String complemento;

	@Size(max = 50)
	@Column(name = "bairro")
	private String bairro;

	@NotNull
	@Size(max = 20)
	@Column(name = "localidade") // localidade
	private String localidade;

	@Column(name = "numero")
	private String numero;// string

	@Column(name = "uf")
	private String uf;

//	@JsonProperty(access=JsonProperty.Access.READ_ONLY)//mapeamento no endereço
//	//1 usuario tem 1 endereço
//	@ManyToOne
//	@JoinColumn(name="id_usuario")
//	// @JsonBackReference
////	@Size(max=30)
////	@Column(name="usuario_endereco")
//	private Usuario usuario;

	public Endereco() {
		super();
	}

	public Endereco(Integer id, Boolean ativo, @NotNull @Size(max = 20) String cep, @Size(max = 100) String logradouro,
			@Size(max = 100) String complemento, @Size(max = 50) String bairro,
			@NotNull @Size(max = 20) String localidade, String numero, String uf) {
		super();
		this.id = id;
		this.ativo = ativo;
		this.cep = cep;
		this.logradouro = logradouro;
		this.complemento = complemento;
		this.bairro = bairro;
		this.localidade = localidade;
		this.numero = numero;
		this.uf = uf;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	@Override
	public String toString() {
		return "Endereco [id=" + id + ", ativo=" + ativo + ", cep=" + cep + ", logradouro=" + logradouro
				+ ", complemento=" + complemento + ", bairro=" + bairro + ", localidade=" + localidade + ", numero="
				+ numero + ", uf=" + uf + "]";
	}

}
