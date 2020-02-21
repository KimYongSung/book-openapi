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

## 2. 실행 옵션

java -jar openapi-0.0.1-SNAPSHOT.jar

## 3. 접속 url

http://localhost:8080/

## 4. vue 설정 추가

참고 : https://deockstory.tistory.com/26

### 4.1 node plugin 설정 추가

```text
// node plugin 추가
plugins {
	...
	id 'com.moowork.node' version '1.3.1'
	...
}

// node 설정
node {
	version = '13.6.0'
	npmVersion = '6.13.1'
	download = false
	workDir = file("./src/frontend")
	nodeModulesDir = file("./src/frontend")
}

// npmInstall 실행 Config
npmInstall {
	args = ['install']
	inputs.files file('package.json')
	outputs.files file('node_modules')
}

task viewBuild(type: NpmTask, dependsOn: npmInstall){
	description = "Build vue.js"
	args = ['run','build']
}

processResources.dependsOn 'viewBuild'

```

### 4.2 vue cli로 개발환경 설정

src 로 이동 후 명령어 실행 후 default 설정으로 구성

```text
vue create frontend
``` 

## 99. TODO

* SPA로 변경 작업 
* 네이버와 카카오 오픈 api 연동 부분 fegin 활용 검토