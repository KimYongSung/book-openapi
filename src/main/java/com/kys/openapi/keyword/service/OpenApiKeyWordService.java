package com.kys.openapi.keyword.service;

import com.kys.openapi.app.result.DataResponse;
import com.kys.openapi.app.result.PageResponse;
import com.kys.openapi.keyword.cache.ConcurrentMapKeyWordCacheManager;
import com.kys.openapi.keyword.domain.KeyWordHistory;
import com.kys.openapi.keyword.domain.repository.KeyWordRepositorySupport;
import com.kys.openapi.keyword.dto.KeyWordCallInfo;
import com.kys.openapi.keyword.dto.KeyWordDTO;
import com.querydsl.core.QueryResults;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@AllArgsConstructor
public class OpenApiKeyWordService implements KeyWordService {

    private ConcurrentMapKeyWordCacheManager manager;

    private KeyWordRepositorySupport keyWordRepositorySupport;

    @Override
    public DataResponse<List<KeyWordCallInfo>> getKeyWordByTop10() {
        return DataResponse.success(manager.getTop10());
    }

    @Override
    public PageResponse<KeyWordHistory> getKeyWordHistory(KeyWordDTO keyWordDTO, Principal principal) {

        Long userNo = Long.parseLong(principal.getName());

        QueryResults<KeyWordHistory> queryResult = keyWordRepositorySupport.findByUserNoOrderByKeywordDesc(userNo, keyWordDTO.toPageable());

        return PageResponse.success(queryResult);
    }
}
