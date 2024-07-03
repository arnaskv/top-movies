package com.arnaskv.top_movies.services.genre;

import com.arnaskv.top_movies.dto.TmdbGenreResponse;
import com.arnaskv.top_movies.models.Genre;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GenreMapper {
    public List<Genre> mapToGenres(TmdbGenreResponse tmdbGenreResponse) {
        return tmdbGenreResponse
                .getGenres()
                .stream()
                .map(tmdbGenre -> Genre.builder()
                        .id(tmdbGenre.getId())
                        .name(tmdbGenre.getName())
                        .build()
                )
                .collect(Collectors.toList());
    }
}
