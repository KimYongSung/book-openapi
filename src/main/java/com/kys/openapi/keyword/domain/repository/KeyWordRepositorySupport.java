package com.kys.openapi.keyword.domain.repository;

import com.kys.openapi.keyword.domain.KeyWordHistory;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import static com.kys.openapi.keyword.domain.QKeyWordHistory.keyWordHistory;

/**
 * 키워드 레파지토리
 */
@Repository
public class KeyWordRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public KeyWordRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        super(KeyWordHistory.class);
        this.queryFactory = jpaQueryFactory;
    }

    /**
     * 고객번호로 키워드 이력 조회
     * @param userNo   고객번호
     * @param pageable 페이지 정보
     * @return
     */
    public QueryResults<KeyWordHistory> findByUserNoOrderByKeywordDesc(Long userNo, Pageable pageable) {
        return queryFactory.selectFrom(keyWordHistory)
                           .where(keyWordHistory.userNo.eq(userNo))
                           .offset(pageable.getOffset())
                           .limit(pageable.getPageSize())
                           .orderBy(keyWordHistory.keyWordNo.desc())
                .fetchResults();
    }

}
