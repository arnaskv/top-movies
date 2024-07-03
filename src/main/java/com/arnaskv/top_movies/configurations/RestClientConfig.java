package com.arnaskv.top_movies.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {
    @Bean
    public RestClient restClient() {
        String TMDB_ACCESS_TOKEN = System.getenv("TMDB_ACCESS_TOKEN");

        if (TMDB_ACCESS_TOKEN == null || TMDB_ACCESS_TOKEN.isEmpty()) {
            throw new RuntimeException("TMDB_ACCESS_TOKEN not set");
        }

        return RestClient.builder()
                .baseUrl("https://api.themoviedb.org/3")
                .defaultHeader("accept", "application/json")
                .defaultHeader("Authorization", "Bearer " + TMDB_ACCESS_TOKEN)
                .build();
    }
}
