package com.arnaskv.top_movies.repositories;

import com.arnaskv.top_movies.models.Movie;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByGenresTmdbIdOrderByRatingDesc(Long tmdbId);

    List<Movie> findAllByOrderByRatingDesc();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM movie_genre", nativeQuery = true)
    void deleteAllMovieGenreRelationships();
}
