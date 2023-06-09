package bitcamp.myapp;

import bitcamp.util.Calculator;

public class Test {
  
  public static void main(String[] args) {
    // 2 * 3 + 7 - 2 / 2 = ?
    // 3 - 1 * 7 + 15 / 3 = ?
    // => 위의 계산을 동시에 수행하기

    // 두 개의 계산 결과를 저장할 수 있는 result 변수를 준비한다.
    Calculator c1 = new Calculator();
    Calculator c2 = new Calculator();

    Calculator.init(c1, 2);
    Calculator.init(c2, 3);
    Calculator.multiple(c1, 3);
    Calculator.minus(c2, 1);
    Calculator.plus(c1, 7);
    Calculator.multiple(c2, 7);
    Calculator.minus(c1, 2);
    Calculator.plus(c2, 15);
    Calculator.divide(c1, 2);
    Calculator.divide(c2, 3);
    
    System.out.println(Calculator.getResult(c1));
    System.out.println(Calculator.getResult(c2));
  }

}
