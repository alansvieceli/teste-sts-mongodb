package com.alan.teste_mongodb.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.alan.teste_mongodb.domain.Post;
import com.alan.teste_mongodb.domain.Usuario;
import com.alan.teste_mongodb.dto.AutorDTO;
import com.alan.teste_mongodb.dto.ComentarioDTO;
import com.alan.teste_mongodb.repository.PostRepository;
import com.alan.teste_mongodb.repository.UsuarioRepository;

@Configuration
public class IniciandoComDados implements CommandLineRunner{
	
	@Autowired
	private UsuarioRepository userRepo;
	
	@Autowired
	private PostRepository postRepo;

	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepo.deleteAll();
		postRepo.deleteAll();
		
		Usuario maria = new Usuario(null, "Maria Brown", "maria@gmail.com");
		Usuario alex = new Usuario(null, "Alex Green", "alex@gmail.com");
		Usuario bob = new Usuario(null, "Bob Grey", "bob@gmail.com");
		
		userRepo.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viarja para SP, abraços!", new AutorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei Feliz hoje!!", new AutorDTO(maria));
		
		ComentarioDTO c1 = new ComentarioDTO("Boa viagem mano!", sdf.parse("21/03/2018"), new AutorDTO(alex));
		ComentarioDTO c2 = new ComentarioDTO("Aproveite!", sdf.parse("22/03/2018"), new AutorDTO(bob));
		ComentarioDTO c3 = new ComentarioDTO("Tenha um ótimo dia!!", sdf.parse("23/03/2018"), new AutorDTO(alex));
		
		post1.getComentarios().addAll(Arrays.asList(c1, c2));
		post2.getComentarios().addAll(Arrays.asList(c3));
		
		postRepo.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepo.save(maria);
		
	}

}
