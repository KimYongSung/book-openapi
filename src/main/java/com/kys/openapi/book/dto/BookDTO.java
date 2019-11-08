package com.kys.openapi.book.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookDTO {

    private String query;

    private Integer page;

    private Integer display;

    private String isbn;

    @Builder
    public BookDTO(String query, Integer page, Integer display, String isbn) {
        this.query = query;
        this.page = page;
        this.display = display;
        this.isbn = isbn;
    }
}
