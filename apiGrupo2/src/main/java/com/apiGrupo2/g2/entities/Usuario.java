package com.apiGrupo2.g2.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_usuario")
	private Integer id;
	
	@NotNull
	@Size(max=100)
	@Column(name="nome")
	private String nome;

	@Size(max=20)
	@Column(name="telefone")
	private String telefone;

	@Size(max=20)
	@Column(name="calular")
	private String celular;

	@NotNull
	@Size(max=100)
	@Column(name="nome_usuario")
	private String nomeUsuario;

	@NotNull
	@Size(max=100)
	@Column(name="email")
	private String email;

	@Size(max=20)
	@Column(name="cpf")
	private String cpf;

	@Column(name="data_nascimento")
	private LocalDate dataNascimento;

	@NotNull
	@Size(max=20)
	@Column(name="senha_usuario")
	private String senha;

	@Column(name="ativo")
	private Boolean ativo;

	//private List<String> roles; // OBS String virar enum

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)//nome da tabela. Cascade serve para propagar os comandos do banco para os filhos do usuario. 
	// @JsonManagedReference - evitar o lupping infinito....
	private List<Endereco> enderecos;

	@OneToMany(mappedBy = "usuario")
	private List<Pedido> pedidos;
	
	@OneToMany(mappedBy = "usuario")
	private List<Produto> produtos;
	
	
	public Usuario() {
		super();
	}
	
	
	public Usuario(Integer id, @NotNull @Size(max = 100) String nome, @Size(max = 20) String telefone,
			@Size(max = 20) String celular, @NotNull @Size(max = 100) String nomeUsuario,
			@NotNull @Size(max = 100) String email, @Size(max = 20) String cpf,
			LocalDate dataNascimento, @NotNull @Size(max = 20) String senha, Boolean ativo,
			List<Endereco> enderecos, List<Pedido> pedidos, List<Produto> produtos) {
		super();
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.celular = celular;
		this.nomeUsuario = nomeUsuario;
		this.email = email;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.senha = senha;
		this.ativo = ativo;
		this.enderecos = enderecos;
		this.pedidos = pedidos;
		this.produtos = produtos;
	}


	public List<Pedido> getPedidos() {
		return pedidos;
	}


	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

//	public List<String> getRoles() {
//		return roles;
//	}
//
//	public void setRoles(List<String> roles) {
//		this.roles = roles;
//	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}


	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", telefone=" + telefone + ", celular=" + celular
				+ ", nomeUsuario=" + nomeUsuario + ", email=" + email + ", cpf=" + cpf + ", dataNascimento="
				+ dataNascimento + ", senha=" + senha + ", ativo=" + ativo + ", enderecos=" + enderecos + ", pedidos="
				+ pedidos + ", produtos=" + produtos + "]";
	}

			
}