package bitcamp.test;

public class Test3 {
  public static void main(String[] args) {
    // 17 - 3 - 4 = 10

    Calculator2 c = new Calculator2();

    int result = c.minus(17, 3);
    result = c.minus(result, 4);
    System.out.println(result);

    result = c.minus(17, 3, 4);
    System.out.println(result);
  }
}
