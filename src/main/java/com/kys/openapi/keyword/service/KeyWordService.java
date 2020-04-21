package com.kys.openapi.keyword.service;

import com.kys.openapi.app.result.Response;
import com.kys.openapi.keyword.dto.KeyWordDTO;

import java.security.Principal;

public interface KeyWordService {

    /**
     * top10 키워드 조회
     *
     * @return top10 정보
     */
    Response getKeyWordByTop10();

    /**
     * 키워드 검색 이력 조회
     *
     * @param keyWordDTO 요청파라미터
     * @param principal  인증정보
     * @return 검색 이력
     */
    Response getKeyWordHistory(KeyWordDTO keyWordDTO, Principal principal);
}
