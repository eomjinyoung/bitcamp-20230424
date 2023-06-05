package bitcamp.myapp;

// 소스 코드에서 Calculator 클래스는 bitcamp.util 패키지에 소속된 클래스를 가리킨다.
import bitcamp.util.Calculator;

public class Test {
  
  public static void main(String[] args) {
    // 2 * 3 + 7 - 2 / 2 = ?
    // => 연산자 우선 순위를 고려하지 않고 앞에서부터 뒤로 순차적으로 계산한다.

    Calculator.init(2);
    Calculator.multiple(3);
    Calculator.plus(7);
    Calculator.minus(2);
    Calculator.divide(2);
    System.out.println(Calculator.result);
  }

  
}
