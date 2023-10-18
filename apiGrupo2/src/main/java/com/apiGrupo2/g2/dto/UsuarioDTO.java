package com.apiGrupo2.g2.dto;

import java.util.Set;

public class UsuarioDTO {

	private String nomeUsuario;
    private String email;
    private Set<String> roles;
    private String password;
//  private String cep;
//	private String numero;
    
    
    public UsuarioDTO() {
    	super();
    }
    
	public UsuarioDTO(String nomeUsuario, String email, Set<String> roles, String password) {
	super();
	this.nomeUsuario = nomeUsuario;
	this.email = email;
	this.roles = roles;
	this.password = password;
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
    
    
	
}
