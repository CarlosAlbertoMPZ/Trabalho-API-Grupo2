package com.apiGrupo2.g2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiGrupo2.g2.entities.Endereco;
import com.apiGrupo2.g2.repositories.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	EnderecoRepository enderecoRepository;
	
	public Integer getContar() {
		return enderecoRepository.contar();
	}
	
	public Endereco salvar(Endereco objetoEndereco) {
		return enderecoRepository.save(objetoEndereco);
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
		
		if (objetoEndereco.getIbge()!=null) {
			registroAntigo.setIbge(objetoEndereco.getIbge());
		}
		
		if (objetoEndereco.getGuia()!=null) {
			registroAntigo.setGuia(objetoEndereco.getGuia());
		}
		
		if (objetoEndereco.getDdd()!=null) {
			registroAntigo.setDdd(objetoEndereco.getDdd());
		}
		if (objetoEndereco.getSiafi()!=null) {
			registroAntigo.setSiafi(objetoEndereco.getSiafi());
		}
				
		if (objetoEndereco.getUsuario()!=null) {
			registroAntigo.setUsuario(objetoEndereco.getUsuario());
		}
								
		registroAntigo.setId(id);
		return enderecoRepository.save(registroAntigo);
	}
}
