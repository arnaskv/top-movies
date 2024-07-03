package com.arnaskv.top_movies.services.tmdb;

import com.arnaskv.top_movies.dto.TmdbGenreResponse;
import com.arnaskv.top_movies.dto.TmdbMovieResponse;
import com.arnaskv.top_movies.models.Genre;
import com.arnaskv.top_movies.models.Movie;
import com.arnaskv.top_movies.services.genre.GenreMapper;
import com.arnaskv.top_movies.services.genre.GenreService;
import com.arnaskv.top_movies.services.movie.MovieMapper;
import com.arnaskv.top_movies.services.movie.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TmdbService {
    private final RestClient restClient;
    private final GenreMapper genreMapper;
    private final MovieMapper movieMapper;
    private final GenreService genreService;
    private final MovieService movieService;

    public List<Genre> filterNewGenres(List<Genre> genres) {
        List<String> existingGenreNames = genreService.getAll().stream()
                .map(Genre::getName)
                .toList();
        return genres.stream()
                .filter(genre -> !existingGenreNames.contains(genre.getName()))
                .collect(Collectors.toList());
    }

    public List<Genre> getAllMovieGenres() {
        ResponseEntity<TmdbGenreResponse> response = restClient.get()
                .uri("https://api.themoviedb.org/3/genre/movie/list?language=en")
                .retrieve()
                .toEntity(TmdbGenreResponse.class);

        if (response.getBody() != null) {
            List<Genre> genres = genreMapper.mapToGenres(response.getBody());
            List<Genre> filteredGenres = filterNewGenres(genres);
            genreService.saveAll(filteredGenres);
            return filteredGenres;
        }

        return List.of();
    }

    public List<Movie> getTop250Movies() {
        List<Movie> movies = new ArrayList<>();
        int page = 1;

        while (movies.size() < 250) {
            List<Movie> pageMovies = getTopMovies(page);
            if (pageMovies.isEmpty()) {
                break;
            }
            movies.addAll(pageMovies);
            page++;
        }

        List<Movie> moviesNeeded = movies.subList(0, 250);

        movieService.refreshMovies(moviesNeeded);
        return moviesNeeded;
    }

    public List<Movie> getTopMovies(int page) {
        ResponseEntity<TmdbMovieResponse> response = restClient.get()
                .uri("/movie/top_rated?language=en-US&page={page}", page)
                .retrieve()
                .toEntity(TmdbMovieResponse.class);

        if (response.getBody() != null) {
            return movieMapper.mapToMovies(response.getBody());
        }

        return List.of();
    }

    @Scheduled(cron = "@weekly")
    public void refreshTop250Movies() {
        getTop250Movies();
        getAllMovieGenres();
    }
}
