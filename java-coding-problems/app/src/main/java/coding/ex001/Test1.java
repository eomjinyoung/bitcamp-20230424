package coding.ex001;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Test1 {
  public static void main(String[] args) {
    String str = "Be strong, be fearless, be beautiful. "
        + "And believe that anything is possible when you have the right "
        + "people there to support you. ";

    Map<Character,Integer> result = new HashMap<>();

    for (char ch : str.toCharArray()) {
      Integer value = result.get(ch);
      if (value == null) {
        result.put(ch, 1);
      } else {
        result.put(ch, ++value);
      }
    }

    for (Entry<Character,Integer> entry: result.entrySet()) {
      System.out.printf("%c: %d\n", entry.getKey(), entry.getValue());
    }


  }
}
