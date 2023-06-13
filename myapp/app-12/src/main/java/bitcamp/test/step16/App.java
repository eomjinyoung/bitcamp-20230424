package bitcamp.test.step16;

import bitcamp.test.step16.vo.Score;

// 1) 낱개의 변수 사용
// 2) 낱개의 변수 재사용
// 3) 배열 사용
// 4) 클래스를 이용하여 데이터 타입 정의(중첩클래스; 로컬 클래스)
// 5) 출력 기능을 별도의 메서드로 분리(중첩클래스; 스태틱 중첩 클래스) 
// 6) 합계 및 평균을 계산하는 기능을 메서드로 분리
// 7) GRASP 패턴: Information Expert(정보를 갖고 있는 클래스가 그 정보를 다룬다.)
// 8) 인스턴스 메서드 도입
// 9) 객체 생성이 번거롭고 복잡한 경우 메서드로 분리하는 것이 낫다.(디자인패턴; 팩토리 메서드)
// 10) GRASP 패턴: Information Expert
// 11) 생성자 도입: 인스턴스 변수를 보다 쉽게 초기화시키기
// 12) 클래스를 유지보수 하기 쉽게 별도 소스 파일로 분리
// 13) 클래스를 유지보수 하기 쉽게 패키지로 분류: import, public
// 14) 외부접근 차단과 값 꺼내기: private, getter
// 15) 프로그래밍의 일관성을 위해 보통 다른 필드에 대해서도 getter를 만들고 사용한다.
// 16) 필드의 직접 접근을 막고 setter를 정의하는 이유
public class App {
  
  public static void main(String[] args) {

    final int MAX_SIZE = 10;
    Score[] scores = new Score[MAX_SIZE];
    int length = 0;

    scores[length++] = new Score("홍길동", 100, 100, 100);
    scores[length++] = new Score("임꺽정", 90, 90, 90);
    scores[length++] = new Score("유관순", 80, 80, 80);

    // 합계와 평균 계산이 끝난 후에 국어 점수를 변경한다면
    // => 국영수 점수와 합계, 평균이 일치하지 않는 문제가 발생한다.
    //    즉, 데이터에 결함이 발생한다.
    // 국영수 점수를 변경한 후에 compute()를 호출하면 되지 않을까?
    // => 만약 개발자가 compute() 호출하는 것을 잊어 먹는다면 아무 소용이 없다.
    // 만약 유효하지 않은 국영수 점수를 입력한다면?
    // => 흠... 이건 도저히 막을 길이 없다.
    scores[0].kor = 7000; // 이렇게 무효한 점수를 입력하는 것을 막을 수 없다.
    scores[0].compute(); // 호출하지 않으면 아무 소용이 없다.

    for (int i = 0; i < length; i++) {
      printScore(scores[i]);
    }

  }

  static void printScore(Score s) {
    System.out.printf("%s: 국어=%d, 영어=%d, 수학=%d, 합계=%d, 평균=%.1f\n", 
      s.getName(), s.kor, s.eng, s.math, s.getSum(), s.getAver());
  }

}
