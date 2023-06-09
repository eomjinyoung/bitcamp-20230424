package bitcamp.myapp;

public class Calculator {
  
  static int result = 0;

  static void init(int a) {
    result = a;
  }

  static void plus(int a) {
    result += a;
  }

  static void minus(int a) {
    result -= a;
  }

  static void multiple(int a) {
    result *= a;
  }

  static void divide(int a) {
    result /= a;
  }  
}
