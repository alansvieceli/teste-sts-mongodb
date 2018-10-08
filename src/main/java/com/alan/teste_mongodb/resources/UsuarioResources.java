package com.alan.teste_mongodb.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.alan.teste_mongodb.domain.Post;
import com.alan.teste_mongodb.domain.Usuario;
import com.alan.teste_mongodb.dto.UsuarioDTO;
import com.alan.teste_mongodb.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResources {
	
	@Autowired
	private UsuarioService service;

	//@GetMapping
	//ou
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<UsuarioDTO>> buscaTodos() {
		//200 - retorno ok com conteudo	
		List<Usuario> lista = service.buscaTodos();	
		List<UsuarioDTO> listaDTO = lista.stream().map(x ->  new UsuarioDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaDTO);

	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<UsuarioDTO> buscaPorID(@PathVariable String id) {
			
		Usuario usuario = service.buscaPorID(id);		
		return ResponseEntity.ok().body(new UsuarioDTO(usuario));

	} 
	
	@PostMapping
	public ResponseEntity<Void> inserirUsuario(@RequestBody UsuarioDTO objDTO){
		//201 - codigo gerado quando se cria um novo recurso, conteudo ok mas sem body e sim no header
		Usuario user = service.dtoToUsuario(objDTO);
		user = service.inserir(user);		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri(); 
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> excluir(@PathVariable String id) {
		//204 - no content...ok mas nao retorna nada
		service.excluir(id);		
		return ResponseEntity.noContent().build();
	} 
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Void> atualizarUsuario(@RequestBody UsuarioDTO objDTO, @PathVariable String id){
		Usuario user = service.dtoToUsuario(objDTO);
		user.setId(id);
		user = service.atualizar(user);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}/posts", method=RequestMethod.GET)
	public ResponseEntity<List<Post>> buscaPosts(@PathVariable String id) {
			
		Usuario usuario = service.buscaPorID(id);		
		return ResponseEntity.ok().body(usuario.getPosts());

	} 
	

}
