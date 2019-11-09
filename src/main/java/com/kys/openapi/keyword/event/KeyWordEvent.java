package com.kys.openapi.keyword.event;

import com.kys.openapi.keyword.domain.KeyWordHistory;

/**
 * 키워드 이벤트 수신용 marker interface
 */
public interface KeyWordEvent {

    Long userNo();

    String keyWord();

    default KeyWordHistory toEntity() {
        return KeyWordHistory.builder()
                .keyWord(keyWord())
                .userNo(userNo())
                .build();
    }

}
