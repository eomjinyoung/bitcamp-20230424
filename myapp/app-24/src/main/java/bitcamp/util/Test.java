package bitcamp.util;

public class Test {
  public static void main(String[] args) {
    ArrayList<String> names = new ArrayList<>();
    names.add(new String("홍길동"));
    names.add("임꺽정");
    names.add("유관순");
    names.add("안중근");

    String[] arr = names.toArray(new String[0]);

    for (String item : arr) {
      System.out.println(item);
    }
  }
}
