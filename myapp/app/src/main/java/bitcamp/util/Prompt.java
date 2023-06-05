package bitcamp.util;

import java.util.Scanner;

public class Prompt {

  static Scanner scanner = new Scanner(System.in);

  public static String inputString(String title) {
    System.out.print(title);
    return scanner.nextLine();
  }

  public static void close() {
    scanner.close();
  }

}
