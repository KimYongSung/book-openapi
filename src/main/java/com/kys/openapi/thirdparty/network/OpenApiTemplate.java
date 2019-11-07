package com.kys.openapi.thirdparty.network;

import com.kys.openapi.thirdparty.config.ApiUrl;
import com.kys.openapi.thirdparty.config.QueryString;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

/**
 * OpenAPI 공통 송수신 처리
 */
public abstract class OpenApiTemplate {

    protected RestTemplate restTemplate;

    public OpenApiTemplate(RestTemplateBuilder builder) {
        this.restTemplate = build(builder);
    }

    /**
     * RestTemplate 추가 설정 및 build
     * @param builder 기본설정된 RestTemplateBuilder
     */
    protected abstract RestTemplate build(RestTemplateBuilder builder);

    /**
     * Header에 OpenAPI 인증 정보 추가
     * @param headers 헤더에 OpenAPI 서버별 인증정보 설정
     */
    protected abstract void addAuthorization(HttpHeaders headers);

    /**
     * 기본 Header 생성
     * @return OpenAPI 서버 별로 필요한 헤더 정보 생성
     */
    protected abstract HttpHeaders makeHeaders();

    /**
     * API 호출
     * @param url   요청 URL
     * @param t     요청 정보
     * @param clazz 응답 클래스
     * @returns
     */
    public <T extends QueryString, R> ResponseEntity<R> get(ApiUrl url, T t, Class<R> clazz, Objects... uriVariables){
        return exchange(makeGetUrl(url, t), HttpMethod.GET, null, makeHeaders(), clazz, uriVariables);
    }

    /**
     * API 호출
     * @param url   요청 URL
     * @param t     요청 정보
     * @param clazz 응답 클래스
     * @return
     */
    public <T, R> ResponseEntity<R> post(ApiUrl url, T t, Class<R> clazz, Objects... uriVariables){
        return exchange(url.getFullUrl(), HttpMethod.POST, t, makeHeaders(), clazz, uriVariables);
    }

    /**
     * API 호출
     * @param url           요청 URL
     * @param t             요청 정보
     * @param responseType  응답 클래스
     * @return
     */
    public <T extends QueryString, R> ResponseEntity<R> get(ApiUrl url, T t, ParameterizedTypeReference<R> responseType, Objects... uriVariables){
        return exchange(makeGetUrl(url, t), HttpMethod.GET, null, makeHeaders(), responseType, uriVariables);
    }

    /**
     * API 호출
     * @param url           요청 URL
     * @param t             요청 정보
     * @param responseType  응답 클래스
     * @return
     */
    public <T, R> ResponseEntity<R> post(ApiUrl url, T t, ParameterizedTypeReference<R> responseType, Objects... uriVariables){
        return exchange(url.getFullUrl(), HttpMethod.POST, t, makeHeaders(), responseType, uriVariables);
    }

    /**
     * 송수신 처리
     * @param url           요청 url
     * @param method        요청 method
     * @param t             요청 전문
     * @param headers       헤더 정보
     * @param clazz         응답 class
     * @param uriVariables  uri 파라미터 정보
     * @return
     */
    public <T, R> ResponseEntity<R> exchange(String url, HttpMethod method, T t, HttpHeaders headers, Class<R> clazz, Objects... uriVariables){
        addAuthorization(headers);
        return restTemplate.exchange(url, method, new HttpEntity<>(t, headers), clazz, uriVariables);
    }

    /**
     * 송수신 처리
     * @param url           요청 url
     * @param method        요청 method
     * @param t             요청 전문
     * @param headers       헤더 정보
     * @param responseType  응답 class
     * @param uriVariables  uri 파라미터 정보
     * @return
     */
    public <T, R> ResponseEntity<R> exchange(String url, HttpMethod method, T t, HttpHeaders headers, ParameterizedTypeReference<R> responseType, Objects... uriVariables){
        addAuthorization(headers);
        return restTemplate.exchange(url, method, new HttpEntity<>(t, headers), responseType, uriVariables);
    }

    /**
     * Get 메소드일때 요청 url 생성
     * @param url           openApi url 정보
     * @param queryString   openApi 파라미터
     * @return
     */
    private String makeGetUrl(ApiUrl url, QueryString queryString){
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url.getFullUrl());
        return queryString.toUrl(builder);
    }

}
