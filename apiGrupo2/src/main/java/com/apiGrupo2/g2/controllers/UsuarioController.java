package com.apiGrupo2.g2.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.apiGrupo2.g2.dto.MessageResponseDTO;
import com.apiGrupo2.g2.dto.UsuarioDTO;
import com.apiGrupo2.g2.dto.UsuarioRequestDTO;
import com.apiGrupo2.g2.dto.UsuarioResponseDTO;
import com.apiGrupo2.g2.entities.Endereco;
import com.apiGrupo2.g2.entities.Role;
import com.apiGrupo2.g2.entities.Usuario;
import com.apiGrupo2.g2.enums.TipoRoleEnum;
import com.apiGrupo2.g2.exceptions.MyEntityNotFoundException;
import com.apiGrupo2.g2.repositories.EnderecoRepository;
import com.apiGrupo2.g2.repositories.RoleRepository;
import com.apiGrupo2.g2.services.EmailService;
import com.apiGrupo2.g2.services.EnderecoService;
import com.apiGrupo2.g2.services.UsuarioService;




@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	EnderecoRepository enderecoRepository;

	@Autowired
	EnderecoService enderecoService;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/registro")
	public ResponseEntity<MessageResponseDTO> cadastro(@RequestParam String email, @Valid @RequestBody UsuarioDTO usuario) {

		Set<String> strRoles = usuario.getRoles();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role usuarioRole = roleRepository.findByName(TipoRoleEnum.ROLE_COMPRADOR)
					.orElseThrow(() -> new RuntimeException("Erro: Role não encontrada."));
			roles.add(usuarioRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "VENDEDOR":
					Role adminRole = roleRepository.findByName(TipoRoleEnum.ROLE_VENDEDOR)
							.orElseThrow(() -> new RuntimeException("Erro: Role não encontrada."));
					roles.add(adminRole);
					break;
				case "COMPRADOR":
					Role usuarioRole = roleRepository.findByName(TipoRoleEnum.ROLE_COMPRADOR)
							.orElseThrow(() -> new RuntimeException("Erro: Role não encontrada."));
					roles.add(usuarioRole);
				}
			});
		}

		Endereco viaCep = enderecoService.pesquisarEndereco(usuario.getCep());
		Endereco enderecoNovo = new Endereco();
		enderecoNovo.setBairro(viaCep.getBairro());
		enderecoNovo.setCep(usuario.getCep());
		enderecoNovo.setComplemento(viaCep.getComplemento());
		enderecoNovo.setLocalidade(viaCep.getLocalidade());
		enderecoNovo.setLogradouro(viaCep.getLogradouro());
		enderecoNovo.setNumero(usuario.getNumero());
		enderecoNovo.setUf(viaCep.getUf());
		enderecoNovo.setAtivo(true);
		

		Usuario usuarioResumido = new Usuario();
		usuarioResumido.setNomeUsuario(usuario.getNomeUsuario());
		usuarioResumido.setEmail(usuario.getEmail());
		usuarioResumido.setRoles(roles);
		String encodedPass = passwordEncoder.encode(usuario.getPassword());
		usuarioResumido.setPassword(encodedPass);
		usuarioResumido.setCelular(usuario.getCelular());
		usuarioResumido.setCpf(usuario.getCpf());
		usuarioResumido.setTelefone(usuario.getTelefone());
		usuarioResumido.setNome(usuario.getNome());
		usuarioResumido.setDataNascimento(usuario.getDataNascimento());
		usuarioResumido.setAtivo(true);
		enderecoRepository.save(enderecoNovo);
		List<Endereco> enderecos = new ArrayList<>();
		enderecos.add(enderecoNovo);
		usuarioResumido.setEnderecos(enderecos);
		
		
		usuarioService.salvar(usuarioResumido);
		emailService.envioEmailCadastro(/*email, usuario*/);
		
		return ResponseEntity.ok(new MessageResponseDTO("Parabens você finalizou o trabalho com muito custo!"));
	}//mensagem de retorno positivo
	
	@PostMapping("/logar")
	public ResponseEntity<?>logar(@RequestBody LoginDTO body) {
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

			
			MessageResponseDTO retornoLogin = new MessageResponseDTO("Parabens você finalizou o trabalho com muito custo!", token);
			return ResponseEntity.ok().body(retornoLogin);
			
		} catch (AuthenticationException authExc) {
			throw new RuntimeException("Credenciais Invalidas");
		}
	}
	
	@GetMapping("/contar")
	public Integer getContar() {
		return usuarioService.getContar();
	}
	
//	@PostMapping("/salvar")
//	public Usuario salvar (@RequestBody Usuario objetoUsuario) {
//		return usuarioService.salvar(objetoUsuario);
//	}
	@GetMapping("/listar")
	public ResponseEntity<List<UsuarioResponseDTO>> listar(){
		return ResponseEntity.ok(usuarioService.listar());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioResponseDTO> acharId(@PathVariable Integer id) {
		UsuarioResponseDTO usuarioDTO = usuarioService.acharId(id);
		if (usuarioDTO == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(usuarioDTO);
	}
//	@DeleteMapping("/delete/{id}")
//	public void apagar(@PathVariable Integer id) {
//		usuarioService.apagar(id);
//	}
	@DeleteMapping("/deletarLogico/{id}")//perguntar......
	public ResponseEntity<Void> deletarLogico(@PathVariable Integer id) throws MyEntityNotFoundException {//alterando o apagar para deletarLogico
		boolean deletarLogico = usuarioService.deletarLogico(id);
		if (deletarLogico) {
			return ResponseEntity.noContent().build();			
		} else {
			return ResponseEntity.notFound().build(); 
		}
	}
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<UsuarioResponseDTO> atualizar(@PathVariable Integer id, @RequestBody UsuarioRequestDTO objetoUsuario) throws MyEntityNotFoundException { 
		UsuarioResponseDTO usuarioDTO = usuarioService.atualizar(id, objetoUsuario);
		if (usuarioDTO == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(usuarioDTO);
	}
}
