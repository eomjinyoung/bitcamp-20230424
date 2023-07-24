# 네이버 클라우드 7기(2023-04-24 ~ 2023-10-27)

## 웨일즈 화상회의

https://whaleon.us/o/CSrtk1/001aa759f29c46b985e32dd8562f594a

## 학습 내용

### 1일(5/22,월)

- 오전: 매직에콜 대표 세미나
- 프로그래밍 개론
  - 소프트웨어 아키텍처 소개
  - 네이버클라우드: VPC, Subnet, ACG, Unbuntu Linux 생성
  - 컴파일 방식

### 2일(5/23,화)

- 컴파일 방식(계속)
  - C 소스 컴파일 과정 및 gcc 컴파일러 사용법
- 인터프리터 방식: NodeJS 사용
- 하이브리드 방식: Java 컴파일 및 실행
- JIT 컴파일과 AOT 컴파일
- 기계어, OS, CPU의 관계
- 개발 도구 준비
  - JDK 설치 및 환경 변수 설정
  - VSCode 설치 및 플러그인 설치
  - git 클라이언트 설치

### 3일(5/24,수)

- 개발 도구 준비(계속)
  - 형상관리시스템 소개 
  - git 사용법: clone, add, commit, push, pull
  - Gradle 설치 및 사용법: init, build, run
    - 빌드도구 소개 및 비교: Ant, Maven, Gradle
- 자바 컴파일러 사용법
  - `-d` 옵션
  - 소스와 컴파일된 파일을 두는 폴더를 분리: src, bin
- 학습용 git 저장소 준비
  - 개인 별 git 저장소 생성 및 복제: bitcamp-study
  - 저장소와 프로젝트 폴더의 관례: 1:1, 1:다
- 실습 프로젝트 준비
  - Gradle을 이용한 실습 프로젝트 폴더 구성
  - 구성된 폴더의 용도
- 자바 프로그래밍
  - 소스 파일과 클래스 블록의 관계(com.eomcs.lang.ex01.*)
  - 클래스의 접근 범위: public 과 (default)
  - Entry Point: main() 메서드


### 4일(5/25,목)

- Git 사용법
  - .gitignore 파일 소개
  - `gitignore.io` 사이트에서 .gitignore 설정 내용 만들기
- 자바 프로그래밍
  - 문자열, 문자, 논리, 정수, 부동소수점 표기법
  - 정수 리터럴
    - 각 진수별 표현방법
    - 2진수로 변환하는 다양한 방법
      - Sign-Magnitude, 1's Complement, 2's Complement, Excess-K
  - 부동소수점 리터럴
    - 32비트 크기의 2진수로 바꾸는 방법
    - 64비트 크기의 2진수로 바꾸는 방법 
- 프로젝트 실습
  - 1. 자바 프로젝트 준비하기
  - 2. 리터럴을 사용하여 데이터 출력하기

### 5일(5/26,금)

- 자바 프로그래밍
  - 문자 리터럴
    - 2진수로 변환하는 방법
      - ASCII, ISO-8859-1, KSC-5601(완성형), 조합형, MS-949, Unicode, UTF-8
  - 변수 사용법
    - JVM이 관리하는 메모리 영역 소개
    - 변수 선언, 변수에 값 할당
    - Primitive Data Type 소개: byte, short, int, long, char, float, double, boolean
- 프로젝트 실습
  - 3. 변수를 사용하여 데이터를 저장하기

### 6일(5/30,화)

- 자바 프로그래밍
  - 표준 입출력 API 사용법(com.eomcs.lang.ex99)
  - 변수 사용법(com.eomcs.lang.ex04)
  - 배열 사용법(com.eomcs.lang.ex04)
- 프로젝트 실습
  - 4. 키보드로 값을 입력 받기
  - 5. 배열과 반복문을 이용하여 여러 개의 데이터를 입력 받기

### 7일(5/31,수)

- 자바 프로그래밍
  - 배열 레퍼런스와 인스턴스
  - Heap과 JVM Stack 메모리
  - 가비지와 가비지 컬렉터, 레퍼런스 카운트
  - 연산자 사용법(com.eomcs.lang.ex05)

### 8일(6/1,목)

- 자바 프로그래밍
  - 전위/후위 연산자 동작원리
  - 배열 초기화 기법
  - final 사용법
- 프로젝트 실습
  - 5. 배열과 반복문을 이용하여 여러 개의 데이터를 입력 받기(계속)
    - final 적용
  - 6. 조건문을 활용하여 실행 흐름을 제어하기
  - 7. 기능 단위로 명령문 묶기 : 메서드 사용법
 
### 9일(6/2,금)

- 자바 프로그래밍
  - 흐름제어문 사용법
  - static 메서드 사용법
    - call by value, call by reference
    - 로컬 변수와 JVM Stack 메모리 영역
    - 인스턴스와 Heap 메모리 영역
    - 레퍼런스를 리턴하기
  - static 변수 사용법
- 프로젝트 실습
  - 8. 메서드 간에 변수 공유하기 : 스태틱 변수 사용법
- 기타
  - 개인 별 *프로그래머스* 회원 가입 및 코딩테스트 연습 시작


### 10일(6/5,월)

- 자바 프로그래밍
  - 클래스 사용법
    - 클래스 문법의 목적
    - 클래스의 public 접근 제어
    - 메서드의 public 접근 제어
  - 패키지 사용법
    - import를 이용하여 패키지를 미리 지정하기
- 프로젝트 실습
  - 8. 메서드 간에 변수 공유하기 : 스태틱 변수 사용법(계속)
    - 리팩토링: 메서드 추가 정의, 상수 선언
  - 9. 메서드를 역할에 따라 분류하기 : 클래스 및 패키지 사용법
- 과제(개인)
  - 개인 프로젝트 수행
    - 프로젝트 폴더 생성 및 초기화
    - 실습 프로젝트와 마찬가지로 1단계에서 9단계까지 진행
    - 각 단계별로 app 폴더를 백업할 것.


### 11일(6/7,수)

- 프로젝트 실습
  - 10. 메뉴 구성 및 CRUD 구현
- 과제
  - 프로그래머스 일별 점수 보고(6/5, 6/6)
  - github.com 잔디 확인
    - 개인별 github.com 의 bitcamp-study 저장소 주소 제출
      - 이름, url
    - to: jinyoung.eom@gmail.com

### 12일(6/8,목)

- 프로젝트 실습
  - 11. 사용자 정의 데이터 타입 만들기
- 과제(개인) 발표
  - 개인 프로젝트 시연(17:10)
  - 프로그래머스 일별 점수 보고(6/7)
  - github.com 잔디 확인

### 13일(6/9,금)

- 자바 프로그래밍
  - 클래스 사용법(계속)
    - 스태틱 필드와 스태틱 메서드 사용법
    - 인스턴스 필드와 인스턴스 메서드 사용법
    - 패키지 사용법: import, public 접근 제어
    - GRASP 패턴: Information Expert 패턴 소개 
    - GoF의 Design Patters: Factory Method 패턴 소개
    - 생성자 사용법
    - private/public, setter/getter 사용법

### 14일(6/12,월)

- 자바 프로그래밍
  - 클래스 사용법(복습)
  - Eclipse IDE 도구 설정
    - 다운로드 및 설치, 워크스페이스 설정
    - 프로젝트 임포트
    - 소스 코드 편집과 컴파일, 실행하기
    - git add/commit/push 실행하기
- Gradle 사용법
  - 빌드 스크립트 파일과 플러그인, 태스크 관계
  - `eclispe` 플러그인 사용법
- 프로젝트 실습
  - 12. 생성자, 셋터, 겟터 도입하기

### 15일(6/13,화)

- 자바 프로그래밍(com.eomcs.oop.ex03.*)
  - 클래스 로딩과 스태틱 필드: Method Area 메모리 영역
  - new 연산자와 인스턴스 필드: Heap 메모리 영역
  - 메서드와 로컬 변수: JVM Stack 메모리 영역
- 프로젝트 실습
  - 13. 게시판 관리 기능 추가

### 16일(6/14,수)

- 프로젝트 실습
  - 13. 게시판 관리 기능 추가(계속)
  - 14. 스태틱 필드의 한계 확인
  - 15. 인스턴스 필드와 인스턴스 메서드, 생성자와 의존 객체 주입
  

### 17일(6/15,목)

- 자바 프로그래밍(com.eomcs.oop.ex03.*)
  - Eclipse IDE로 디버깅하는 방법
- 프로젝트 실습
  - 16. GRASP 패턴: Information Expert 적용
  - 17. 인터페이스를 이용한 객체 사용 규칙 정의
  - 18. 인스턴스 목록 제어 기능을 별도의 클래스로 캡슐화: 재사용성 높임

### 18일(6/16,금)

- 자바 프로그래밍(com.eomcs.basic.ex01.*)
  - java.lang.Object 클래스 사용법
- 프로젝트 실습
  - 19. 다형성을 이용하여 범용으로 사용할 수 있는 목록 클래스 만들기

### 19일(6/19,월)

- 프로젝트 실습
  - 20. LinkedList 자료구조 구현하기
  - 21. 인터페이스를 이용하여 List 사용 규칙 정의하기

### 20일(6/20,화)

- 프로젝트 실습
  - 22. Stack, Queue 자료구조 구현하기

### 21일(6/21,수)

- 자바 프로그래밍(com.eomcs.oop)
  - 상속: Specialization vs Generalization
  - 추상 클래스와 추상 메서드
- 프로젝트 실습
  - 23. Composite, Command, Observer 디자인 패턴, 추상 클래스/메서드 활용하기
  
### 22일(6/22,목)

- 자바 프로그래밍(com.eomcs.oop)
  - ex03: 스태틱 필드/블록, 인스턴스 필드/블록/생성자 사용법
  - ex05: 상속 사용법
  - ex06: 다형성 사용법

### 23일(6/23,금)

- 자바 프로그래밍(com.eomcs.oop)
  - ex06: 다형성 사용법(계속)
  - com.eomcs.basic
    - ex01: Object 클래스 사용법
    - ex02: String, Wrapper, Date, Calendar 클래스 사용법 

### 24일(6/26,월)

- 프로젝트 실습
  - 24. 제네릭을 사용하여 타입을 파라미터로 다루기
  - 25. Iterator 디자인 패턴을 활용하여 목록 조회 기능을 캡슐화하기
  - 26. 자바 Collection API 사용하기

### 25일(6/27,화)

- 자바 프로그래밍(com.eomcs.io)
  - File 클래스 사용법(ex01)
  - 바이너리 파일 입출력 다루기(ex02)
  - 프로젝트에서 외부 라이브러리를 사용하는 방법
    - JPEG 파일의 위도 경도 알아내기 
    - 1) search.maven.org 사이트에서 라이브러리 검색
    - 2) 의존 라이브러리 정보를 빌드 스크립트 파일(build.gradle)에 등록
    - 3) 라이브러리 다운로드 및 이클립스 설정 파일 갱신: gradle eclipse 실행
    - 4) Eclipse IDE에서 해당 프로젝트를 refresh 한다.
    - 5) 프로젝트에서 외부 라이브러리 확인
    - 6) 소스 파일에 해당 라이브러리 클래스를 적용

### 26일(6/28,수)

- 자바 프로그래밍(com.eomcs.io)
  - 바이너리 파일 입출력 다루기(ex02)
    - 바이트 스트림으로 텍스트 입출력하기
- 프로젝트 실습
  - 27. File I/O API를 이용하여 데이터를 바이너리 형식으로 입출력하기
  - 28. 상속을 이용하여 primitive type과 String 출력 기능을 추가하기

### 27일(6/29,목)

- 자바 프로그래밍(com.eomcs.io)
  - 바이너리 파일 입출력 다루기(ex04)(자습)
    - primitive type 과 String 값을 입출력하기
  - 버퍼 사용하기(ex06)
    - 버퍼 사용 전/후 성능 비교
  - 상속을 이용한 입출력 기능 확장(ex07)(자습)
  - 포함 관계를 이용한 입출력 기능 확장(ex08)(자습)
  - 데코레이터 패턴을 이용한 입출력 기능 확장(ex09)(자습)
  - Java Stream API 사용법(ex10)(자습)
- 프로젝트 실습
  - 29. 입출력 성능을 높이기 위해 버퍼 기능 추가하기
  - 30. 입출력 기능 확장에 상속 대신 Decorator 패턴을 적용하기
  - 31. Java Stream API 로 교체하기

### 28일(6/30,금)

- 프로젝트 실습
  - 32. 인스턴스를 통째로 입출력하기(객체 직렬화)
  - 33. character stream API를 사용하여 CSV 텍스트 형식으로 입출력하기
  - 34. 리팩토링: Factory Method 패턴(GoF), Information Expert 패턴(GRASP)

### 29일(7/03,월)

- 자바 프로그래밍(com.eomcs.openapi.json)
  - Gson 라이브러리 사용법
  - Jackson 라이브러리 사용법
- 프로젝트 실습
  - 35. JSON 형식으로 입출력하기

### 30일(7/04,화)

- 자바 프로그래밍(com.eomcs.oop)
  - 추상클래스(ex07) 사용법
    - Template Method 패턴(GoF)
  - 인터페이스(ex09, ex10) 사용법
- 개인 과제 발표
  - 프로젝트 진행 상황 보고
    - 김선준, 김현덕, 박동우, 이승혁, 이정훈, 전준호, 손병진, 박진혁, 박승현, 이진석, 김동영, 신현우
    - 김묘경, 방현석, 최근우, 김선래, 최기현, 김호일, 곽나현, 양소율, 신준식, 정연수, 김종원, 봉세환
    - 주현욱, 서재운, 한태영, 김성주, 김세연
    - 오동현(예비군)

### 31일(7/05,수)

- 프로젝트 실습
  - 36. 데이터의 등록, 조회, 수정, 삭제 기능을 캡슐화하기 : DAO 객체 도입
  - 37. 네트워킹을 이용하여 데이터 공유하기 : Client/Server 아키텍처로 전환

### 32일(7/06,목)

- 자바 프로그래밍(com.eomcs.net)
  - Socket, ServerSocket 사용법
  - 데이터 전송 및 수신 방법
- 프로젝트 실습
  - 37. 네트워킹을 이용하여 데이터 공유하기 : Client/Server 아키텍처로 전환(계속)

### 33일(7/07,금)

- 자바 프로그래밍(com.eomcs.reflect)
  - GoF의 프록시 패턴의 원리 이해 및 적용
  - 분산 컴퓨팅의 개념과 주요 기술 이해
- 프로젝트 실습
  - 38. DAO 프록시 객체를 자동 생성하기

### 34일(7/10,월)

- 자바 프로그래밍(com.eomcs.exception)
  - 예외 처리 문법을 사용하는 방법
- 프로젝트 실습
  - 39. Reflection API를 활용하여 DAO 메서드 호출을 자동화하기

### 35일(7/11,화)

- 자바 프로그래밍
  - 네트워킹 프로그래밍(com.eomcs.net)
  - character stream API 사용법(com.eomcs.io.ex3)
- 프로젝트 실습
  - 40. 예외 처리하기
  - 41. 여러 클라이언트의 요청을 순차적으로 처리하기: Stateful 방식
  - 42. 여러 클라이언트의 요청을 순차적으로 처리하기: Stateless 방식

### 36일(7/12,수)

- 자바 프로그래밍
- 프로젝트 실습
  - 43. 여러 클라이언트 요청을 동시에 처리하기: Thread 적용

### 37일(7/13,목)

- 자바 프로그래밍
  - 스레드 프로그래밍(com.eomcs.concurrent.ex1 ~ ex4)
- 멀티태스킹의 메커니즘 이해
  - 프로세스 스케쥴링: Round Robin 방식, Priority + Aging 방식
  - 컨텍스트 스위칭 개념
  - 프로세스 복제(fork)방식과 스레드 방식 비교
- 스레드의 구동원리와 사용법
  - 스레드의 라이프사이클 이해
  - Thread 클래스와 Runnable 인터페이스 사용법
- 프로젝트 실습

### 38일(7/14,금)

- 자바 프로그래밍
  - 스레드 프로그래밍(com.eomcs.concurrent.ex5 ~ ex6)
- 멀티태스킹의 메커니즘 이해
  - 임계영역(Critical Region, Critical Section): 세마포어(Semaphore)와 뮤텍스(Mutex)
- 프로젝트 실습
  - 44. 스레드 재사용하기 : 스레드풀(thread pool) 구현
  
### 39일(7/17,월)

- 자바 프로그래밍
  - 스레드 프로그래밍(com.eomcs.concurrent.ex7)
    - 스레드풀 사용법
  - JDBC 프로그래밍(com.eomcs.jdbc)
    - MySQL 로컬 설치 및 설정
    - 사용자 추가/삭제
    - 데이터베이스 추가/삭제
    - 사용자, 데이터베이스, 테이블 정보 조회
    - DBMS와 DBMS 클라이언트와 관계 
- 프로젝트 실습
  - 45. 스레드 재사용하기 : 자바에서 제공하는 스레드풀(thread pool) 사용

### 40일(7/18,화)

- 자바 프로그래밍
  - JDBC 프로그래밍(com.eomcs.jdbc)
    - DBMS API와 ODBC API
    - JDBC API와 JDBC 드라이버
    - SQL - DDL 사용법

### 41일(7/19,수)

- 자바 프로그래밍
  - JDBC 프로그래밍(com.eomcs.jdbc)
    - SQL - DML 사용법
    - SQL - DQL 사용법
  

### 42일(7/20,목)

- 자바 프로그래밍
  - JDBC 프로그래밍(com.eomcs.jdbc)
    - SQL - DQL 사용법: 조인, 서브쿼리, 그룹 

### 43일(7/21,금)

- 자바 프로그래밍
  - 자바 코딩 문제집(001)
    - 제네릭, 중첩클래스, Map.compute(), BiFunction 인터페이스 사용법
  - JDBC 프로그래밍(com.eomcs.jdbc)
    - JDBC API와 Driver 개념
    - JDBC 드라이버 로딩 원리
    - select/insert/update/delete 프로그래밍
- 프로젝트 실습
  - 46. DBMS 도입하기

### 43일(7/21,금)

- 자바 프로그래밍
  - JDBC 프로그래밍(com.eomcs.jdbc)
    - PreparedStatement 사용법
- 프로젝트 실습
  - 46. DBMS 도입하기(계속)

## 웨일즈 화상회의

https://whaleon.us/o/CSrtk1/001aa759f29c46b985e32dd8562f594a