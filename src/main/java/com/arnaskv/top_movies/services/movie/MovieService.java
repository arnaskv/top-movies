package com.arnaskv.top_movies.services.movie;

import com.arnaskv.top_movies.dto.MovieResponseDto;
import com.arnaskv.top_movies.models.Movie;
import com.arnaskv.top_movies.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    public List<Movie> getAll() {
        return movieRepository.findAllByOrderByRatingDesc();
    }

    public List<MovieResponseDto> getMoviesByTmdbGenreId(Long tmdbId) {
        List<Movie> moviesFound = movieRepository.findByGenresTmdbIdOrderByRatingDesc(tmdbId);
        return moviesFound.stream()
                .map(movieMapper::mapToMovieResponseDto)
                .collect(Collectors.toList());
    }
}
