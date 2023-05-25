package bitcamp.myapp;

public class App {
  public static void main(String[] args) {
    System.out.println("나의 목록 관리 시스템");
    System.out.println("----------------------------------");
    
    System.out.print("번호: ");
    System.out.println(100);
    
    System.out.printf("이름: %s", "홍길동");
    System.out.println();

    System.out.println("나이: " + 20);
    
    System.out.printf("재직자: %b\n", true);

    System.out.printf("성별(남자(M), 여자(W)): %c\n", 'M');

    System.out.printf("좌우시력: %.1f,%.1f\n", 1.5f, 1.0f);
  }
}
