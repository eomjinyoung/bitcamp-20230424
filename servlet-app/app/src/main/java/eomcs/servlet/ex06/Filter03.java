// 컨텍스트 파라미터 가져오기
package eomcs.servlet.ex06;

import javax.servlet.*;
import java.io.IOException;

//@WebFilter("/ex06/*")
public class Filter03 implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    // 필터의 DD 설정으로 지정한 파라미터 값 가져오기
    System.out.printf("ex06.Filter03 : encoding=%s\n",
        request.getServletContext().getInitParameter("encoding"));

    System.out.printf("ex06.Filter03 : aaa=%s\n",
        request.getServletContext().getInitParameter("aaa"));

    chain.doFilter(request, response);
  }
}


