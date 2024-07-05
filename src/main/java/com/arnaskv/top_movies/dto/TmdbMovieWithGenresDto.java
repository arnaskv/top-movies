package com.arnaskv.top_movies.dto;

import com.arnaskv.top_movies.models.Genre;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
@Getter
public class TmdbMovieWithGenresDto {
    final TmdbMovieResponseDto.TmdbMovie tmdbMovie;
    final Set<Genre> genres;
}
