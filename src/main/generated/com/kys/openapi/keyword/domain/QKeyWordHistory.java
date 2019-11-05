package com.kys.openapi.keyword.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


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

