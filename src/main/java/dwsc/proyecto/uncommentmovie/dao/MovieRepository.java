package dwsc.proyecto.uncommentmovie.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import dwsc.proyecto.uncommentmovie.domain.Movie;


@RepositoryRestResource(collectionResourceRel = "movie", path = "movie")
public interface MovieRepository extends MongoRepository<Movie, String> {
}
