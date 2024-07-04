package com.arnaskv.top_movies.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class MovieResponseDto {
    private Long id;
    private String title;
    private String overview;
    private Float popularity;
    private String posterPath;
    private Float rating;
}
