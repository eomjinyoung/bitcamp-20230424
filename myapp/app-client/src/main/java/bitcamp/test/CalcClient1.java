package bitcamp.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Stateful 방식으로 통신하기
public class CalcClient1 {

  static Pattern pattern = Pattern.compile("[0-9]+|\\p{Punct}");

  public static void main(String[] args) throws Exception {
    try (
        Socket socket = new Socket("localhost", 8888);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream());
        Scanner keyscan = new Scanner(System.in);) {

      while (true) {
        System.out.print("계산식> ");
        String input = keyscan.nextLine();
        if (input.equals("quit")) {
          out.writeUTF("quit");
          break;
        }

        String[] values = parseExpression(input);

        out.writeUTF(values[1]);
        out.writeInt(Integer.parseInt(values[0]));
        out.writeInt(Integer.parseInt(values[2]));

        String result = in.readUTF();
        System.out.printf("결과: %s\n", result);
      }

    }
  }

  public static String[] parseExpression(String expr) {

    Matcher matcher = pattern.matcher(expr);

    ArrayList<String> values = new ArrayList<>();
    while (matcher.find()) {
      values.add(matcher.group());
    }
    return values.toArray(new String[] {});
  }
}







