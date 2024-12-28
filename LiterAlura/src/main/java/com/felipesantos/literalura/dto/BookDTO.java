package com.felipesantos.literalura.dto;

public class BookDTO {
    private Long id; // Pode ser null para livros vindos da API
    private String title;
    private String language;
    private String authorName;
    private String source; // "API" ou "Database"

    // Construtores
    public BookDTO(Long id, String title, String language, String authorName, String source) {
        this.id = id;
        this.title = title;
        this.language = language;
        this.authorName = authorName;
        this.source = source;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }
    public String getAuthorName() { return authorName; }
    public void setAuthorName(String authorName) { this.authorName = authorName; }
    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    @Override
    public String toString() {
        return "BookDTO{" +
                "id=" + (id != null ? id : "N/A") +
                ", title='" + title + '\'' +
                ", language='" + language + '\'' +
                ", authorName='" + authorName + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}

