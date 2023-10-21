package com.apiGrupo2.g2.dto;

import java.time.LocalDate;
import java.util.Set;

public class UsuarioDTO {

	private String nomeUsuario;
	private String email;
	private Set<String> roles;
	private String password;
	private String nome;
	private String telefone;
	private String celular;
	private String cpf;
	private LocalDate dataNascimento;
	private String cep;
	private String numero;

	public UsuarioDTO() {
		super();
	}

	public UsuarioDTO(String nomeUsuario, String email, Set<String> roles, String password, String nome,
			String telefone, String celular, String cpf, LocalDate dataNascimento, String cep, String numero) {
		super();
		this.nomeUsuario = nomeUsuario;
		this.email = email;
		this.roles = roles;
		this.password = password;
		this.nome = nome;
		this.telefone = telefone;
		this.celular = celular;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.cep = cep;
		this.numero = numero;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

}
