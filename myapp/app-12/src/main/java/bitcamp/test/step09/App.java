package bitcamp.test.step09;

// 1) 낱개의 변수 사용
// 2) 낱개의 변수 재사용
// 3) 배열 사용
// 4) 클래스를 이용하여 데이터 타입 정의(중첩클래스; 로컬 클래스)
// 5) 출력 기능을 별도의 메서드로 분리(중첩클래스; 스태틱 중첩 클래스) 
// 6) 합계 및 평균을 계산하는 기능을 메서드로 분리
// 7) GRASP 패턴: Information Expert(정보를 갖고 있는 클래스가 그 정보를 다룬다.)
// 8) 인스턴스 메서드 도입
// 9) 객체 생성이 번거롭고 복잡한 경우 메서드로 분리하는 것이 낫다.(디자인패턴; 팩토리 메서드)
public class App {
  
  static class Score {
    String name;
    int kor;
    int eng;
    int math;
    int sum;
    float aver;

    void compute() {
      this.sum = this.kor + this.eng + this.math;
      this.aver = this.sum / 3f;
    }
  }
  
  public static void main(String[] args) {

    final int MAX_SIZE = 10;
    Score[] scores = new Score[MAX_SIZE];
    int length = 0;

    scores[length++] = createScore("홍길동", 100, 100, 100);
    scores[length++] = createScore("임꺽정", 90, 90, 90);
    scores[length++] = createScore("유관순", 80, 80, 80);

    for (int i = 0; i < length; i++) {
      printScore(scores[i]);
    }

  }

  // 팩토리 메서드
  static Score createScore(String name, int kor, int eng, int math) {
    Score s = new Score();
    s.name = name;
    s.kor = kor;
    s.eng = eng;
    s.math = math;
    s.compute();
    return s;
  }

  static void printScore(Score s) {
    System.out.printf("%s: 합계=%d, 평균=%.1f\n", 
      s.name, s.sum, s.aver);
  }

}
