package com.arnaskv.top_movies.services.genre;

import com.arnaskv.top_movies.dto.GenreResponseDto;
import com.arnaskv.top_movies.dto.MovieResponseDto;
import com.arnaskv.top_movies.dto.TmdbGenreResponseDto;
import com.arnaskv.top_movies.models.Genre;
import com.arnaskv.top_movies.repositories.GenreRepository;
import com.arnaskv.top_movies.services.movie.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final RestClient restClient;
    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;
    private final MovieService movieService;

    public GenreResponseDto getById(Long id) {
        Genre genreFound = genreRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Genre with ID: " + id + " not found"));

        List<MovieResponseDto> movieDtos = movieService.getMoviesByTmdbGenreId(genreFound.getTmdbId());

        return genreMapper.mapToGenreResponseDto(genreFound, movieDtos);
    }

    public List<Genre> getAll() {
        return genreRepository.findAll();
    }

    public void saveAll(List<Genre> genres) {
        genreRepository.saveAll(genres);
    }

    public Genre getByTmdbGenreId(Long tmdbId) {
        Optional<Genre> genreFound = genreRepository.findByTmdbId(tmdbId);

        if (genreFound.isPresent()) {
            return genreFound.get();
        }

        getTmdbMovieGenres();
        genreFound = genreRepository.findByTmdbId(tmdbId);

        if (genreFound.isEmpty()) {
            throw new RuntimeException("Genre with TMDB ID: " + tmdbId + " not found");
        }

        return genreFound.get();
    }

    public List<Genre> getTmdbMovieGenres() {
        ResponseEntity<TmdbGenreResponseDto> response = restClient.get()
                .uri("https://api.themoviedb.org/3/genre/movie/list?language=en")
                .retrieve()
                .toEntity(TmdbGenreResponseDto.class);

        if (response.getBody() != null) {
            List<Genre> genres = genreMapper.mapToGenres(response.getBody());
            List<Genre> filteredGenres = filterNewGenres(genres);
            saveAll(filteredGenres);
            return filteredGenres;
        }

        return List.of();
    }

    public List<Genre> filterNewGenres(List<Genre> genres) {
        List<String> existingGenreNames = getAll().stream()
                .map(Genre::getName)
                .toList();
        return genres.stream()
                .filter(genre -> !existingGenreNames.contains(genre.getName()))
                .collect(Collectors.toList());
    }

}
