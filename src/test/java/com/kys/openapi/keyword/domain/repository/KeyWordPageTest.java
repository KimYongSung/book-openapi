package com.kys.openapi.keyword.domain.repository;

import com.kys.openapi.keyword.domain.KeyWordHistory;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
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
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕2"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕3"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕4"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕2"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕3"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕4"));

        // when
        QueryResults<KeyWordHistory> results = keyWordRepositorySupport.findByUserNoOrderByKeywordDesc(userNo, PageRequest.of(0, 10));

        // then
        then(results.getTotal()).isEqualTo(12);
        then(results.getOffset()).isEqualTo(0);
        then(results.getLimit()).isEqualTo(10);

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
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕2"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕3"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕4"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕2"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕3"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕4"));

        // when
        QueryResults<KeyWordHistory> results = keyWordRepositorySupport.findByUserNoOrderByKeywordDesc(userNo, PageRequest.of(1, 10));

        // then
        then(results.getTotal()).isEqualTo(12);
        then(results.getOffset()).isEqualTo(10);
        then(results.getLimit()).isEqualTo(10);

        List<KeyWordHistory> keyWords = results.getResults();

        then(keyWords.get(0)).hasFieldOrPropertyWithValue("userNo", userNo)
                             .hasFieldOrPropertyWithValue("keyWord", "안녕2");

        then(keyWords.get(1)).hasFieldOrPropertyWithValue("userNo", userNo)
                             .hasFieldOrPropertyWithValue("keyWord", "안녕");
    }

    @Test
    @Rollback
    public void 키워드_통계확인() {

        // given
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕1"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕1"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕1"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕2"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕3"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕4"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕5"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕6"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕7"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕8"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕9"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕10"));
        keyWordRepository.save(new KeyWordHistory(userNo, "안녕11"));

        // when
        List<Tuple> tuples = keyWordRepositorySupport.findKeyWordAndCountByGrouping(10);

        // then
        then(tuples).hasSize(10);

        System.out.println(tuples.toString());
    }
}
