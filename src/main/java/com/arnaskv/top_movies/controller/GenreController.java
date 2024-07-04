package com.arnaskv.top_movies.controller;

import com.arnaskv.top_movies.dto.GenreResponseDto;
import com.arnaskv.top_movies.models.Genre;
import com.arnaskv.top_movies.services.genre.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/genres")
@RequiredArgsConstructor
public class GenreController {
    private final GenreService genreService;

    @GetMapping
    public ResponseEntity<GenreResponseDto> getGenre(@RequestParam Long id) {
        return ResponseEntity.ok(genreService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Genre>> getAllGenres() {
        return ResponseEntity.ok(genreService.getAll());
    }

    @GetMapping("/refresh")
    public ResponseEntity<List<Genre>> getNewGenres() {
        List<Genre> genres = genreService.getTmdbMovieGenres();
        return ResponseEntity.ok(genres);
    }
}
