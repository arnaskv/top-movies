package com.arnaskv.top_movies.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class TmdbMovieResponseDto {
    private List<TmdbMovie> results;

    @Getter
    public static class TmdbMovie {
        private Long id;
        private String title;
        private String overview;
        private Float popularity;
        @JsonProperty("poster_path")
        private String posterPath;
        @JsonProperty("vote_average")
        private Float voteAverage;
        @JsonProperty("genre_ids")
        private List<Long> genreIds;
    }
}
