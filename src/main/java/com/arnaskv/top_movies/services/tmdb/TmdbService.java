package com.arnaskv.top_movies.services.tmdb;

import com.arnaskv.top_movies.dto.TmdbMovieResponseDto;
import com.arnaskv.top_movies.dto.TmdbMovieWithGenresDto;
import com.arnaskv.top_movies.models.Movie;
import com.arnaskv.top_movies.repositories.MovieRepository;
import com.arnaskv.top_movies.services.genre.GenreService;
import com.arnaskv.top_movies.services.movie.MovieMapper;
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
    private final MovieMapper movieMapper;
    private final GenreService genreService;
    private final MovieRepository movieRepository;

    @Scheduled(cron = "@weekly")
    public void refreshTop250Movies() {
        genreService.getTmdbMovieGenres();
        getTmdbTop250Movies();
    }

    public void refreshMovies(List<Movie> newMovies) {
        movieRepository.deleteAllMovieGenreRelationships();
        movieRepository.deleteAll();
        movieRepository.saveAll(newMovies);
    }

    public List<Movie> getTmdbTop250Movies() {
        List<Movie> movies = new ArrayList<>();
        int page = 1;

        while (movies.size() < 250) {
            List<Movie> pageMovies = getTmdbTopMovies(page);
            if (pageMovies.isEmpty()) {
                break;
            }
            movies.addAll(pageMovies);
            page++;
        }

        List<Movie> moviesNeeded = movies.subList(0, 250);

        refreshMovies(moviesNeeded);
        return moviesNeeded;
    }

    public List<Movie> getTmdbTopMovies(int page) {
        try {
        ResponseEntity<TmdbMovieResponseDto> response = restClient.get()
                .uri("/movie/top_rated?language=en-US&page={page}", page)
                .retrieve()
                .toEntity(TmdbMovieResponseDto.class);

        TmdbMovieResponseDto tmdbMovieResponseDto = response.getBody();
        if(tmdbMovieResponseDto == null) {
            throw new RuntimeException("Tmdb top rated movies not found");
        }

        List<TmdbMovieWithGenresDto> moviesWithGenres = tmdbMovieResponseDto.getResults().stream()
                .map(tmdbMovie -> new TmdbMovieWithGenresDto(
                        tmdbMovie,
                        tmdbMovie.getGenreIds().stream()
                                .map(genreService::getByTmdbGenreId)
                                .collect(Collectors.toSet())
                ))
                .collect(Collectors.toList());

        return movieMapper.mapToMovies(moviesWithGenres);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching tmdb top movies", e);
        }
    }
}
