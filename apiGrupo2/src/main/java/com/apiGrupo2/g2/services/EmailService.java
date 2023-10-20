package com.apiGrupo2.g2.services;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.apiGrupo2.g2.dto.PedidoDTO;
import com.apiGrupo2.g2.dto.UserDTO;
import com.apiGrupo2.g2.entities.Pedido;
import com.apiGrupo2.g2.entities.Produto;
import com.apiGrupo2.g2.entities.Usuario;

@Configuration
@Service
public class EmailService {

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	PedidoService pedidoService;
	
	@Autowired
	ProdutoService produtoService;
	
	private JavaMailSender emailSender;

	@Autowired
	public void setJavaMailSender(JavaMailSender emailSender) {
		this.emailSender = emailSender;
	}

	@Value("${spring.mail.host}") // @value definido no arquivo de propriedades. Define para o atributo host o
									// valor referente ao ("...")
	private String host;

	@Value("${spring.mail.port}")
	private Integer port;

	@Value("${spring.mail.username}")
	private String username;

	@Value("${spring.mail.password}")
	private String password;

	@Bean
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl emailSender = new JavaMailSenderImpl();
		Properties prop = new Properties();
		emailSender.setHost(host);
		emailSender.setPort(port);
		emailSender.setUsername(username);
		emailSender.setPassword(password);
		prop.put("mail.transport.protocol", "smtp");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		emailSender.setJavaMailProperties(prop);
		return emailSender;
	}

	public void envioEmailCadastro() {
		MimeMessage mensagemCadastro = emailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(mensagemCadastro, true);
			helper.setFrom("api.grupo2.serratec@gmail.com");
			helper.setTo("carlosalbertompz@gmail.com");
			helper.setSubject("Cadastro concluido!");

			StringBuilder builder = new StringBuilder();
			builder.append("<html>\r\n");
			builder.append("	<body>\r\n");
			builder.append("		<div align=\"center\">\r\n");
			builder.append("			<h1>Cadastro realizado com sucesso!</h1>\r\n");
			builder.append("		</div>\r\n");
			builder.append("		<br/>\r\n");
			builder.append("		<div align=\"center\">\r\n");
			builder.append("			Em caso de erro, favor contatar o suporte: api.grupo2.serratec@gmail.com");
			builder.append("		</div>\r\n");
			builder.append("	</body>\r\n");
			builder.append("</html>\r\n");

			helper.setText(builder.toString(), true);
			emailSender.send(mensagemCadastro);

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	public void envioEmailTeste() {//(Pedido pedido) la em baixo getValorNota DoubleValor 
		MimeMessage mensagemCadastro = emailSender.createMimeMessage();// não precisa ser altrada em todos os disparos
																		// de email, abertura p escrit de emal
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mensagemCadastro, true);// perm entradas iniciais de email
			helper.setFrom("api.grupo2.serratec@gmail.com");
			helper.setTo("carlosalbertompz@gmail.com");
			helper.setSubject("Ex.1!!");// titulo do email qnd enviamos para alguem
			
			LocalDate localDate = LocalDate.now();
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
			
			String dataEntrega = localDate.plusDays(7).format(format);
			
			Double valor = 15.9;/// getValorPedido
			DecimalFormat df = new DecimalFormat("R$, ##0.00");
			
			StringBuilder builder = new StringBuilder();
			builder.append("<html>\r\n");//texto email = escreva - \r\n pula linha
			builder.append("<body>\r\n");
			builder.append("<div align=\"center\">\r\n");
			builder.append("<h1>Exemplo</h1>\r\n");
			builder.append("</div>\r\n");
			builder.append("<br/>\r\n");
			
			builder.append("<center>\r\n");
			builder.append("<table border='2' cellpadding>\r\n");
			builder.append("<tr> <th> Nome</th> <th> Email</th> <th>Perfis</th><th> Data de entrega</th> </tr>\r\n");
			
			List<Usuario>listaUsuarios=usuarioService.listar();
			for(Usuario usuario : listaUsuarios) {//vai percorrer a lista usuario e vai executar oque esta dentro do for
				builder.append("		    <tr>\r\n");
				builder.append("			<td>\r\n");
				builder.append(usuario.getNomeUsuario());
				builder.append("			</td>\r\n");
				builder.append("			<td>\r\n");
				builder.append(usuario.getEmail());
				builder.append("			</td>\r\n");
				builder.append("		    <td>\r\n");
				builder.append(usuario.getRoles());
				builder.append("			</td>\r\n");
				builder.append("			<td>\r\n");
				builder.append(dataEntrega);
				builder.append("			</td>\r\n");
			}
			
			builder.append("		</table>\r\n");
			builder.append("		</center>\r\n");
			builder.append("		<table border='1' cellpadding='1'  >\r\n");
			builder.append("<tr><th>Valor Total</th></tr>\r\n");
			builder.append("			<td>\r\n");
			builder.append(df.format(valor));
			builder.append("			</td>\r\n");
			builder.append("		</table>\r\n");
			builder.append("	</body>\r\n");
			builder.append("</html>");
			
			helper.setText(builder.toString(), true);
			emailSender.send(mensagemCadastro);
			
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
	
	public void envioEmailPedido(Pedido pedido) {//(Pedido pedido) la em baixo getValorNota DoubleValor 
		MimeMessage mensagemCadastro = emailSender.createMimeMessage();// não precisa ser altrada em todos os disparos
																	// de email, abertura p escrit de emal
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mensagemCadastro, true);// perm entradas iniciais de email
			helper.setFrom("api.grupo2.serratec@gmail.com");
			helper.setTo("carlosalbertompz@gmail.com");
			helper.setSubject("Pedido Realizado!!");// titulo do email qnd enviamos para alguem
			
			LocalDate localDate = LocalDate.now();
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
			
			String dataEntrega = localDate.plusDays(7).format(format);
			
			Double valor = pedido.getValorPedido();/// getValorPedido
			DecimalFormat df = new DecimalFormat("R$, ##0.00");
			
			StringBuilder builder = new StringBuilder();
			builder.append("<html>\r\n");//texto email = escreva - \r\n pula linha
			builder.append("<body>\r\n");
			builder.append("<div align=\"center\">\r\n");
			builder.append("<h1>DADOS PEDIDO</h1>\r\n");
			builder.append("</div>\r\n");
			builder.append("<br/>\r\n");
			
			builder.append("<center>\r\n");
			builder.append("<table border='2' cellpadding>\r\n");
			builder.append("<tr> <th> Nome</th> <th> </th> <th> Data de entrega</th> </tr>\r\n");
			
			List<Pedido>listapedidos = pedidoService.listar();
			for(Pedido pedidos : listapedidos) {//vai percorrer a lista usuario e vai executar oque esta dentro do for
				builder.append("		    <tr>\r\n");
				builder.append("			<td>\r\n");
				builder.append(localDate);
				builder.append("			</td>\r\n");
				builder.append("			<td>\r\n");
				builder.append(pedidos.getProdutos());
				builder.append("			</td>\r\n");
				builder.append("		    <td>\r\n");
				builder.append(valor);
				builder.append("			</td>\r\n");
				builder.append("			<td>\r\n");
				builder.append(dataEntrega);
				builder.append("			</td>\r\n");
			}
			
			builder.append("		</table>\r\n");
			builder.append("		</center>\r\n");
			builder.append("		<table border='1' cellpadding='1'  >\r\n");
			builder.append("<tr><th>Valor Total</th></tr>\r\n");
			builder.append("			<td>\r\n");
			builder.append(df.format(valor));
			builder.append("			</td>\r\n");
			builder.append("		</table>\r\n");
			builder.append("	</body>\r\n");
			builder.append("</html>");
			
			helper.setText(builder.toString(), true);
			emailSender.send(mensagemCadastro);
			
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	public void envioEmailProduto(Produto produto) {//(Pedido pedido) la em baixo getValorNota DoubleValor 
		MimeMessage mensagemCadastro = emailSender.createMimeMessage();// não precisa ser altrada em todos os disparos
																	// de email, abertura p escrit de emal
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mensagemCadastro, true);// perm entradas iniciais de email
			helper.setFrom("api.grupo2.serratec@gmail.com");
			helper.setTo("carlosalbertompz@gmail.com");
			helper.setSubject("Venda realizada!!");// titulo do email qnd enviamos para alguem
			
			LocalDate localDate = LocalDate.now();
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
			
			String dataEntrega = localDate.plusDays(7).format(format);
			
			Double valor = produto.getValorUnitario();/// getValorPedido
			DecimalFormat df = new DecimalFormat("R$, ##0.00");
			
			StringBuilder builder = new StringBuilder();
			builder.append("<html>\r\n");//texto email = escreva - \r\n pula linha
			builder.append("<body>\r\n");
			builder.append("<div align=\"center\">\r\n");
			builder.append("<h1>DADOS DA VENDA</h1>\r\n");
			builder.append("</div>\r\n");
			builder.append("<br/>\r\n");
			
			builder.append("<center>\r\n");
			builder.append("<table border='2' cellpadding>\r\n");
			builder.append("<tr> <th> Nome</th> <th> </th> <th> Data de entrega</th> </tr>\r\n");
			
			List<Produto>listaProdutos = produtoService.listar();
			for(Produto produtos : listaProdutos) {//vai percorrer a lista usuario e vai executar oque esta dentro do for
				builder.append("		    <tr>\r\n");
				builder.append("			<td>\r\n");
				builder.append(localDate);
				builder.append("			</td>\r\n");
				builder.append("			<td>\r\n");
				builder.append(produtos.getNome());
				builder.append("			</td>\r\n");
				builder.append("		    <td>\r\n");
				builder.append(valor);
				builder.append("			</td>\r\n");
				builder.append("			<td>\r\n");
				builder.append(produtos.getQuantidadeEstoque());
				builder.append("			</td>\r\n");
				builder.append("		    <td>\r\n");
				builder.append(dataEntrega);
				builder.append("			</td>\r\n");
			}
			
			builder.append("		</table>\r\n");
			builder.append("		</center>\r\n");
			builder.append("		<table border='1' cellpadding='1'  >\r\n");
			builder.append("<tr><th>Valor Total</th></tr>\r\n");
			builder.append("			<td>\r\n");
			builder.append(df.format(valor));
			builder.append("			</td>\r\n");
			builder.append("		</table>\r\n");
			builder.append("	</body>\r\n");
			builder.append("</html>");
			
			helper.setText(builder.toString(), true);
			emailSender.send(mensagemCadastro);
			
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
