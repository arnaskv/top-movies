package com.arnaskv.top_movies.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class TmdbMovieResponse {
    private List<TmdbMovie> results;

    @Getter
    @ToString
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
