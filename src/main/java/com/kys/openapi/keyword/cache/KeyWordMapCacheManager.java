package com.kys.openapi.keyword.cache;

import com.kys.openapi.app.util.ExecuteTimer;
import com.kys.openapi.keyword.dto.KeyWordCallInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
@Component
public class KeyWordMapCacheManager implements KeyWordCacheManager {

    private ConcurrentHashMap<String, KeyWordCallInfo> cache = new ConcurrentHashMap<>();

    private List<KeyWordCallInfo> top10;

    @Override
    public List<KeyWordCallInfo> getTop10() {
        return cache.size() < 100 ? newTop10() : top10;
    }

    /**
     * 키워드 추가
     *
     * @param keyword
     */
    @Override
    public void addKeyWord(String keyword) {
        cache.putIfAbsent(keyword, new KeyWordCallInfo(keyword));
        cache.computeIfPresent(keyword, (s, info) -> info.update());
    }

    /**
     * @return
     */
    private List<KeyWordCallInfo> newTop10() {
        return cache.values()
                .stream()
                .sorted()
                .limit(10)
                .collect(Collectors.toList());
    }

    @Scheduled(fixedRate = 1000 * 60)
    public void rebuild() {
        ExecuteTimer timer = ExecuteTimer.newTimer();

        timer.start();
        top10 = newTop10();
        timer.end();

        log.info("top10 rebuild finish - {} ms", timer.calc());
    }
}
