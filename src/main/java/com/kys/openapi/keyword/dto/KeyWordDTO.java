package com.kys.openapi.keyword.dto;

import lombok.Builder;
import org.springframework.data.domain.PageRequest;

import javax.validation.constraints.AssertTrue;
import java.util.Objects;

public class KeyWordDTO {

    private Integer start;

    private Integer length;

    @Builder
    public KeyWordDTO(Integer start, Integer length) {
        this.start = start;
        this.length = length;
    }

    @AssertTrue(message = "start는 1 ~ 100 범위로 요청 가능합니다.")
    public boolean isValidPage() {
        if (Objects.isNull(start) || start == 0) return false;

        return start <= 100;
    }

    @AssertTrue(message = "length는 1 ~ 50 범위로 요청 가능합니다.")
    public boolean isValidDisplay() {
        if (Objects.isNull(length) || length == 0) return false;

        return length <= 50;
    }

    public PageRequest toPageable() {
        return PageRequest.of(start-1, length);
    }
}
