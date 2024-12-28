package com.felipesantos.literalura.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.felipesantos.literalura.dto.BookDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class GutendexService {
    private static final String API_URL = "https://gutendex.com/books?search=";

    public List<BookDTO> searchBooks(String title) {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(API_URL + title, String.class);

        try {
            // Parse JSON
            JsonNode root = new com.fasterxml.jackson.databind.ObjectMapper().readTree(response);
            JsonNode results = root.get("results");

            List<BookDTO> books = new ArrayList<>();
            for (JsonNode result : results) {
                String bookTitle = result.get("title").asText();
                String language = result.get("languages").get(0).asText();
                String authorName = result.get("authors").get(0).get("name").asText();

                books.add(new BookDTO(null, bookTitle, language, authorName, "API"));
            }

            return books;
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
