package com.kys.openapi.keyword.cache;

import com.kys.openapi.keyword.dto.KeyWordCallInfo;
import org.assertj.core.data.Index;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ConcurrentMapKeyWordCacheManagerTest {

    private static ConcurrentMapKeyWordCacheManager cacheManager = new ConcurrentMapKeyWordCacheManager();

    @BeforeClass
    public static void setUp() {
        addKeyWord("오브젝트", 9);
        addKeyWord("상실의시대", 8);
        addKeyWord("토비의스프링", 7);
        addKeyWord("원피스", 6);
        addKeyWord("나루토", 5);
        addKeyWord("블리치", 1);
        addKeyWord("react", 1);
        addKeyWord("spring", 2);
        addKeyWord("자바", 1);
        addKeyWord("kotlin", 1);
        addKeyWord("이펙티브자바", 4);
        addKeyWord("vue", 1);
        addKeyWord("typescript", 1);
    }

    public static void addKeyWord(String str, int count) {
        for (int i = 0; i < count; i++) {
            cacheManager.addKeyWord(str);
        }
    }

    @Test
    public void top10조회() {
        List<KeyWordCallInfo> top10 = cacheManager.getTop10();

        assertThat(top10).hasSize(10)
                .contains(new KeyWordCallInfo("오브젝트", 9), Index.atIndex(0))
                .contains(new KeyWordCallInfo("상실의시대", 8), Index.atIndex(1))
                .contains(new KeyWordCallInfo("토비의스프링", 7), Index.atIndex(2))
                .contains(new KeyWordCallInfo("원피스", 6), Index.atIndex(3))
                .contains(new KeyWordCallInfo("나루토", 5), Index.atIndex(4))
                .contains(new KeyWordCallInfo("이펙티브자바", 4), Index.atIndex(5))
        ;
    }

    @Test
    public void rebuild확인() {

        addKeyWord("vue", 10);

        cacheManager.rebuild();

        List<KeyWordCallInfo> top10 = cacheManager.getTop10();

        assertThat(top10).hasSize(10)
                .contains(new KeyWordCallInfo("vue", 11), Index.atIndex(0))
        ;
    }

}
