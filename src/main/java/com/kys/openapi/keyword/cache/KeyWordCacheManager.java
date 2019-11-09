package com.kys.openapi.keyword.cache;

import com.kys.openapi.keyword.dto.KeyWordCallInfo;

import java.util.List;

public interface KeyWordCacheManager {

    /**
     * 키워드 저장
     *
     * @param keyword
     */
    void addKeyWord(String keyword);

    /**
     * 검색 키워드 top10 조회
     *
     * @return
     */
    List<KeyWordCallInfo> getTop10();

}
