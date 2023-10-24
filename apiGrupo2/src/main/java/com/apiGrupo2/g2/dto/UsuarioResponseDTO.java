package com.apiGrupo2.g2.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.apiGrupo2.g2.entities.Endereco;
import com.apiGrupo2.g2.entities.Role;
import com.apiGrupo2.g2.entities.Usuario;

public class UsuarioResponseDTO {

	private Integer id;
	private String nomeUsuario;
	private String email;
	private Set<String> roles = new HashSet();
	private String password;
	private String nome;
	private String telefone;
	private String celular;
	private String cpf;
	private LocalDate dataNascimento;
	private List<EnderecoDTO> enderecos = new ArrayList<>();

	public UsuarioResponseDTO() {
		super();
	}

	public UsuarioResponseDTO(Usuario usuario) {
		this.nomeUsuario = usuario.getNomeUsuario();
		this.email = usuario.getEmail();
		this.password = usuario.getPassword();
		this.nome = usuario.getNome();
		this.telefone = usuario.getTelefone();
		this.celular = usuario.getCelular();
		this.cpf = usuario.getCpf();
		this.id = usuario.getId();
		this.dataNascimento = usuario.getDataNascimento();
		for (Role role: usuario.getRoles()) {
			this.roles.add(role.getName().getTipo());
		}
//		this.cep = usuario.getEnderecos().size() > 0 ? usuario.getEnderecos().get(0).getCep() : "";//if de 1 linha./
//		this.numero = usuario.getEnderecos().size() > 0 ? usuario.getEnderecos().get(0).getNumero(): "";

		for (Endereco endereco : usuario.getEnderecos()) {
			EnderecoDTO enderecoNew = new EnderecoDTO(endereco.getCep(), endereco.getComplemento(),
					endereco.getNumero());
			enderecos.add(enderecoNew);
		}
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

	public List<EnderecoDTO> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<EnderecoDTO> enderecos) {
		this.enderecos = enderecos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
