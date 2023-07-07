# 실습 프로젝트

## 1. 자바 프로젝트 준비하기

- Gradle 빌드 도구를 이용하여 프로젝트 폴더를 구성하는 방법
  - `init` task 실행
- Gradle로 프로젝트를 빌드하고 실행하는 방법
  - `build`, `run` task 실행

## 2. 리터럴을 사용하여 데이터 출력하기

- 리터럴을 사용하는 방법

## 3. 변수를 사용하여 데이터를 저장하기

- 변수를 사용하는 방법

## 4. 키보드로 값을 입력 받기

- 키보드로 값을 입력 받는 방법

## 5. 배열과 반복문을 이용하여 여러 개의 데이터를 입력 받기

- 배열 활용
- 반복문 활용
- 상수 활용

## 6. 조건문을 활용하여 실행 흐름을 제어하기

- if ~ else ~ 조건문 활용
- switch 활용
- break 활용 
- while 활용

## 7. 기능 단위로 명령문 묶기 : 메서드 사용법

- static 메서드를 정의하고 호출하는 방법 

## 8. 메서드 간에 변수 공유하기 : 스태틱 변수 사용법

- static 변수를 정의하고 사용하는 방법
- 리팩토링
  - 사용자에게서 입력 받는 기능을 메서드로 분리하여 정의(메서드 문법 활용)
  - 남, 녀 식별 값을 상수로 선언(final 문법 활용)

## 9. 메서드를 역할에 따라 분류하기 : 클래스 및 패키지 사용법

- 클래스 및 패키지 사용법(스태틱 클래스)
- import 명령문 사용법
- public 접근 제어 사용법
- 스태틱 변수가 생성되고 제거되는 시점, 메모리 영역
 
## 10. 메뉴 구성 및 CRUD 구현

- 회원정보를 다루는 메뉴 구성하기
- 회원정보 조회 및 변경, 삭제 구현하기

## 11. 사용자 정의 데이터 타입 만들기

- 클래스 문법을 이용하여 데이터 타입을 정의하는 방법
- 클래스를 이용하여 만든 데이터 타입의 메모리를 준비하는 방법: new 명령
- 클래스 배열 사용법
- 인스턴스와 인스턴스 필드, 힙(heap) 메모리의 관계

## 12. 생성자, 셋터, 겟터 도입하기

- 인스턴스 필드를 초기화시키는 방법: 생성자
- 인스턴스 필드에 직접 접근하는 것을 막는 방법: private
- 인스턴스 필드에 값을 저장하고 꺼내는 방법: setter/getter
- 스태틱 필드 및 생성자 활용
- 스태틱 상수 필드 활용 + GRASP 패턴의 Information Expert 

## 13. 복사/붙여넣기를 이용한 CRUD 구현

- 게시글 CRUD 기능 추가
- Value Object, Handler 클래스 추가
- Prompt 클래스 리팩토링

## 14. 스태틱 필드의 한계 확인

- BoardHandler 클래스를 복제하여 독서록 게시판 추가
- 클래스 코드 복제의 문제점 확인

## 15. 인스턴스 필드와 인스턴스 메서드, 생성자와 의존 객체 주입

- BoardHandler 클래스에 인스턴스 필드 및 메서드 적용
- 향후 확장성을 고려하여 MemberHandler 크래스에도 인스턴스 필드와 인스턴스 메서드를 적용
  - 그래서 실무에서는 대부분의 클래스가 인스턴스 필드와 인스턴스 메서드로 구성된다.
- 향후 확장성을 고려하여 Prompt 크래스에도 인스턴스 필드와 인스턴스 메서드를 적용
  - 생성자 도입: Scanner 사용할 입력 도구를 지정할 수 있게 한다.
- 의존 객체 주입의 개념과 구현
  - 생성자를 통해 Prompt 객체를 Handler에 주입

## 16. GRASP 패턴: Information Expert 적용

- 메뉴 기능을 각 핸들러에게 위임
  - 기능을 수행하는데 필요한 정보를 가지고 있는 객체에 역할 부여
  - CRUD 메뉴 기능은 핸들러로 이전
- App 클래스는 메인 메뉴 제공

## 17. 인터페이스를 이용한 객체 사용 규칙 정의

- 인터페이스 문법으로 핸들러의 실행 규칙 정의
- 인터페이스에 정의한 대로 핸들러 구현
- 인터페이스에 정의한 대로 핸들러 실행

## 18. 인스턴스 목록 제어 기능을 별도의 클래스로 캡슐화: 재사용성 높임

- 핸들러에서 인스턴스 목록을 다루는 기능을 별도의 클래스로 분리
  - UI가 CLI에서 윈도우 또는 웹으로 바뀌더라도 인스턴스 목록 다루는 기능은 재사용 가능
- 배열 크기 자동 증가 기능 추가

## 19. 다형성을 이용하여 범용으로 사용할 수 있는 목록 클래스 만들기

- 목록 관리 범용 클래스 ArrayList 정의
  - 다형성의 polymorphic variable 문법 활용
- equals() 메서드와 오버라이딩 활용
  - Object 클래스와 상속
  - Member와 Board 클래스에 적용
- 오버로딩을 활용하여 생성자를 추가
  - Member와 Board 클래스 적용
- MemberHandler와 BoardHandler에 적용

## 20. LinkedList 자료구조 구현하기

- 목록 관리 범용 클래스 LinkedList 정의
  - LinkedList 구동원리 이해 및 구현
  - 중첩 클래스 활용
- MemberHandler와 BoardHandler에 적용

## 21. 인터페이스를 이용하여 List 사용 규칙 정의하기

- 목록 관리 객체의 사용 규칙을 인터페이스 정의
  - List 인터페이스 정의
  - ArrayList, LinkedList에 List 인터페이스 적용
- MemberHandler와 BoardHandler에 적용
  - List 구현체를 생성자를 통해 주입: DI(Dependency Injection) 적용

## 22. Stack, Queue 자료구조 구현하기

- Stack과 Queue의 구동원리 이해 및 구현
- Stack 적용
  - Prompt 클래스의 서브 클래스 MenuPrompt 정의
  - MenuPrompt에서 Stack을 이용하여 프롬프트 제목에 breadcrumb 기능을 적용
- Queue 적용
  - MenuPrompt 클래스에 메뉴 출력 기능을 추가
    - App, BoardHandler, MemberHandler 변경
  - MenuPrompt 클래스에 입력한 명령어의 history 기능을 추가

## 23. Composite, Command, Observer 디자인 패턴, 추상 클래스/메서드 활용하기

- Composite 패턴을 활용하여 메뉴 구현하기
  - BreadcrumbPrompt에 적용
  - Menu, MenuGroup 클래스 정의
- Observer 패턴을 활용하여 메뉴 명령 처리하기
  - ActionListener 인터페이스 정의
  - Menu와 리스너 객체 연결
- Command 패턴을 활용하여 메뉴 기능 구현하기
  - BoardHandler, MemberHandler에 적용
  - ActionListener 인터페이스 활용
  - BoardXxxListener, MemberXxxListener 클래스로 분해
- Generalization(상속) 수행 
  - AbstractBoardListener 추상 클래스 정의
    - 추상 메서드 도입

## 24. 제네릭을 사용하여 타입을 파라미터로 다루기

- ArrayList, LinkedList, Stack, Queue에 제네릭 적용하기
- T[] toArray(T[]) 메서드 추가하기

## 25. Iterator 디자인 패턴을 활용하여 목록 조회 기능을 캡슐화하기

- GoF의 디자인 패턴 중 Iterator 패턴의 동작원리 이해 및 구현
- ArrayList, LinkedList, Stack, Queue에 적용
- 중접 클래스 문법을 이용하여 Iterator 구현하기
  - static/non-static nested 클래스 문법을 활용하는 방법
  - local/anonymous 클래스 문법을 활용하는 방법

## 26. 자바 Collection API 사용하기

- 목록을 다루는 기존 클래스를 자바 컬렉션 API 로 교체

## 27. File I/O API를 이용하여 데이터를 바이너리 형식으로 입출력하기

- FileInputStream/FileOutputStream 사용법
- 바이너리 형식으로 데이터를 입출력하는 방법

## 28. 상속을 이용하여 primitive type과 String 출력 기능을 추가하기

- 상속을 이용하여 바이트 입출력 기능을 확장하기
  - DataInputStream = FileInputStream 클래스 + primitive type/String 값 읽기
  - DataOutputStream = FileOutputStream 클래스 + primitive type/String 값 쓰기

## 29. 입출력 성능을 높이기 위해 버퍼 기능 추가하기

- 기존의 클래스에 버퍼 기능을 추가한다.
  - BufferedDataInputStream = DataInputStream + 버퍼 기능
  - BufferedDataOutputStream = DataOutputStream + 버퍼 기능

## 30. 입출력 기능 확장에 상속 대신 Decorator 패턴을 적용하기

- 상속 vs Decorator 패턴(GoF)
  - 기존 코드를 손대지 않고 기능 확장하는 방법
  - 상속: 기능 확장 용이
  - Decorator: 기능 확장 및 기능 제거 용이
- BufferedDataInputStream 분해
  - BufferedInputStream, DataInputStream, FileInputStream
- BufferedDataOutputStream 분해
  - BufferedOutputStream, DataOutputStream, FileOutputStream

## 31. Java Stream API 로 교체하기

- 입출력 관련 클래스를 자바 스트림 클래스로 교체
  - java.io.* 패키지의 클래스 사용

## 32. 인스턴스를 통째로 입출력하기(객체 직렬화)

- ObjectInputStream/ObjectOutputStream 사용법
  - java.io.Serializable 인터페이스 사용법
  - transient modifier 사용법

## 33. character stream API를 사용하여 CSV 텍스트 형식으로 입출력하기

- CSV 형식으로 데이터를 읽고 쓰는 법
- FileReader/FileWriter 사용법

## 34. 리팩토링: Factory Method 패턴(GoF), Information Expert 패턴(GRASP)

- CSV 데이터 생성을 Board 클래스에 맡기기
  - Information Expert 패턴 적용
- CSV 데이터를 가지로 Board 클래스 생성하기
  - Factory Method 패턴 적용
  - Reflection API 사용법
    - Class, Method 사용법

## 35. JSON 형식으로 입출력하기

- JSON 형식으로 데이터를 읽고 쓰는 법
- Gson 라이브러리 사용법

## 36. 데이터의 등록, 조회, 수정, 삭제 기능을 캡슐화하기 : DAO 객체 도입

- XxxListener에서 데이터를 조작하는 코드를 캡슐화하여 별도의 클래스로 분리
- 인터페이스로 DAO 객체 사용법을 정의

## 37. 네트워킹을 이용하여 데이터 공유하기 : Client/Server 아키텍처로 전환

- 네트워크 프로그래밍 방법
  - Client와 Server 개념
  - 프로토콜에 따라 애플리케이션 간에 데이터를 주고 받기
  - GoF의 프록시 패턴의 원리 이해 및 적용
  - 분산 컴퓨팅의 개념과 주요 기술 이해

## 38. DAO 프록시 객체를 자동 생성하기

- java.lang.reflect.Proxy 클래스 사용법
- 프록시 객체의 구동원리 이해 


## 39. Reflection API를 활용하여 DAO 메서드 호출을 자동화하기

- Reflection API를 사용하는 방법
- 서버의 DAO 메서드 호출을 자동화 하는 방법



