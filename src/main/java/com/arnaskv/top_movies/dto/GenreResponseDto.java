package com.arnaskv.top_movies.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Data
public class GenreResponseDto {
    @NotBlank
    private Long id;
    @NotBlank
    private String name;
    @NotEmpty
    private List<MovieResponseDto> movies;
}
