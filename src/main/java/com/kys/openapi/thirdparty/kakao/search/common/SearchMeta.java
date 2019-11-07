package com.kys.openapi.thirdparty.kakao.search.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * 검색 메타 정보
 */
@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SearchMeta {

    @JsonProperty(value = "is_end")
    private boolean isEnd;

    @JsonProperty(value = "pageable_count")
    private int pageableCount;

    @JsonProperty(value = "total_count")
    private int totalCount;

}
