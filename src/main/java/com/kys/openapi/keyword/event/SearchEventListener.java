package com.kys.openapi.keyword.event;

import com.kys.openapi.keyword.cache.KeyWordCacheManager;
import com.kys.openapi.keyword.domain.repository.KeyWordRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 특정 키워드로 조회 요청시 발생 이벤트 처리
 */
@Slf4j
@AllArgsConstructor
@Component
public class SearchEventListener {

    private KeyWordRepository repository;

    private KeyWordCacheManager cacheManager;

    @Transactional
    @EventListener
    @Async
    public void eventListener(KeyWordEvent event) {
        repository.save(event.toEntity());
        cacheManager.addKeyWord(event.keyWord());
    }
}
