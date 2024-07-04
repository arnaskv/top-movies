package com.arnaskv.top_movies.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class TmdbGenreResponseDto {
    private List<TmdbGenre> genres;

    @Getter
     public static class TmdbGenre {
         private Long id;
         private String name;
     }
}
