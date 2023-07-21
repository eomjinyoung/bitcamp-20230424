package coding.ex001;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Test5 {
  public static void main(String[] args) {

    String str = "Be strong, be fearless, be beautiful. "
        + "And believe that anything is possible when you have the right "
        + "people there to support you. ";

    Map<Character,Integer> result = new HashMap<>();

    for (char ch : str.toCharArray()) {
      result.compute(ch, (key, value) -> (value == null) ? 1 : value + 1);
    }

    for (Entry<Character,Integer> entry: result.entrySet()) {
      System.out.printf("%c: %d\n", entry.getKey(), entry.getValue());
    }


  }
}
