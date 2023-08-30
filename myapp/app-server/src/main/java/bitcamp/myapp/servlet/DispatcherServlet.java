package bitcamp.myapp.servlet;

import bitcamp.myapp.controller.LoginController;
import bitcamp.myapp.controller.LogoutController;
import bitcamp.myapp.controller.PageController;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/app/*")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
public class DispatcherServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  Map<String, PageController> controllerMap = new HashMap<>();

  @Override
  public void init() throws ServletException {
    controllerMap.put("/auth/login", new LoginController());
    controllerMap.put("/auth/logout", new LogoutController());
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    String pageControllerPath = request.getPathInfo();

    response.setContentType("text/html;charset=UTF-8");

    // 클라이언트가 요청한 페이지 컨트롤러를 찾는다.
    PageController pageController = controllerMap.get(pageControllerPath);
    if (pageController == null) {
      throw new ServletException("해당 요청을 처리할 수 없습니다!");
    }

    // 페이지 컨트롤러를 실행한다.
    try {
      String viewUrl = pageController.execute(request, response);
      if (viewUrl.startsWith("redirect:")) {
        response.sendRedirect(viewUrl.substring(9)); // 예) redirect:/app/board/list
      } else {
        request.getRequestDispatcher(viewUrl).include(request, response);
      }

    } catch (Exception e) {
      // 페이지 컨트롤러 실행 중 오류가 발생했다면, 예외를 던진다.
      throw new ServletException("요청 처리 중 오류 발생!", e);
    }

  }
}
