package com.kys.openapi.thirdparty.kakao.search.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * 검색 메타 정보
 */
@ToString
@Getter
@NoArgsConstructor
public class SearchMeta {

    @JsonProperty(value = "is_end")
    private boolean isEnd;

    @JsonProperty(value = "pageable_count")
    private int pageableCount;

    @JsonProperty(value = "total_count")
    private int totalCount;

    @Builder
    public SearchMeta(boolean isEnd, int pageableCount, int totalCount) {
        this.isEnd = isEnd;
        this.pageableCount = pageableCount;
        this.totalCount = totalCount;
    }
}
