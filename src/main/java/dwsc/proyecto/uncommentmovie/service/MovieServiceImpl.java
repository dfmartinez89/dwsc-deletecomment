package dwsc.proyecto.uncommentmovie.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dwsc.proyecto.uncommentmovie.dao.CommentRepository;
import dwsc.proyecto.uncommentmovie.dao.MovieRepository;
import dwsc.proyecto.uncommentmovie.domain.Comment;
import dwsc.proyecto.uncommentmovie.domain.Movie;

@Service
public class MovieServiceImpl implements MovieService {
	@Autowired
	MovieRepository movieRepo;

	@Autowired
	CommentRepository commentRepo;

	public Optional<Movie> findMovieById(String movieId) {
		return movieRepo.findById(movieId);
	}

	public double getScoreAverageByMovie(String movieId) {
		Iterable<Comment> comments = commentRepo.findAllByMovieId(movieId);
		int elem = 0;
		double totalScore = 0;
		for (Comment comment : comments) {
			elem++;
			totalScore = totalScore + comment.getScore();
		}

		if (elem != 0) {
			return totalScore / elem;
		} else {
			return 0;
		}

	}

	public void updateScore(String id, double score) {
		Optional<Movie> movieOptional = movieRepo.findById(id);

		Movie movie = movieOptional.get();
		movie.setAverageScore(score);

		movieRepo.save(movie);

	}

}
