package com.alan.teste_mongodb.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alan.teste_mongodb.domain.Post;
import com.alan.teste_mongodb.resources.util.URL;
import com.alan.teste_mongodb.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResources {
	
	@Autowired
	private PostService service;

	//ttp://localhost:8080/posts/dsdsdsd
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Post> buscaPorID(@PathVariable String id) {			
		Post obj = service.buscaPorID(id);		
		return ResponseEntity.ok().body(obj);

	}
	
	//http://localhost:8080/posts/buscatitulo?texto=Bom%20dia
	@GetMapping(value="/buscatitulo")
	public ResponseEntity<List<Post>> buscaPorTitulo(@RequestParam(value="texto", defaultValue="") String texto) {
		texto = URL.decodeParam(texto);
		List<Post> lista = service.buscaPorTitulo(texto);		
		return ResponseEntity.ok().body(lista);
	}
	
	@GetMapping(value="/buscatitulo2")
	public ResponseEntity<List<Post>> buscaPorTitulo2(@RequestParam(value="texto", defaultValue="") String texto) {
		texto = URL.decodeParam(texto);
		List<Post> lista = service.buscaPorTitulo2(texto);		
		return ResponseEntity.ok().body(lista);
	} 	
	
	@GetMapping(value="/buscavarioscriterios")
	public ResponseEntity<List<Post>> buscaVariosCriterios(
			@RequestParam(value="texto", defaultValue="") String texto,
			@RequestParam(value="minData", defaultValue="") String minData,
			@RequestParam(value="maxData", defaultValue="") String maxData) {
		texto = URL.decodeParam(texto);
		Date min = URL.converterData(minData, new Date(0));
		Date max = URL.converterData(maxData, new Date());
		List<Post> lista = service.buscaVariosCriterios(texto, min, max);		
		return ResponseEntity.ok().body(lista);
	} 

}
