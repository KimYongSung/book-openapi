package com.kys.openapi.keyword.domain.repository;

import com.kys.openapi.keyword.domain.KeyWordHistory;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        JPAQuery<KeyWordHistory> query = queryFactory.selectFrom(keyWordHistory)
                                                    .where(keyWordHistory.userNo.eq(userNo))
                                                    .orderBy(keyWordHistory.keyWordNo.desc());
        return getQuerydsl().applyPagination(pageable, query).fetchResults();
    }

    /**
     * 전체 키워드 이력 정보 조회
     *
     * @param max 페이지 정보
     * @return
     */
    public List<Tuple> findKeyWordAndCountByGrouping(int max) {
        return queryFactory.select(keyWordHistory.keyWord, keyWordHistory.count())
                .from(keyWordHistory)
                .groupBy(keyWordHistory.keyWord)
                .offset(0)
                .limit(max)
                .orderBy(keyWordHistory.count().desc())
                .fetch();
    }
}
