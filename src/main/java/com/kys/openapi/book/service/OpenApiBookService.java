package com.kys.openapi.book.service;

import com.kys.openapi.book.dto.BookDTO;
import com.kys.openapi.book.dto.BookInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OpenApiBookService implements BookService {

    @Override
    public List<BookInfo> searchBooks(BookDTO dto) {
        return null;
    }

    @Override
    public BookInfo searchBookDetail(BookDTO dto) {
        return null;
    }
}
