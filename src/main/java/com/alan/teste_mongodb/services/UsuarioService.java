package com.alan.teste_mongodb.services;

//Serviço

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alan.teste_mongodb.domain.Usuario;
import com.alan.teste_mongodb.dto.UsuarioDTO;
import com.alan.teste_mongodb.repository.UsuarioRepository;
import com.alan.teste_mongodb.services.exception.ObjectNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repo;

	public List<Usuario> buscaTodos() {
		return repo.findAll();
	}

	public Usuario buscaPorID(String id) {
		Optional<Usuario> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado id=" + id));
	}
	
	public Usuario inserir(Usuario obj) {
		return repo.insert(obj);
	}
	
	public void excluir(String id) {
		buscaPorID(id);
		repo.deleteById(id);
	}
	
	public Usuario atualizar(Usuario user) {
		Usuario newObj = buscaPorID(user.getId());
		atualizarDado(newObj, user);
		return repo.save(newObj);
	}
	
	public void atualizarDado(Usuario newObj, Usuario obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());		
	}
	
	public Usuario dtoToUsuario(UsuarioDTO obj) {
		return new Usuario(obj.getId(), obj.getNome(), obj.getEmail());		
	}
	
}
