package bitcamp.test.p1;

public class Test1 {
  public static void main(String[] args) {
    A obj = new A();

    //obj.v1 = 100; // 접근불가!
    obj.v2 = 200;
    obj.v3 = 300;
    obj.v4 = 400;
    obj.m();
  }
}
