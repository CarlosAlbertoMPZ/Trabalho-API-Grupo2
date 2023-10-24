package com.apiGrupo2.g2.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiGrupo2.g2.dto.UsuarioRequestDTO;
import com.apiGrupo2.g2.dto.UsuarioResponseDTO;
import com.apiGrupo2.g2.entities.Usuario;
import com.apiGrupo2.g2.exceptions.MyEntityNotFoundException;
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

	public UsuarioResponseDTO acharId(Integer id) throws MyEntityNotFoundException {
		Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
		if (usuarioOpt.isEmpty()) {
			return null;
		}
		Usuario usuario = usuarioOpt.get();
		return new UsuarioResponseDTO(usuario);
	}

	public List<UsuarioResponseDTO> listar() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		List<UsuarioResponseDTO> usuariosDTO = new ArrayList<>();
		for (Usuario usuario : usuarios) {
			UsuarioResponseDTO usuarioDTO = new UsuarioResponseDTO(usuario);
			usuariosDTO.add(usuarioDTO);
		}
		return usuariosDTO;
	}

//	public void apagar(Integer id) {
//		 usuarioRepository.deleteById(id);
//	}
	public boolean deletarLogico(Integer id) {
		Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
		if (usuarioOpt.isPresent()) {
			Usuario usuario = usuarioOpt.get();
			usuario.setAtivo(false);
			usuarioRepository.save(usuario);
			return true;
		} else {
			return false;
		}

	}

	public UsuarioResponseDTO atualizar(Integer id, UsuarioRequestDTO objetoUsuario) {
		Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
		if (usuarioOpt.isEmpty()) {
			return null;
		}

		Usuario usuarioBD = usuarioOpt.get();

		if (objetoUsuario.getCelular() != null) {
			usuarioBD.setCelular(objetoUsuario.getCelular());
		}

		if (objetoUsuario.getTelefone() != null) {
			usuarioBD.setTelefone(objetoUsuario.getTelefone());
		}

		if (objetoUsuario.getNome() != null) {
			usuarioBD.setNome(objetoUsuario.getNome());
		}

		if (objetoUsuario.getNomeUsuario() != null) {
			usuarioBD.setNomeUsuario(objetoUsuario.getNomeUsuario());
		}

		if (objetoUsuario.getEmail() != null) {
			usuarioBD.setEmail(objetoUsuario.getEmail());
		}

		if (objetoUsuario.getPassword() != null) {
			usuarioBD.setPassword(objetoUsuario.getPassword());
		}

		usuarioBD = usuarioRepository.save(usuarioBD);

		return new UsuarioResponseDTO(usuarioBD);
	}

	public Usuario findByEmail(String email) {
		return usuarioRepository.findByEmail(email).get();
	}

}
