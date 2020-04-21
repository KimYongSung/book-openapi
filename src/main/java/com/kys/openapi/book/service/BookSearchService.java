package com.kys.openapi.book.service;

import com.kys.openapi.app.result.Response;
import com.kys.openapi.book.dto.BookDTO;
import com.kys.openapi.book.dto.BookDetailDTO;

import java.security.Principal;

public interface BookSearchService {

    /**
     * 키워드에 일치하는 책 검색
     * @param dto 요청파라미터
     * @return 일치하는 검색 결과
     */
    Response searchBooks(BookDTO dto, Principal principal);

    /**
     * 책 상세 검색
     * @param dto 요청파라미터
     * @return 일치하는 검색 결과
     */
    Response searchBookDetail(BookDetailDTO dto, Principal principal);
}
