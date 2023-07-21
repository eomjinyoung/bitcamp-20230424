package coding.ex001;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiFunction;

public class Test3 {
  public static void main(String[] args) {

    BiFunction<Character, Integer, Integer> 값생성기 = new BiFunction<>() {
      @Override
      public Integer apply(Character key, Integer value) {
        return (value == null) ? 1 : value + 1;
      }
    };

    String str = "Be strong, be fearless, be beautiful. "
        + "And believe that anything is possible when you have the right "
        + "people there to support you. ";

    Map<Character,Integer> result = new HashMap<>();

    for (char ch : str.toCharArray()) {
      result.compute(ch, 값생성기);
    }

    for (Entry<Character,Integer> entry: result.entrySet()) {
      System.out.printf("%c: %d\n", entry.getKey(), entry.getValue());
    }


  }
}
