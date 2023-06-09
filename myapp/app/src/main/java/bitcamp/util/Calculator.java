package bitcamp.util;

public class Calculator {
  
  // non-static 필드는 new 명령을 실행해야만 heap 영역에 생성된다.
  private int result = 0;

  public int getResult() {
    return this.result;
  }

  public void init(int a) {
    this.result = a;
  }

  public void plus(int a) {
    this.result += a;
  }

  public void minus(int a) {
    this.result -= a;
  }

  public void multiple(int a) {
    this.result *= a;
  }

  public void divide(int a) {
    this.result /= a;
  }  
}
