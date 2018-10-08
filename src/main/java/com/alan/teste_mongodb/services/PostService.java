package com.alan.teste_mongodb.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alan.teste_mongodb.domain.Post;
import com.alan.teste_mongodb.repository.PostRepository;
import com.alan.teste_mongodb.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;

	public Post buscaPorID(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado id=" + id));
	}	
	
	public List<Post> buscaPorTitulo(String texto){
		return repo.findByTituloContainingIgnoreCase(texto);
	}
	
	public List<Post> buscaPorTitulo2(String texto){
		return repo.findByTitulo(texto);
	}
	
	public List<Post> buscaVariosCriterios(String texto, Date minData, Date maxData){
		
		//maxData = new Date(maxData.getTime() + 24 * 60 *60 * 1000);
		return repo.findByVariosCriterios(texto, minData, maxData);
	}		
		
}
