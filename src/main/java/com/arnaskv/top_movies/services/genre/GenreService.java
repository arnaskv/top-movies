package com.arnaskv.top_movies.services.genre;

import com.arnaskv.top_movies.models.Genre;
import com.arnaskv.top_movies.repositories.GenreRepository;
import com.arnaskv.top_movies.services.movie.MovieMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;
    private final MovieMapper movieMapper;

    public Genre getById(Long id) {
        return genreRepository.findById(id).orElse(null);
    }

    public List<Genre> getAll() {
        return genreRepository.findAll();
    }

    public void saveAll(List<Genre> genres) {
        genreRepository.saveAll(genres);
    }
}
