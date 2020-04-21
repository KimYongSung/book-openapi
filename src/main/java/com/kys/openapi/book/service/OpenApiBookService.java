package com.kys.openapi.book.service;

import com.kys.openapi.app.result.Response;
import com.kys.openapi.book.dto.BookDTO;
import com.kys.openapi.book.dto.BookDetailDTO;
import com.kys.openapi.book.dto.BookInfo;
import com.kys.openapi.book.event.BookSearchEventPublisher;
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

    private NaverOpenApiSearch naverSearch;

    private KakaoOpenApiSearch kakaoSearch;

    private BookSearchEventPublisher publisher;

    @Override
    public Response searchBooks(BookDTO dto, Principal principal) {

        Response res = routingOpenApi.call(dto, kakaoSearch::bookSearch, naverSearch::bookSearch);

        publisher.publishEvent(dto, principal);

        return res;
    }

    @Override
    public Response searchBookDetail(BookDetailDTO dto, Principal principal) {
        return routingOpenApi.call(dto, kakaoSearch::bookDetailSearch, naverSearch::bookDetailSearch);
    }
}
