package com.kys.openapi.user.domain;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 117505574L;

    public static final QUser user = new QUser("user");

    public final DateTimePath<java.time.LocalDateTime> chgDt = createDateTime("chgDt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> regDt = createDateTime("regDt", java.time.LocalDateTime.class);

    public final EnumPath<com.kys.openapi.user.code.UserStatus> status = createEnum("status", com.kys.openapi.user.code.UserStatus.class);

    public final StringPath userId = createString("userId");

    public final NumberPath<Long> userNo = createNumber("userNo", Long.class);

    public final StringPath userPwd = createString("userPwd");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

