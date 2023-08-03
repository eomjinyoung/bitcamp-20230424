package bitcamp.util;

import java.util.HashMap;
import java.util.Map;

// 클라이언트 당 한 개가 배정되는 보관소
// - 클라이언트 요청 간에 데이터를 공유할 때 사용한다.
// - 클라이언트 세션ID로 구분한다.
//
public class HttpSession {

  String sessionId;
  Map<String,Object> attrMap = new HashMap<>();

  public HttpSession(String sessionId) {
    this.sessionId = sessionId;
  }

  public String getId() {
    return this.sessionId;
  }

  public void setAttribute(String name, Object value) {
    attrMap.put(name, value);
  }

  public Object getAttribute(String name) {
    return attrMap.get(name);
  }
}
