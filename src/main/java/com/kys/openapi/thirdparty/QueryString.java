package com.kys.openapi.thirdparty;

import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

/**
 * Get 요청 api의 경우 object를 QueryString으로 변환
 */
public interface QueryString {

    /**
     * QueryString 생성
     * @return
     */
    String toUrl(UriComponentsBuilder builder);

    /**
     * Builder에 파라미터 추가
     * @param builder
     * @param name
     * @param value
     */
    default void addParam(UriComponentsBuilder builder, String name, Object value){
        if(Objects.nonNull(value)){
            builder.queryParam(name, value);
        }
    }

    /**
     * Builder에 파라미터 추가
     * @param builder
     * @param name
     * @param value
     */
    default void addParam(UriComponentsBuilder builder, String name, Enum<?> value){
        if(Objects.nonNull(value)){
            builder.queryParam(name, value.name());
        }
    }

}
