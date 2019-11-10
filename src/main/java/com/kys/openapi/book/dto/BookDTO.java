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
    private String search;

    private Integer start;

    private Integer length;

    @Builder
    public BookDTO(String search, Integer start, Integer length) {
        this.search = search;
        this.start = start;
        this.length = length;
    }

    @AssertTrue(message = "start는 0 ~ 100 범위로 요청 가능합니다.")
    public boolean isValidPage() {
        if (Objects.isNull(start) || start == 0) return true;

        return start <= 100;
    }

    @AssertTrue(message = "length는 0 ~ 50 범위로 요청 가능합니다.")
    public boolean isValidDisplay() {
        if (Objects.isNull(length) || length == 0) return true;

        return length <= 50;
    }
}
