package com.kys.openapi.keyword.service;

import com.kys.openapi.app.result.DataResponse;
import com.kys.openapi.app.result.PageResponse;
import com.kys.openapi.keyword.domain.KeyWordHistory;
import com.kys.openapi.keyword.dto.KeyWordCallInfo;
import com.kys.openapi.keyword.dto.KeyWordDTO;

import java.security.Principal;
import java.util.List;

public interface KeyWordService {

    /**
     * top10 키워드 조회
     *
     * @return top10 정보
     */
    DataResponse<List<KeyWordCallInfo>> getKeyWordByTop10();

    /**
     * 키워드 검색 이력 조회
     *
     * @param keyWordDTO 요청파라미터
     * @param principal  인증정보
     * @return 검색 이력
     */
    PageResponse<KeyWordHistory> getKeyWordHistory(KeyWordDTO keyWordDTO, Principal principal);
}
