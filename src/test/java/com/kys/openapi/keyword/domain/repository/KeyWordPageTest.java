package com.kys.openapi.keyword.domain.repository;

import com.kys.openapi.keyword.domain.KeyWordHistory;
import com.querydsl.core.QueryResults;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KeyWordPageTest {

    @Autowired
    private KeyWordRepository keyWordRepository;

    @Autowired
    private KeyWordRepositorySupport keyWordRepositorySupport;

    private static final Long userNo = 100000l;

    @Test
    @Rollback
    public void 키워드_페이징_확인_0페이지(){

        // given
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕2"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕3"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕4"));

        // when
        QueryResults<KeyWordHistory> results = keyWordRepositorySupport.findByUserNoOrderByKeywordDesc(userNo, PageRequest.of(0, 2));

        // then
        then(results.getTotal()).isEqualTo(4);
        then(results.getOffset()).isEqualTo(0);
        then(results.getLimit()).isEqualTo(2);

        List<KeyWordHistory> keyWords = results.getResults();

        then(keyWords.get(0)).hasFieldOrPropertyWithValue("userNo", userNo)
                             .hasFieldOrPropertyWithValue("keyWord", "안녕4");

        then(keyWords.get(1)).hasFieldOrPropertyWithValue("userNo", userNo)
                             .hasFieldOrPropertyWithValue("keyWord", "안녕3");
    }

    @Test
    @Rollback
    public void 키워드_페이징_확인_1페이지(){

        // given
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕2"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕3"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕4"));

        // when
        QueryResults<KeyWordHistory> results = keyWordRepositorySupport.findByUserNoOrderByKeywordDesc(userNo, PageRequest.of(1, 2));

        // then
        then(results.getTotal()).isEqualTo(4);
        then(results.getOffset()).isEqualTo(2);
        then(results.getLimit()).isEqualTo(2);

        List<KeyWordHistory> keyWords = results.getResults();

        then(keyWords.get(0)).hasFieldOrPropertyWithValue("userNo", userNo)
                             .hasFieldOrPropertyWithValue("keyWord", "안녕2");

        then(keyWords.get(1)).hasFieldOrPropertyWithValue("userNo", userNo)
                             .hasFieldOrPropertyWithValue("keyWord", "안녕");
    }
}
