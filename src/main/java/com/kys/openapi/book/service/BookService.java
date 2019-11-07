package com.kys.openapi.book.service;

import com.kys.openapi.book.dto.BookDTO;
import com.kys.openapi.book.dto.BookInfo;

import java.util.List;

public interface BookService {

    /**
     * 키워드에 일치하는 책 검색
     * @param dto
     * @return
     */
    List<BookInfo> searchBooks(BookDTO dto);

    /**
     * 책 상세 검색
     * @param dto
     * @return
     */
    BookInfo searchBookDetail(BookDTO dto);
}
