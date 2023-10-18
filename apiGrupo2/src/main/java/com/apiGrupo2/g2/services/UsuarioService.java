package com.apiGrupo2.g2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiGrupo2.g2.entities.Usuario;
import com.apiGrupo2.g2.repositories.UsuarioRepository;



@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	public Integer getContar() {
		return usuarioRepository.contar();
	}
	public Usuario salvar(Usuario objetoUsuario) {
		return usuarioRepository.save(objetoUsuario);
	}
	public Usuario acharId(Integer id) {
		return usuarioRepository.findById(id).get();
	}
	public List<Usuario> listar(){
		return usuarioRepository.findAll();
	}
//	public void apagar(Integer id) {
//		 usuarioRepository.deleteById(id);
//	}
	public void deletarLogico(Integer id) {
		Usuario objUsuario = acharId(id);
		
		if(acharId(id) != null) {
			acharId(id).setAtivo(false);
			usuarioRepository.save(objUsuario);
		}
	}
	
	public Usuario atualizar(Integer id, Usuario objetoUsuario) {
		Usuario registroAntigo = acharId(id);
		// perguntar sobre a verificação do id se ele existe ou não
		if (objetoUsuario.getAtivo()!=null) {
			registroAntigo.setAtivo(objetoUsuario.getAtivo());
		}
		
		if (objetoUsuario.getCelular()!=null) {
			registroAntigo.setCelular(objetoUsuario.getCelular());
		}
		
		if (objetoUsuario.getTelefone()!=null) {
			registroAntigo.setTelefone(objetoUsuario.getTelefone());
		}
		
		if (objetoUsuario.getNome()!=null) {
			registroAntigo.setNome(objetoUsuario.getNome());
		}
		
		if (objetoUsuario.getNomeUsuario()!=null) {
			registroAntigo.setNomeUsuario(objetoUsuario.getNomeUsuario());
		}
		
		if (objetoUsuario.getEmail()!=null) {
			registroAntigo.setEmail(objetoUsuario.getEmail());
		}
		
		if (objetoUsuario.getCpf()!=null) {
			registroAntigo.setCpf(objetoUsuario.getCpf());
		}
		
		if (objetoUsuario.getDataNascimento()!=null) {
			registroAntigo.setDataNascimento(objetoUsuario.getDataNascimento());
		}
		
		if (objetoUsuario.getPassword()!=null) {
			registroAntigo.setPassword(objetoUsuario.getPassword());
		}
		
//		if (objetoUsuario.getRoles()!=null) {
//			registroAntigo.setRoles(objetoUsuario.getRoles());
//		}
		
		if (objetoUsuario.getEnderecos()!=null) {
			registroAntigo.setEnderecos(objetoUsuario.getEnderecos());
		}
		
		
		if (objetoUsuario.getPedidos()!=null) {
			registroAntigo.setPedidos(objetoUsuario.getPedidos());
		}
		
		if (objetoUsuario.getProdutos()!=null) {
			registroAntigo.setProdutos(objetoUsuario.getProdutos());
		}
		
		if (objetoUsuario.getRoles()!=null) {
			registroAntigo.setRoles(objetoUsuario.getRoles());
		}
				
		registroAntigo.setId(id);
		return usuarioRepository.save(registroAntigo);
	}
	public Usuario findByEmail(String email){
      return usuarioRepository.findByEmail(email).get();
  }
	
}
