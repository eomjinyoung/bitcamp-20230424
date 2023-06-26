package bitcamp.util;

public class Calculator {
  int result;

  public void plus(int value) {
    this.result += value;
  }

  public void minus(int value) {
    this.result -= value;
  }

  public static void main(String[] args) {
    Calculator c = new Calculator();
    c.plus(100);
    c.minus(50);
    System.out.println(c.result);
  }
}
