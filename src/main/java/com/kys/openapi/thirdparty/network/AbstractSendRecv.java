package com.kys.openapi.thirdparty.network;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

/**
 * OpenAPI 공통 송수신 처리
 */
public abstract class AbstractSendRecv {

    protected RestTemplate restTemplate;

    public AbstractSendRecv(RestTemplateBuilder builder) {
        this.restTemplate = builder.interceptors(loggingInterceptor())
                                   .build();
    }

    /**
     * 로킹용 interceptor 생성
     * @return
     */
    protected abstract ClientHttpRequestInterceptor loggingInterceptor();

    /**
     * Header에 OpenAPI 인증 정보 추가
     * @param headers
     */
    protected abstract void addAuthorization(HttpHeaders headers);

    /**
     * 기본 Header 생성
     * @return
     */
    protected abstract HttpHeaders makeHeaders();

    /**
     * API 호출
     * @param url   요청 URL
     * @param t     요청 정보
     * @param clazz 응답 클래스
     * @param <T>
     * @param <R>
     * @return
     */
    public <T extends QueryString, R> ResponseEntity<R> get(ApiUrl url, T t, Class<R> clazz, Objects... uriVariables){
        return exchange(makeGetUrl(url, t), HttpMethod.GET, null, makeHeaders(), clazz, uriVariables);
    }

    /**
     * API 호출
     * @param url   요청 URL
     * @param t     요청 정보
     * @param clazz 응답 클래스
     * @param <T>
     * @param <R>
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
     * @param <T>
     * @param <R>
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
     * @param <T>
     * @param <R>
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
     * @param <T> 요청 클래스
     * @param <R> 응답 클래스
     * @return
     */
    private <T, R> ResponseEntity<R> exchange(String url, HttpMethod method, T t, HttpHeaders headers, Class<R> clazz, Objects... uriVariables){
        addAuthorization(headers);
        return restTemplate.exchange(url, method, getHttpEntity(t, headers), clazz, uriVariables);
    }

    /**
     * 송수신 처리
     * @param url           요청 url
     * @param method        요청 method
     * @param t             요청 전문
     * @param headers       헤더 정보
     * @param responseType  응답 class
     * @param uriVariables  uri 파라미터 정보
     * @param <T> 요청 클래스
     * @param <R> 응답 클래스
     * @return
     */
    private <T, R> ResponseEntity<R> exchange(String url, HttpMethod method, T t, HttpHeaders headers, ParameterizedTypeReference<R> responseType, Objects... uriVariables){
        addAuthorization(headers);
        return restTemplate.exchange(url, method, getHttpEntity(t, headers), responseType, uriVariables);
    }

    private <T> HttpEntity<T> getHttpEntity(T t, HttpHeaders headers) {
        return Objects.isNull(t) ? new HttpEntity<>(headers) : new HttpEntity<>(t, headers);
    }

    private String makeGetUrl(ApiUrl url, QueryString queryString){
        return UriComponentsBuilder.fromHttpUrl(url.getFullUrl())
                                   .query(queryString.toQueryString())
                                   .toUriString();
    }
}
