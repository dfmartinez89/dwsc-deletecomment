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

	@Override
	public Optional<Comment> getComment(String id) {
		return commentRepo.findById(id);
	}

	@Override
	public Iterable<Comment> getAllByMovieId(String movieId) {
		return commentRepo.findAllByMovieId(movieId);
	}

	@Override
	public void deleteComment(String id) {
		commentRepo.deleteById(id);
	}

}
