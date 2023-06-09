package bitcamp.util;

public class Calculator {
  
  // non-static 필드는 new 명령을 실행해야만 heap 영역에 생성된다.
  private int result = 0;

  public static int getResult(Calculator c) {
    return c.result;
  }

  public static void init(Calculator c, int a) {
    c.result = a;
  }

  public static void plus(Calculator c, int a) {
    c.result += a;
  }

  public static void minus(Calculator c, int a) {
    c.result -= a;
  }

  public static void multiple(Calculator c, int a) {
    c.result *= a;
  }

  public static void divide(Calculator c, int a) {
    c.result /= a;
  }  
}
