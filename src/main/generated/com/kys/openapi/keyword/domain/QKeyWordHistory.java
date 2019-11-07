package com.kys.openapi.keyword.domain;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QKeyWordHistory is a Querydsl query type for KeyWordHistory
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QKeyWordHistory extends EntityPathBase<KeyWordHistory> {

    private static final long serialVersionUID = 1256728092L;

    public static final QKeyWordHistory keyWordHistory = new QKeyWordHistory("keyWordHistory");

    public final StringPath keyWord = createString("keyWord");

    public final NumberPath<Long> keyWordNo = createNumber("keyWordNo", Long.class);

    public final DateTimePath<java.time.LocalDateTime> searchDt = createDateTime("searchDt", java.time.LocalDateTime.class);

    public final NumberPath<Long> userNo = createNumber("userNo", Long.class);

    public QKeyWordHistory(String variable) {
        super(KeyWordHistory.class, forVariable(variable));
    }

    public QKeyWordHistory(Path<? extends KeyWordHistory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QKeyWordHistory(PathMetadata metadata) {
        super(KeyWordHistory.class, metadata);
    }

}

