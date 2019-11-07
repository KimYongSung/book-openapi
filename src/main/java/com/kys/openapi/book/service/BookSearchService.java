package com.kys.openapi.book.service;

import com.kys.openapi.app.result.DataResponse;
import com.kys.openapi.app.result.ListResponse;
import com.kys.openapi.book.dto.BookDTO;
import com.kys.openapi.book.dto.BookInfo;

import java.security.Principal;

public interface BookSearchService {

    /**
     * 키워드에 일치하는 책 검색
     * @param dto
     * @return
     */
    ListResponse<BookInfo> searchBooks(BookDTO dto, Principal principal);

    /**
     * 책 상세 검색
     * @param dto
     * @return
     */
    DataResponse<BookInfo> searchBookDetail(BookDTO dto, Principal principal);
}
