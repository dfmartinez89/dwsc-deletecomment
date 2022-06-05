package dwsc.proyecto.deletecomment.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dwsc.proyecto.deletecomment.domain.Comment;
import dwsc.proyecto.deletecomment.domain.Movie;
import dwsc.proyecto.deletecomment.exceptions.CommentNotFoundException;
import dwsc.proyecto.deletecomment.exceptions.CustomResponse;
import dwsc.proyecto.deletecomment.exceptions.MovieNotFoundException;
import dwsc.proyecto.deletecomment.service.CommentService;
import dwsc.proyecto.deletecomment.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "comment", description = "create comment")
public class DeleteCommentController {

	@Autowired
	CommentService commentService;

	@Autowired
	MovieService movieService;

	@Operation(summary = "Get all comments related to a given movie", description = "Operation to list comments")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "comments listed succesfully"),
			@ApiResponse(responseCode = "404", description = "movie not found", content = @Content(schema = @Schema(implementation = CustomResponse.class))) })
	@RequestMapping(method = RequestMethod.GET, path = "/comment/{movieId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<Comment>> getAllCommentsByMovieId(
			@Parameter(description = "Movie id") @PathVariable(required = true) String movieId) {
		Optional<Movie> movie = movieService.findMovieById(movieId);
		if (!movie.isPresent()) {
			throw new MovieNotFoundException(HttpStatus.NOT_FOUND, "The movie with id:" + movieId + " does not exists");
		}

		return ResponseEntity.ok(commentService.getAllByMovieId(movieId));
	}

	@Operation(summary = "delete a comment related to a given movie", description = "Operation to delete a comment")
	@ApiResponses({ @ApiResponse(responseCode = "204", description = "comment deleted succesfully"),
			@ApiResponse(responseCode = "404", description = "comment not found", content = @Content(schema = @Schema(implementation = CustomResponse.class))) })
	@RequestMapping(method = RequestMethod.DELETE, path = "/comment/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Comment> deleteComment(
			@Parameter(description = "Comment id") @PathVariable(required = true) String id) {
		Optional<Comment> comment = commentService.getComment(id);

		if (!comment.isPresent()) {
			throw new CommentNotFoundException(HttpStatus.NOT_FOUND, "The comment with id: " + id + " does not exists");
		}

		commentService.deleteComment(id);

		// Update score average
		Movie movie = comment.get().getMovie();
		String movieId = movie.getId();
		double score = movieService.getScoreAverageByMovie(movieId);
		movieService.updateScore(movieId, score);

		return ResponseEntity.noContent().build();

	}
}
