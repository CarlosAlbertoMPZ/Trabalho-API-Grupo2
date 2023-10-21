package com.apiGrupo2.g2.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.apiGrupo2.g2.dto.EnderecoDTO;
import com.apiGrupo2.g2.entities.Endereco;
import com.apiGrupo2.g2.repositories.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	EnderecoRepository enderecoRepository;
	
	public Integer getContar() {
		return enderecoRepository.contar();
	}
	
	public Endereco salvar(EnderecoDTO objetoEndereco) {
		
		Endereco viaCep=pesquisarEndereco(objetoEndereco.getCep());
		Endereco enderecoNovo=new Endereco();
		enderecoNovo.setBairro(viaCep.getBairro());
		enderecoNovo.setCep(objetoEndereco.getCep());
		enderecoNovo.setComplemento(objetoEndereco.getComplemento());
		enderecoNovo.setLocalidade(viaCep.getLocalidade());
		enderecoNovo.setLogradouro(viaCep.getLogradouro());
		enderecoNovo.setUf(viaCep.getUf());
		enderecoNovo.setNumero(objetoEndereco.getNumero());
		return enderecoRepository.save(enderecoNovo);
	}
	
	public Endereco acharId(Integer id) {
		return enderecoRepository.findById(id).get();
	}
	
	public List<Endereco> listar(){
		return enderecoRepository.findAll();
	}
	
	public void deletarLogico(Integer id) {
		Endereco objEndereco = acharId(id);
		
		if(acharId(id) != null) {
			acharId(id).setAtivo(false);
			enderecoRepository.save(objEndereco);
		}
	}
	
//	public Endereco registrarEndereco(Endereco endereco) {
//		Endereco viaCep=pesquisarEndereco(endereco.getCep());
//		Endereco enderecoNovo=new Endereco();
//		enderecoNovo.setBairro(viaCep.getBairro());
//		enderecoNovo.setCep(endereco.getCep());
//		enderecoNovo.setComplemento(endereco.getComplemento());
//		enderecoNovo.setLocalidade(viaCep.getLocalidade());
//		enderecoNovo.setLogradouro(viaCep.getLogradouro());
//		enderecoNovo.setUf(viaCep.getUf());
//		return enderecoRepository.save(endereco);
//	}
	
	public Endereco pesquisarEndereco(String cep) {
		RestTemplate restTemplate = new RestTemplate();
		String uri = "http://viacep.com.br/ws/{cep}/json/";
		Map<String, String> params = new HashMap<>();
		params.put("cep", cep);
		return restTemplate.getForObject(uri, Endereco.class, params);
	}
	
	public Endereco atualizar(Integer id, Endereco objetoEndereco) {
		Endereco registroAntigo = acharId(id);

		if (objetoEndereco.getAtivo()!=null) {
			registroAntigo.setAtivo(objetoEndereco.getAtivo());
		}
		
		if (objetoEndereco.getCep()!=null) {
			registroAntigo.setCep(objetoEndereco.getCep());
		}
		
		if (objetoEndereco.getLogradouro()!=null) {
			registroAntigo.setLogradouro(null);
		}
		
		if (objetoEndereco.getComplemento()!=null) {
			registroAntigo.setComplemento(objetoEndereco.getComplemento());
		}
		
		if (objetoEndereco.getNumero()!=null) {
			registroAntigo.setNumero(objetoEndereco.getNumero());
		}
		
		if (objetoEndereco.getBairro()!=null) {
			registroAntigo.setBairro(objetoEndereco.getBairro());
		}
		
		if (objetoEndereco.getUf()!=null) {
			registroAntigo.setUf(objetoEndereco.getUf());
		}
		
		if (objetoEndereco.getLocalidade()!=null) {
			registroAntigo.setLocalidade(objetoEndereco.getLocalidade());
		}
		
								
		registroAntigo.setId(id);
		return enderecoRepository.save(registroAntigo);
	}
}
