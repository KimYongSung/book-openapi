package com.kys.openapi.keyword.service;

import com.kys.openapi.app.result.Response;
import com.kys.openapi.keyword.cache.KeyWordCacheManager;
import com.kys.openapi.keyword.domain.KeyWordHistory;
import com.kys.openapi.keyword.domain.repository.KeyWordRepositorySupport;
import com.kys.openapi.keyword.dto.KeyWordDTO;
import com.querydsl.core.QueryResults;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@AllArgsConstructor
public class OpenApiKeyWordService implements KeyWordService {

    private KeyWordCacheManager cacheManager;

    private KeyWordRepositorySupport keyWordRepositorySupport;

    @Override
    public Response getKeyWordByTop10() {
        return Response.success(cacheManager.getTop10());
    }

    @Override
    public Response getKeyWordHistory(KeyWordDTO keyWordDTO, Principal principal) {

        Long userNo = Long.parseLong(principal.getName());

        PageRequest pageable = keyWordDTO.toPageable();

        QueryResults<KeyWordHistory> queryResult = keyWordRepositorySupport.findByUserNoOrderByKeywordDesc(userNo, pageable);

        return Response.success(queryResult, pageable);
    }
}
