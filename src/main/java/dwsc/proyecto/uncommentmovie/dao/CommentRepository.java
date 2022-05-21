package dwsc.proyecto.uncommentmovie.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import dwsc.proyecto.uncommentmovie.domain.Comment;

@RepositoryRestResource(collectionResourceRel = "comment", path = "comment")
public interface CommentRepository extends MongoRepository<Comment, String> {
	public Iterable<Comment> findAllByMovieId(String movieId);
}
