package com.kys.openapi.keyword.service;

import com.kys.openapi.app.result.PageResponse;
import com.kys.openapi.keyword.cache.KeyWordCacheManager;
import com.kys.openapi.keyword.domain.KeyWordHistory;
import com.kys.openapi.keyword.domain.repository.KeyWordRepository;
import com.kys.openapi.keyword.domain.repository.KeyWordRepositorySupport;
import com.kys.openapi.keyword.dto.KeyWordDTO;
import com.querydsl.core.QueryResults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OpenApiKeyWordServiceTest {

    @Autowired
    private KeyWordRepository keyWordRepository;

    @Autowired
    private KeyWordRepositorySupport repositorySupport;

    @Autowired
    private KeyWordCacheManager cacheManager;

    @Autowired
    private OpenApiKeyWordService openApiKeyWordService;

    private static final Long userNo = 100000l;

    @Test
    @Rollback
    public void 검색이력조회(){

        // given
        KeyWordDTO dto = new KeyWordDTO(1, 10);
        Principal principal = Mockito.mock(Principal.class);
        given(principal.getName()).willReturn(String.valueOf(userNo));

        keyWordRepository.save(new KeyWordHistory(userNo, "안녕"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕2"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕3"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕4"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕2"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕3"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕4"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕2"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕3"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕4"));

        // when
        PageResponse<KeyWordHistory> response = openApiKeyWordService.getKeyWordHistory(dto, principal);

        // then
        assertThat(response).hasFieldOrPropertyWithValue("totalSize", 12)
                .hasFieldOrPropertyWithValue("size",10)
                .hasFieldOrPropertyWithValue("page",1);

        assertThat(response.getData()).asList()
                .hasSize(10);

        assertThat(response.getData().get(0)).hasFieldOrPropertyWithValue("userNo", userNo)
                .hasFieldOrPropertyWithValue("keyWord","안녕4");

        assertThat(response.getData().get(1)).hasFieldOrPropertyWithValue("userNo", userNo)
                .hasFieldOrPropertyWithValue("keyWord","안녕3");
    }

    @Test
    @Rollback
    public void 검색이력조회_페이지2(){

        // given
        KeyWordDTO dto = new KeyWordDTO(2, 10);
        Principal principal = Mockito.mock(Principal.class);
        given(principal.getName()).willReturn(String.valueOf(userNo));

        keyWordRepository.save(new KeyWordHistory(userNo, "안녕"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕2"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕3"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕4"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕2"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕3"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕4"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕2"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕3"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕4"));


        // when
        PageResponse<KeyWordHistory> response = openApiKeyWordService.getKeyWordHistory(dto, principal);

        // then
        assertThat(response).hasFieldOrPropertyWithValue("totalSize", 12)
                .hasFieldOrPropertyWithValue("size",2)
                .hasFieldOrPropertyWithValue("page",2);

        assertThat(response.getData()).asList()
                .hasSize(2);

        assertThat(response.getData().get(0)).hasFieldOrPropertyWithValue("userNo", userNo)
                .hasFieldOrPropertyWithValue("keyWord","안녕2");

        assertThat(response.getData().get(1)).hasFieldOrPropertyWithValue("userNo", userNo)
                .hasFieldOrPropertyWithValue("keyWord","안녕");
    }

    public QueryResults<KeyWordHistory> pageHistorySuccess() {
        List<KeyWordHistory> datas = Arrays.asList(new KeyWordHistory(1l, "한국")
                , new KeyWordHistory(1l, "독도")
                , new KeyWordHistory(1l, "고기"));
        return new QueryResults(datas, (long) datas.size(), 1l, datas.size());
    }

}
