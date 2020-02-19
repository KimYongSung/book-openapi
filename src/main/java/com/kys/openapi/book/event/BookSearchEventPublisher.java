package com.kys.openapi.book.event;

import com.kys.openapi.book.dto.BookDTO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.security.Principal;

/**
 * 책 검색 이벤트 발행자
 */
@Component
@RequiredArgsConstructor
public class BookSearchEventPublisher {

    private final ApplicationEventPublisher publisher;

    /**
     * 이벤트 발행
     *
     * @param bookDTO
     * @param principal
     */
    public void publishEvent(BookDTO bookDTO, Principal principal) {
        publisher.publishEvent(new BookSearchEvent(Long.parseLong(principal.getName()), bookDTO.getSearch()));
    }
}
