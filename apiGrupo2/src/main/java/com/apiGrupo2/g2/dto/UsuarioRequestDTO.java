package com.apiGrupo2.g2.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.apiGrupo2.g2.entities.Endereco;
import com.apiGrupo2.g2.entities.Role;
import com.apiGrupo2.g2.entities.Usuario;

public class UsuarioRequestDTO {

	private String nomeUsuario;
	private String email;
	private String password;
	private String nome;
	private String telefone;
	private String celular;

	public UsuarioRequestDTO() {
	}

	public UsuarioRequestDTO(String nomeUsuario, String email, String password, String nome, String telefone,
			String celular) {
		super();
		this.nomeUsuario = nomeUsuario;
		this.email = email;
		this.password = password;
		this.nome = nome;
		this.telefone = telefone;
		this.celular = celular;
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

}
