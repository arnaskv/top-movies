package com.arnaskv.top_movies.controller;

import com.arnaskv.top_movies.models.Genre;
import com.arnaskv.top_movies.services.genre.GenreService;
import com.arnaskv.top_movies.services.tmdb.TmdbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/genres")
@RequiredArgsConstructor
public class GenreController {
    private final GenreService genreService;
    private final TmdbService tmdbService;

    @GetMapping("/{id}")
    public ResponseEntity<Genre> getGenre(@PathVariable Long id) {
        return ResponseEntity.ok(genreService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Genre>> getAllGenres() {
        return ResponseEntity.ok(genreService.getAll());
    }

    @GetMapping("/refresh")
    public ResponseEntity<List<Genre>> getGenres() {
        List<Genre> genres = tmdbService.getAllMovieGenres();
        return ResponseEntity.ok(genres);
    }
}
