package com.kys.openapi.book.controller;

import com.kys.openapi.app.result.DataResponse;
import com.kys.openapi.app.result.ListResponse;
import com.kys.openapi.book.dto.BookDTO;
import com.kys.openapi.book.dto.BookInfo;
import com.kys.openapi.book.service.BookSearchService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/search")
public class BookSearchController {

    private BookSearchService bookService;

    @GetMapping(value = "/books")
    public ResponseEntity<ListResponse<BookInfo>> searchBooks(@RequestBody BookDTO dto, Principal principal){
        return ResponseEntity.ok(bookService.searchBooks(dto, principal));
    }

    @GetMapping(value = "/book")
    public ResponseEntity<DataResponse<BookInfo>> searchBookDetail(@RequestBody BookDTO dto, Principal principal){
        return ResponseEntity.ok(bookService.searchBookDetail(dto, principal));
    }
}
