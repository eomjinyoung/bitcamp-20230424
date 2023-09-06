package bitcamp.myapp.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class MyWebApplicationInitializer3 extends AbstractDispatcherServletInitializer {

  @Override
  public void onStartup(ServletContext sc) throws ServletException {
    System.out.println("MyWebApplicationInitializer3.onStartup() 호출됨!");

    // 수퍼 클래스에 정의된 작업은 그대로 수행하고,
    // => DispatcherServlet을 준비하는 작업
//    super.onStartup(sc);
  }

  @Override
  protected WebApplicationContext createServletApplicationContext() {
    // DispatcherServlet 이 사용할 IoC 컨테이너를 생성한다.
    AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
    context.register(AppConfig.class);
    return context;
  }

  @Override
  protected String[] getServletMappings() {
    // DispatcherServlet의 URL을 지정한다.
    return new String[] {"/app/*"};
  }

  @Override
  protected void customizeRegistration(ServletRegistration.Dynamic registration) {
    registration.setMultipartConfig(new MultipartConfigElement("temp", 10000000, 15000000, 1000000));
  }

  @Override
  protected WebApplicationContext createRootApplicationContext() {
    // ContextLoaderListener가 사용할 IoC 컨테이너를 리턴한다.
    // 만약 리턴하지 않으면 ContextLoaderListener는 생성되지 않는다.
    return null;
  }
}
