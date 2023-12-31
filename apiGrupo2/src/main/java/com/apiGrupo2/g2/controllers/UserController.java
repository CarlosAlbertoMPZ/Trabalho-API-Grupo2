//package com.apiGrupo2.g2.controllers;
//
//import java.util.Collections;
//import java.util.HashSet;
//import java.util.Map;
//import java.util.Set;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.apiGrupo2.g2.config.JWTUtil;
//import com.apiGrupo2.g2.dto.LoginDTO;
//import com.apiGrupo2.g2.dto.UserDTO;
//import com.apiGrupo2.g2.entities.Endereco;
//import com.apiGrupo2.g2.entities.Role;
//import com.apiGrupo2.g2.entities.User;
//import com.apiGrupo2.g2.enums.TipoRoleEnum;
//import com.apiGrupo2.g2.repositories.EnderecoRepository;
//import com.apiGrupo2.g2.repositories.RoleRepository;
//import com.apiGrupo2.g2.services.EnderecoService;
//import com.apiGrupo2.g2.services.UserService;
//
//@RestController
//@RequestMapping("/user")
//public class UserController {
//
//	@Autowired
//	UserService userService;
//
//	@Autowired
//	EnderecoRepository enderecoRepository;
//
//	@Autowired
//	EnderecoService enderecoService;
//
//	@Autowired
//	RoleRepository roleRepository;
//
//	@Autowired
//	private JWTUtil jwtUtil;
//
//	@Autowired
//	private AuthenticationManager authManager;
//
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//
//	@PostMapping("/registro")
//	public User cadastro(@RequestParam String email, @RequestBody UserDTO user) {
//
//		Set<String> strRoles = user.getRoles();
//		Set<Role> roles = new HashSet<>();
//
//		if (strRoles == null) {
//			Role userRole = roleRepository.findByName(TipoRoleEnum.ROLE_COMPRADOR)
//					.orElseThrow(() -> new RuntimeException("Erro: Role não encontrada."));
//			roles.add(userRole);
//		} else {
//			strRoles.forEach(role -> {
//				switch (role) {
//				case "VENDEDOR":
//					Role adminRole = roleRepository.findByName(TipoRoleEnum.ROLE_VENDEDOR)
//							.orElseThrow(() -> new RuntimeException("Erro: Role não encontrada."));
//					roles.add(adminRole);
//					break;
//				case "COMPRADOR":
//					Role userRole = roleRepository.findByName(TipoRoleEnum.ROLE_COMPRADOR)
//							.orElseThrow(() -> new RuntimeException("Erro: Role não encontrada."));
//					roles.add(userRole);
//				}
//			});
//		}
//
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
//
//		User usuarioResumido = new User();
//		usuarioResumido.setNomeUsuario(user.getNomeUsuario());
//		usuarioResumido.setEmail(user.getEmail());
//		usuarioResumido.setRoles(roles);
//		String encodedPass = passwordEncoder.encode(user.getPassword());
//		usuarioResumido.setPassword(encodedPass);
//
//		// emailService.envioEmailCadastro(email, user);
//
//		return userService.save(usuarioResumido);
//	}
//
//	@PostMapping("/login")
//	public Map<String, Object> login(@RequestBody LoginDTO body) {
//		try {
//			UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(
//					body.getEmail(), body.getPassword());
//
//			authManager.authenticate(authInputToken);
//
//			User user = userService.findByEmail(body.getEmail());
//			User usuarioResumido = new User();
//			usuarioResumido.setNomeUsuario(user.getNomeUsuario());
//			usuarioResumido.setEmail(user.getEmail());
//			usuarioResumido.setIdUser(user.getIdUser());
//			usuarioResumido.setRoles(user.getRoles());
//			String token = jwtUtil.generateTokenWithUserData(usuarioResumido);
//
//			return Collections.singletonMap("jwt-token", token);
//		} catch (AuthenticationException authExc) {
//			throw new RuntimeException("Credenciais Invalidas");
//		}
//	}
//
//}
