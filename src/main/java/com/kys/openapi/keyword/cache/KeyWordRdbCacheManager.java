package com.kys.openapi.keyword.cache;

import com.kys.openapi.keyword.domain.repository.KeyWordRepositorySupport;
import com.kys.openapi.keyword.dto.KeyWordCallInfo;
import com.querydsl.core.Tuple;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.stream.Collectors;

public class KeyWordRdbCacheManager implements KeyWordCacheManager {

    private KeyWordRepositorySupport keyWordRepositorySupport;

    public KeyWordRdbCacheManager(KeyWordRepositorySupport keyWordRepositorySupport) {
        this.keyWordRepositorySupport = keyWordRepositorySupport;
    }

    private List<KeyWordCallInfo> top10;

    @Override
    public void addKeyWord(String keyword) {
        // skip
    }

    @Override
    public List<KeyWordCallInfo> getTop10() {
        return top10;
    }

    /**
     * @return
     */
    private List<KeyWordCallInfo> newTop10() {
        return keyWordRepositorySupport.findKeyWordAndCountByGrouping(10)
                .stream()
                .map(this::tupleToKeyWordCallInfo)
                .sorted()
                .collect(Collectors.toList());
    }

    private KeyWordCallInfo tupleToKeyWordCallInfo(Tuple tuple) {
        String keyWord = tuple.get(0, String.class);
        Long callCount = tuple.get(1, Long.class);
        return new KeyWordCallInfo(keyWord, callCount.intValue());
    }

    @Scheduled(fixedRate = 1000 * 30)
    public void reload() {
        top10 = newTop10();
    }
}
