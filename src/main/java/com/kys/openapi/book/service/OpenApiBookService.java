package com.kys.openapi.book.service;

import com.kys.openapi.app.result.DataResponse;
import com.kys.openapi.app.result.ListResponse;
import com.kys.openapi.book.dto.BookDTO;
import com.kys.openapi.book.dto.BookInfo;
import com.kys.openapi.thirdparty.network.RoutingOpenApi;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Slf4j
@Service
@AllArgsConstructor
public class OpenApiBookService implements BookSearchService {

    private RoutingOpenApi routingOpenApi;

    @Override
    public ListResponse<BookInfo> searchBooks(BookDTO dto, Principal principal) {
        return routingOpenApi.call(dto,bookDTO -> null, bookDTO -> null);
    }

    @Override
    public DataResponse<BookInfo> searchBookDetail(BookDTO dto, Principal principal) {
        return routingOpenApi.call(dto,bookDTO -> null, bookDTO -> null);
    }
}
