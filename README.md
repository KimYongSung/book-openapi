# 카카오, 네이버 OpenAPI를 활용한 간단한 서비스

## 1. 사용 라이브러리

### Spring Boot

* spring-boot-starter-web
  * 기본적인 web 처리를 위한 사용 하였습니다.

* spring-boot-starter-freemarker
  * 화면 처리를 위한 템플릿 엔진 하였습니다.

* spring-boot-starter-data-jpa
  * Spring DATA와 JPA를 사용 하였습니다.

* spring-boot-starter-security
  * jwt 토큰을 이용한 무상태 서버를 위해서 사용 하였습니다.

* spring-boot-devtools
  * 개발 편의성을 위해서 사용 하였습니다.

* spring-boot-starter-test
  * 테스트 작성을 위해서 사용하였습니다.

* spring-security-test
  * 테스트 작성을 위해서 사용하였습니다.

### Others

* io.jsonwebtoken:jjwt:0.9.1
  * jwt 토큰 생성을 위하여 사용하였습니다.

* com.querydsl:querydsl-apt, com.querydsl:querydsl-jpa
  * 자바 기반의 타입 세이프한 쿼리를 작성하기 위해서 사용하였습니다.

* org.projectlombok:lombok
  * 반복적인 코드 작성을 줄이기 위해서 사용하였습니다.

* com.h2database:h2
  * 인메모리 DB 사용하기 위해서 선택했습니다

* JQuery, bootstrap
  * 프론트 개발을 위해서 사용하였습니다.

## 2. 다운로드 경로

구글 드라이드 : https://drive.google.com/open?id=1HE4EUQQ99NBMFYC48t2t2OYcGw_ZvtaC

* 집에서 테스트시 바이러스 검사로 인하여 응답없는 대기 시간이 소요될 수 있습니다. 양해 부탁드립니다.

github : https://github.com/KimYongSung/book-openapi/blob/master/openapi-0.0.1-SNAPSHOT.jar

## 3. 실행 옵션

java -jar openapi-0.0.1-SNAPSHOT.jar
