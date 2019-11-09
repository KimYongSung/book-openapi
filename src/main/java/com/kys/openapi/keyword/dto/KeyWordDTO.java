package com.kys.openapi.keyword.dto;

import lombok.Builder;
import org.springframework.data.domain.PageRequest;

import javax.validation.constraints.AssertTrue;
import java.util.Objects;

public class KeyWordDTO {

    private Integer page;

    private Integer display;

    @Builder
    public KeyWordDTO(Integer page, Integer display) {
        this.page = page;
        this.display = display;
    }

    @AssertTrue(message = "page는 1 ~ 100 범위로 요청 가능합니다.")
    public boolean isValidPage() {
        if (Objects.isNull(page) || page == 0) return false;

        return page <= 100;
    }

    @AssertTrue(message = "display는 1 ~ 50 범위로 요청 가능합니다.")
    public boolean isValidDisplay() {
        if (Objects.isNull(display) || display == 0) return false;

        return display <= 50;
    }

    public PageRequest toPageable() {
        return PageRequest.of(page, display);
    }
}
