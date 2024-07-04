package com.arnaskv.top_movies.controller;

import com.arnaskv.top_movies.models.Movie;
import com.arnaskv.top_movies.services.movie.MovieService;
import com.arnaskv.top_movies.services.tmdb.TmdbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
    private final TmdbService tmdbService;

    @GetMapping("/all")
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAll());
    }

    @GetMapping("/refresh")
    public ResponseEntity<List<Movie>> getNewMovies() {
        List<Movie> movies = tmdbService.getTmdbTop250Movies();
        return ResponseEntity.ok(movies);
    }
}
