package dwsc.proyecto.deletecomment.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import dwsc.proyecto.deletecomment.domain.Comment;

@Service
public interface CommentService {

	public Optional<Comment> getComment(String id);

	public Iterable<Comment> getAllByMovieId(String movieId);

	public void deleteComment(String id);
}
