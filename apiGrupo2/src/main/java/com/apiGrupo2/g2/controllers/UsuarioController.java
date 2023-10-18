package com.apiGrupo2.g2.controllers;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apiGrupo2.g2.config.JWTUtil;
import com.apiGrupo2.g2.dto.LoginDTO;
import com.apiGrupo2.g2.dto.UserDTO;
import com.apiGrupo2.g2.dto.UsuarioDTO;
import com.apiGrupo2.g2.entities.Endereco;
import com.apiGrupo2.g2.entities.Role;
import com.apiGrupo2.g2.entities.Usuario;
import com.apiGrupo2.g2.enums.TipoRoleEnum;
import com.apiGrupo2.g2.repositories.EnderecoRepository;
import com.apiGrupo2.g2.repositories.RoleRepository;
import com.apiGrupo2.g2.services.EnderecoService;
import com.apiGrupo2.g2.services.UsuarioService;




@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	
//	@Autowired
//	EnderecoRepository enderecoRepository;

//	@Autowired
//	EnderecoService enderecoService;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/registro")
	public Usuario cadastro(@RequestParam String email, @RequestBody UsuarioDTO usuario) {

		Set<String> strRoles = usuario.getRoles();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role usuarioRole = roleRepository.findByName(TipoRoleEnum.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Erro: Role não encontrada."));
			roles.add(usuarioRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "ADMINISTRADOR":
					Role adminRole = roleRepository.findByName(TipoRoleEnum.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Erro: Role não encontrada."));
					roles.add(adminRole);
					break;
				case "USUARIO":
					Role usuarioRole = roleRepository.findByName(TipoRoleEnum.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Erro: Role não encontrada."));
					roles.add(usuarioRole);
				}
			});
		}

//		Endereco viaCep = enderecoService.pesquisarEndereco(user.getCep());
//		Endereco enderecoNovo = new Endereco();
//		enderecoNovo.setBairro(viaCep.getBairro());
//		enderecoNovo.setCep(user.getCep());
//		enderecoNovo.setComplemento(user.getComplementoAdicional());
//		enderecoNovo.setLocalidade(viaCep.getLocalidade());
//		enderecoNovo.setLogradouro(viaCep.getLogradouro());
//		enderecoNovo.setUf(viaCep.getUf());
//		enderecoNovo.setNumero(user.getNumero());
//		enderecoRepository.save(enderecoNovo);

		Usuario usuarioResumido = new Usuario();
		usuarioResumido.setNomeUsuario(usuario.getNomeUsuario());
		usuarioResumido.setEmail(usuario.getEmail());
		usuarioResumido.setRoles(roles);
		String encodedPass = passwordEncoder.encode(usuario.getPassword());
		usuarioResumido.setPassword(encodedPass);

		// emailService.envioEmailCadastro(email, user);

		return usuarioService.salvar(usuarioResumido);
	}
	
	@PostMapping("/logar")
	public Map<String, Object> logar(@RequestBody LoginDTO body) {
		try {
			UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(
					body.getEmail(), body.getPassword());

			authManager.authenticate(authInputToken);

			Usuario usuario = usuarioService.findByEmail(body.getEmail());
			Usuario usuarioResumido = new Usuario();
			usuarioResumido.setNomeUsuario(usuario.getNomeUsuario());
			usuarioResumido.setEmail(usuario.getEmail());
			usuarioResumido.setId(usuario.getId());
			usuarioResumido.setRoles(usuario.getRoles());
			String token = jwtUtil.generateTokenWithUserData(usuarioResumido);

			return Collections.singletonMap("jwt-token", token);
		} catch (AuthenticationException authExc) {
			throw new RuntimeException("Credenciais Invalidas");
		}
	}
	
	@GetMapping("/contar")
	public Integer getContar() {
		return usuarioService.getContar();
	}
	
	@PostMapping("/salvar")
	public Usuario salvar (@RequestBody Usuario objetoUsuario) {
		return usuarioService.salvar(objetoUsuario);
	}
	@GetMapping("/listar")
	public List <Usuario> listar(){
		return usuarioService.listar();
	}
	
	@GetMapping("/{id}")
	public Usuario acharId(@PathVariable Integer id) {
		return usuarioService.acharId(id);
	}
//	@DeleteMapping("/delete/{id}")
//	public void apagar(@PathVariable Integer id) {
//		usuarioService.apagar(id);
//	}
	@DeleteMapping("/deletarLogico/{id}")//perguntar......
	public void deletarLogico(@PathVariable Integer id) {//alterando o apagar para deletarLogico
		usuarioService.deletarLogico(id);
	}
	@PutMapping("/atualizar/{id}")
	public Usuario atualizar(@PathVariable Integer id, @RequestBody Usuario objetoUsuario) { 
		 return usuarioService.atualizar(id, objetoUsuario);
	
	}
}
