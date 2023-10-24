package com.apiGrupo2.g2.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import com.apiGrupo2.g2.repositories.UsuarioRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

//	    @Autowired
//	    UsuarioRepository usuarioRepository;

	    @Autowired
	    JWTFilter filter;

	    @Autowired
	    UsuarioDetailsServiceImpl uds;

	    @Bean
	    @Override
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Override
	    protected void configure(HttpSecurity http) throws Exception { // Metodo encarregado de configurar a seguranca da API
	        http
			        .cors()
			        .and()
	        		.csrf().disable()
	                .httpBasic().disable()
	                .authorizeHttpRequests()
	                .antMatchers("/contar/salvar", "/contar/listar", "/endereco/salvar","/usuario/salvar", "/usuario/logar").permitAll()
	                .antMatchers("/endereco/contar","/endereco/listar","/endereco/{id}","endereco/deletarLogico/{id}").hasRole("COMPRADOR")
	                .antMatchers("/categoria/contar","/categoria/Listar","/categoria/{id}","/categoria/deletarLogico/{id}","/categoria/atualizar/{id}",
	                		"/produto/contar","/produto/salvar","/produto/listar","/produto/{id}","/produto/deletarLogico/{id}","/produto/atualizar/{id}",
	                		"/atualizar/{id}").hasRole("VENDEDOR")
	                .antMatchers("endereco/atualizar/{id}", "/pedido/listar","/pedido/{id}","/pedido/deletarLogico/{id}",
	                		"/pedido/atualizar/{id}","/pedido/salvar", "/pedido/usuario/{usuarioId}").hasAnyRole("VENDEDOR","COMPRADOR")
	                .and()
	                .userDetailsService(uds)
	                .exceptionHandling()
	                    .authenticationEntryPoint(
	                            (request, response, authException) ->
	                                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
	                    )
	                .and()
	                .sessionManagement()
	                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	    }
	
//	@Bean
//	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests().anyRequest().permitAll().and().csrf(csrf -> csrf.disable());
//		return http.build();
//	}
}
