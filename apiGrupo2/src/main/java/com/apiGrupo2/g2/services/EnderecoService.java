package com.apiGrupo2.g2.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
		Optional<Endereco> enderecoOpt = enderecoRepository.findById(id);
		if (enderecoOpt.isEmpty()) {
			return null;
		}
		return enderecoOpt.get();
	}
	
	public List<Endereco> listar(){
		return enderecoRepository.findAll();
	}
	
	public boolean deletarLogico(Integer id) {
		Optional<Endereco> enderecoOpt = enderecoRepository.findById(id);
		if(enderecoOpt.isPresent()) {
			Endereco endereco = enderecoOpt.get();
			endereco.setAtivo(false);
			enderecoRepository.save(endereco);
			return true;
		} else {
			return false;		
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
	
	public Endereco atualizar(Integer id, EnderecoDTO enderecoDTO) {
		Optional<Endereco> enderecoBDOpt = enderecoRepository.findById(id);
		if (enderecoBDOpt.isEmpty()) {
			return null;
		}
		
		Endereco enderecoBD = enderecoBDOpt.get();

		if (enderecoDTO.getCep()!=null) {
			Endereco enderecoNovo = pesquisarEndereco(enderecoDTO.getCep());
			enderecoBD.setCep(enderecoNovo.getCep());
			enderecoBD.setBairro(enderecoNovo.getBairro());
			enderecoBD.setLocalidade(enderecoNovo.getLocalidade());
			enderecoBD.setLogradouro(enderecoNovo.getLogradouro());
			enderecoBD.setUf(enderecoNovo.getUf());
		}
		
		if (enderecoDTO.getComplemento()!=null) {
			enderecoBD.setComplemento(enderecoDTO.getComplemento());
		}
		
		if (enderecoDTO.getNumero()!=null) {
			enderecoBD.setNumero(enderecoDTO.getNumero());
		}
		
		return enderecoRepository.save(enderecoBD);
	}

	public List<Endereco> buscarPorUsuarioId(Integer usuarioId) {
		List<Endereco> enderecos = enderecoRepository.buscarPorUsuarioId(usuarioId);
		return enderecos;
	}

	public Endereco atualizarStatus(Integer id, boolean status) {
		Optional<Endereco> enderecoBDOpt = enderecoRepository.findById(id);
		if (enderecoBDOpt.isEmpty()) {
			return null;
		}
		
		Endereco enderecoBD = enderecoBDOpt.get();
		enderecoBD.setAtivo(status);
		
		return enderecoRepository.save(enderecoBD);
	}
}
