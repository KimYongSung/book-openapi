package com.kys.openapi.book.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookDTO {

    private String query;

    private Integer start;

    private Integer end;

    private String isbn;

    @Builder
    public BookDTO(String query, Integer start, Integer end, String isbn) {
        this.query = query;
        this.start = start;
        this.end = end;
        this.isbn = isbn;
    }
}
