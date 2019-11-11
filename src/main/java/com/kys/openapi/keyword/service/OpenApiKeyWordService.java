package com.kys.openapi.keyword.service;

import com.kys.openapi.app.result.DataResponse;
import com.kys.openapi.app.result.PageResponse;
import com.kys.openapi.keyword.cache.KeyWordCacheManager;
import com.kys.openapi.keyword.domain.KeyWordHistory;
import com.kys.openapi.keyword.domain.repository.KeyWordRepositorySupport;
import com.kys.openapi.keyword.dto.KeyWordCallInfo;
import com.kys.openapi.keyword.dto.KeyWordDTO;
import com.querydsl.core.QueryResults;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@AllArgsConstructor
public class OpenApiKeyWordService implements KeyWordService {

    private KeyWordCacheManager cacheManager;

    private KeyWordRepositorySupport keyWordRepositorySupport;

    @Override
    public DataResponse<List<KeyWordCallInfo>> getKeyWordByTop10() {
        return DataResponse.success(cacheManager.getTop10());
    }

    @Override
    public PageResponse<KeyWordHistory> getKeyWordHistory(KeyWordDTO keyWordDTO, Principal principal) {

        Long userNo = Long.parseLong(principal.getName());
        PageRequest pageable = keyWordDTO.toPageable();

        QueryResults<KeyWordHistory> queryResult = keyWordRepositorySupport.findByUserNoOrderByKeywordDesc(userNo, pageable);

        return PageResponse.success(queryResult, pageable);
    }
}
