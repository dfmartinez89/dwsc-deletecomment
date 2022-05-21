package dwsc.proyecto.deletecomment.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dwsc.proyecto.deletecomment.dao.CommentRepository;
import dwsc.proyecto.deletecomment.domain.Comment;

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
