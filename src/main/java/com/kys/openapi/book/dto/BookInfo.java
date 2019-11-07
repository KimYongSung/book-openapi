package com.kys.openapi.book.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 책 정보
 */
@Data
@NoArgsConstructor
public class BookInfo {

    private String title;
    private String thumbnail;
    private String contents;
    private String isbn;
    private String author;
    private String publisher;
    private String datetime;
    private Integer price;
    private Integer salePrice;

    @Builder
    public BookInfo(String title, String thumbnail, String contents, String isbn,
                    String author, String publisher, String datetime, Integer price, Integer salePrice) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.contents = contents;
        this.isbn = isbn;
        this.author = author;
        this.publisher = publisher;
        this.datetime = datetime;
        this.price = price;
        this.salePrice = salePrice;
    }
}
