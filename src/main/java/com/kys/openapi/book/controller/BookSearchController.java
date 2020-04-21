package com.kys.openapi.book.controller;

import com.kys.openapi.app.result.Response;
import com.kys.openapi.book.dto.BookDTO;
import com.kys.openapi.book.dto.BookDetailDTO;
import com.kys.openapi.book.service.BookSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/search")
public class BookSearchController {

    private final BookSearchService bookService;

    @GetMapping(value = "/book")
    public ResponseEntity<Response> searchBooks(@Valid BookDTO dto, Principal principal) {
        return ResponseEntity.ok(bookService.searchBooks(dto, principal));
    }

    @GetMapping(value = "/book/detail")
    public ResponseEntity<Response> searchBookDetail(@Valid BookDetailDTO dto, Principal principal) {
        return ResponseEntity.ok(bookService.searchBookDetail(dto, principal));
    }
}
