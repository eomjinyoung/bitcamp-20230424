package bitcamp.myapp.servlet;

import bitcamp.myapp.config.AppConfig;
import bitcamp.myapp.config.NcpConfig;
import bitcamp.myapp.controller.RequestMapping;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

@WebServlet(
        value = "/app/*",
        loadOnStartup = 1)
@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
public class DispatcherServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  AnnotationConfigApplicationContext iocContainer;

  @Override
  public void init() throws ServletException {
    System.out.println("DispatcherServlet.init() 호출됨!");
    iocContainer = new AnnotationConfigApplicationContext(AppConfig.class, NcpConfig.class);

    String[] names = iocContainer.getBeanDefinitionNames();
    for (String name : names) {
      System.out.printf("=> %s\n", iocContainer.getBean(name).getClass().getName());
    }

  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    String pageControllerPath = request.getPathInfo();

    response.setContentType("text/html;charset=UTF-8");

    // 클라이언트가 요청한 페이지 컨트롤러를 찾는다.
    Object pageController = iocContainer.getBean(pageControllerPath);

    // request handler(요청이 들어 왔을 때 호출될 메서드)를 찾는다.
    Method requestHandler = getRequestHandler(pageController);
    if (requestHandler == null) {
      throw new ServletException("요청을 처리할 핸들러가 없습니다!");
    }

    // @RequestMapping 이 붙은 메서드를 호출한다.
    try {
      String viewUrl = (String) requestHandler.invoke(pageController, request, response);
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

  private Method getRequestHandler(Object pageController) {
    Class<?> clazz = pageController.getClass();
    Method[] methods = clazz.getDeclaredMethods();
    for (Method m : methods) {
      if (m.getAnnotation(RequestMapping.class) != null) {
        return m;
      }
    }
    return null;
  }
}














