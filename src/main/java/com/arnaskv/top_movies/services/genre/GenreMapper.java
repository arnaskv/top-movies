package com.arnaskv.top_movies.services.genre;

import com.arnaskv.top_movies.dto.GenreResponseDto;
import com.arnaskv.top_movies.dto.MovieResponseDto;
import com.arnaskv.top_movies.dto.TmdbGenreResponseDto;
import com.arnaskv.top_movies.models.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class GenreMapper {
    // Map tmdb api response to genre entity
    public List<Genre> mapToGenres(TmdbGenreResponseDto tmdbGenreResponse) {
        return tmdbGenreResponse
                .getGenres()
                .stream()
                .map(tmdbGenre -> Genre.builder()
                        .name(tmdbGenre.getName())
                        .tmdbId(tmdbGenre.getId())
                        .build()
                )
                .collect(Collectors.toList());
    }

    // Map genre entity with movies to our api response
    public GenreResponseDto mapToGenreResponseDto(Genre genre, List<MovieResponseDto> movieDtos) {
        return GenreResponseDto.builder()
                .id(genre.getId())
                .name(genre.getName())
                .movies(movieDtos)
                .build();
    }
}
