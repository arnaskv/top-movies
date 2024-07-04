package com.arnaskv.top_movies.dto;

import com.arnaskv.top_movies.models.Genre;
import lombok.Getter;

import java.util.Set;

@Getter
public class TmdbMovieWithGenres {
    final TmdbMovieResponseDto.TmdbMovie tmdbMovie;
    final Set<Genre> genres;

    public TmdbMovieWithGenres(TmdbMovieResponseDto.TmdbMovie tmdbMovie, Set<Genre> genres) {
        this.tmdbMovie = tmdbMovie;
        this.genres = genres;
    }
}
