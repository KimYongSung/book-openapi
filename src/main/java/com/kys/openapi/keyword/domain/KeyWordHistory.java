package com.kys.openapi.keyword.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 키워드 이력 정보
 */
@Getter
@ToString
@Entity
@Table(name = "KEY_WORD_HISTORY", indexes = {@Index(name = "IDX_KEY_WORD_HISTORY_01", columnList = "USER_NO")})
@NoArgsConstructor
public class KeyWordHistory {

    @Id
    @GeneratedValue
    @Column(name = "KEY_WORD_NO", unique = true)
    private Long keyWordNo;

    @Column(name = "USER_NO", nullable = false)
    private Long userNo;

    @Column(name = "KEY_WORD", length = 50, nullable = false)
    private String keyWord;

    @Column(name = "SEARCH_DT")
    private LocalDateTime searchDt;

    @Builder
    public KeyWordHistory(Long userNo, String keyWord) {
        this.userNo = userNo;
        this.keyWord = keyWord;
        this.searchDt = LocalDateTime.now();
    }
}
