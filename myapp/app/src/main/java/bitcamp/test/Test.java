package bitcamp.test;

public class Test {

  public static void main(String[] args) {
    Object c = new Calculator2();

    //Calculator2 c2 = (Calculator2) c;

    System.out.println(((Calculator2)c).minus(100, 200));
    System.out.println(((Calculator)c).plus(100, 200));
  }
}
