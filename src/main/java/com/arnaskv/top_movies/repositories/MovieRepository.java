package com.arnaskv.top_movies.repositories;

import com.arnaskv.top_movies.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByGenresTmdbIdOrderByRatingDesc(Long tmdbId);

    List<Movie> findAllByOrderByRatingDesc();
}
