package bitcamp.myapp;

// 코드 본문에서 사용할 클래스가 어떤 패키지의 클래스인지 지정한다.
import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    System.out.println("나의 목록 관리 시스템");
    System.out.println("----------------------------------");

    // 키보드 스캐너 준비
    Scanner scanner = new Scanner(System.in);

    int[] no = new int[3];
    String[] name = new String[3];
    int[] age = new int[3];
    boolean[] working = new boolean[3];
    char[] gender = new char[3];
    float[] leftEye = new float[3];
    float[] rightEye = new float[3];

    for (int count = 0; count < 3; count++) {
      System.out.print("번호? ");
      no[count] = scanner.nextInt();

      System.out.print("이름? ");
      name[count] = scanner.next();

      System.out.print("나이? ");
      age[count] = scanner.nextInt();

      System.out.print("재직중(true/false)? ");
      working[count] = scanner.nextBoolean();

      System.out.print("성별(남자:M, 여자:W)? ");
      String str = scanner.next();
      gender[count] = str.charAt(0);

      System.out.print("시력(왼쪽, 오른쪽)? ");
      leftEye[count] = scanner.nextFloat();
      rightEye[count] = scanner.nextFloat();
    }

    System.out.println("---------------------------------------");

    for (int count = 0; count < 3; count++) {
      System.out.printf("번호: %d\n", no[count]);
      System.out.printf("이름: %s\n", name[count]);
      System.out.printf("나이: %d\n", age[count]);
      System.out.printf("재직자: %b\n", working[count]);
      System.out.printf("성별(남자(M), 여자(W)): %c\n", gender[count]);
      System.out.printf("좌우시력: %.1f,%.1f\n", leftEye[count], rightEye[count]);
    }
    scanner.close();
  }
}
