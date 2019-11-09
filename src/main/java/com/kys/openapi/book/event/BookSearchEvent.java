package com.kys.openapi.book.event;

import com.kys.openapi.keyword.event.KeyWordEvent;
import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class BookSearchEvent implements KeyWordEvent {

    private Long userNo;

    private String keyWord;

    @Override
    public Long userNo() {
        return userNo;
    }

    @Override
    public String keyWord() {
        return keyWord;
    }
}
