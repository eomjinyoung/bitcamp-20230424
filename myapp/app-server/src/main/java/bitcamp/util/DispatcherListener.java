package bitcamp.util;

import java.util.HashMap;
import java.util.Map;

public class DispatcherListener implements ActionListener {

  // 리스너가 작업할 때 사용할 객체를 보관하는 저장소
  Map<String,Object> applicationContext = new HashMap<>();

  @Override
  public void service(BreadcrumbPrompt prompt) {
    try {
      prompt.printf("%s\n", prompt.getAttribute("menuPath"));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
