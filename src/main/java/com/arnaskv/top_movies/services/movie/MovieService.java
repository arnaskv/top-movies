package com.arnaskv.top_movies.services.movie;

import com.arnaskv.top_movies.models.Movie;
import com.arnaskv.top_movies.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    public void refreshMovies(List<Movie> movies) {
        movieRepository.deleteAll();
        movieRepository.saveAll(movies);
    }
}
