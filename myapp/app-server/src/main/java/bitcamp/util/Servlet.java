package bitcamp.util;

public interface Servlet {
  void service(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
