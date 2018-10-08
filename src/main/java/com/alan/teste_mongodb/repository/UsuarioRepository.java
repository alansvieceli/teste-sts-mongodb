package com.alan.teste_mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.alan.teste_mongodb.domain.Usuario;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {

}
