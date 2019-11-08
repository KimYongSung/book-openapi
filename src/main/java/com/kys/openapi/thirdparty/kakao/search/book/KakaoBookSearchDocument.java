package com.kys.openapi.thirdparty.kakao.search.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@ToString
@Getter
public class KakaoBookSearchDocument {

    private String title;
    private String contents;
    private String url;
    private String isbn;

    @JsonProperty(value = "datetime")
    private String dateTime;
    private List<String> authors;
    private String publisher;
    private List<String> translators;
    private Integer price;

    @JsonProperty(value = "sale_price")
    private Integer salePrice;
    private String thumbnail;
    private String status;

    public List<String> getIsbns(){

        if(Objects.isNull(isbn)) return Collections.emptyList();

        return Arrays.asList(isbn.split(" "));
    }

    public String getAuthorsByString(){

        if(Objects.isNull(authors)) return "";

        return String.join(", ", authors);
    }
}
