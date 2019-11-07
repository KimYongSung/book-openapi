package com.kys.openapi.user.domain.repository;

import com.kys.openapi.user.domain.QUser;
import com.kys.openapi.user.domain.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public UserRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        super(User.class);
        this.queryFactory = jpaQueryFactory;
    }

    /**
     * ID와 비밀번호로 고객정보 조회
     * @param id    고객 ID
     * @param pwd   고객 비밀번호
     * @return
     */
    public Optional<User> findByUserIdAndUserPwd(String id, String pwd){
        User user = queryFactory.selectFrom(QUser.user)
                                .where(QUser.user.userId.eq(id)
                                .and(QUser.user.userPwd.eq(pwd)))
                                .fetchFirst();
        return Optional.ofNullable(user);
    }

    /**
     * ID와 비밀번호로 고객정보 조회
     * @param id    고객 ID
     * @return
     */
    public Optional<User> findByUserId(String id){

        User user = queryFactory.selectFrom(QUser.user)
                                .where(QUser.user.userId.eq(id))
                                .fetchFirst();

        return Optional.ofNullable(user);
    }
}
