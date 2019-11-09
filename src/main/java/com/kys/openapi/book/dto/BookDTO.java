package com.kys.openapi.book.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Data
@NoArgsConstructor
public class BookDTO {

    @NotEmpty(message = "검색 키워드가 누락되었습니다.")
    private String query;

    private Integer page;

    private Integer display;

    @Builder
    public BookDTO(String query, Integer page, Integer display) {
        this.query = query;
        this.page = page;
        this.display = display;
    }

    @AssertTrue(message = "page는 0 ~ 100 범위로 요청 가능합니다.")
    public boolean isValidPage() {
        if (Objects.isNull(page) || page == 0) return true;

        return page <= 100;
    }

    @AssertTrue(message = "display는 0 ~ 50 범위로 요청 가능합니다.")
    public boolean isValidDisplay() {
        if (Objects.isNull(display) || display == 0) return true;

        return display <= 50;
    }
}
