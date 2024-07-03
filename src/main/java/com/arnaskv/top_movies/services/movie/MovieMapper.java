package com.arnaskv.top_movies.services.movie;

import com.arnaskv.top_movies.dto.TmdbMovieResponse;
import com.arnaskv.top_movies.models.Movie;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieMapper {
    public List<Movie> mapToMovies(TmdbMovieResponse tmdbMovieResponse) {
        return tmdbMovieResponse.getResults().stream()
                .map(tmdbMovie -> Movie.builder()
                        .title(tmdbMovie.getTitle())
                        .overview(tmdbMovie.getOverview())
                        .popularity(tmdbMovie.getPopularity())
                        .posterPath(tmdbMovie.getPosterPath())
                        .rating(tmdbMovie.getVoteAverage())
                        .build()
                )
                .collect(Collectors.toList());
    }
}
