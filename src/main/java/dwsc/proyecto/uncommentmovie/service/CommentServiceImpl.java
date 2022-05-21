package dwsc.proyecto.uncommentmovie.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dwsc.proyecto.uncommentmovie.dao.CommentRepository;
import dwsc.proyecto.uncommentmovie.domain.Comment;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	CommentRepository commentRepo;
	
	public Optional<Comment> getComment(String id){
		return commentRepo.findById(id);
	}

	public Iterable<Comment> getAllByMovieId(String movieId) {
		return commentRepo.findAllByMovieId(movieId);
	}

	public void deleteComment(String id) {
		commentRepo.deleteById(id);
	}

}
