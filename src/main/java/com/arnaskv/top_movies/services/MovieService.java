package com.arnaskv.top_movies.services;

import com.arnaskv.top_movies.models.Movie;
import com.arnaskv.top_movies.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository
                .findAll();
    }
}
