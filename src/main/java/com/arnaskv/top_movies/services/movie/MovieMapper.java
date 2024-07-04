package com.arnaskv.top_movies.services.movie;

import com.arnaskv.top_movies.dto.MovieResponseDto;
import com.arnaskv.top_movies.dto.TmdbMovieWithGenres;
import com.arnaskv.top_movies.models.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class MovieMapper {

    public MovieResponseDto mapToMovieResponseDto(Movie movie) {
        return MovieResponseDto.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .overview(movie.getOverview())
                .rating(movie.getRating())
                .popularity(movie.getPopularity())
                .posterPath(movie.getPosterPath())
                .build();
    }

    public List<Movie> mapToMovies(List<TmdbMovieWithGenres> moviesWithGenres) {
        return moviesWithGenres.stream()
                .map(mwg -> Movie.builder()
                        .title(mwg.getTmdbMovie().getTitle())
                        .overview(mwg.getTmdbMovie().getOverview())
                        .popularity(mwg.getTmdbMovie().getPopularity())
                        .posterPath(mwg.getTmdbMovie().getPosterPath())
                        .rating(mwg.getTmdbMovie().getVoteAverage())
                        .genres(mwg.getGenres())
                        .build()
                )
                .collect(Collectors.toList());
    }
}
