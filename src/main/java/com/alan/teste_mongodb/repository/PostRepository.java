package com.alan.teste_mongodb.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.alan.teste_mongodb.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	// https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/
	// Table 7. Supported keywords for query methods

	List<Post> findByTituloContaining(String text);

	List<Post> findByTituloContainingIgnoreCase(String text);

	// https://docs.mongodb.com/manual/reference/operator/query/regex/
	@Query("{ 'titulo': { $regex: ?0, $options: 'i' } }")
	List<Post> findByTitulo(String text);

	@Query("{ $and: [ {data: {$gte: ?1} }, { data: { $lte: ?2} } , { $or: [ { 'titulo': { $regex: ?0, $options: 'i' } }, { 'corpo': { $regex: ?0, $options: 'i' } }, { 'comentarios.texto': { $regex: ?0, $options: 'i' } } ] } ] }")
	List<Post> findByVariosCriterios(String text, Date minData, Date maxData);

}
