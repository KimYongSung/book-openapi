package com.kys.openapi.book.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class BookDetailDTO {

    @NotEmpty(message = "isbn이 누락되었습니다.")
    private String isbn;

    public BookDetailDTO(String isbn) {
        this.isbn = isbn;
    }
}
