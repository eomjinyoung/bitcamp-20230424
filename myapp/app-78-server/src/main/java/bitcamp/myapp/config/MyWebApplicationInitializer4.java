package bitcamp.myapp.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class MyWebApplicationInitializer4 extends AbstractAnnotationConfigDispatcherServletInitializer {

  @Override
  public void onStartup(ServletContext sc) throws ServletException {
    System.out.println("MyWebApplicationInitializer4.onStartup() 호출됨!");

    // 수퍼 클래스에 정의된 작업은 그대로 수행하고,
    // => DispatcherServlet이 사용할 IoC 컨테이너를 준비한다.
    super.onStartup(sc);
  }

  @Override
  protected Class<?>[] getRootConfigClasses() {
    // ContextLoaderListener의 IoC 컨테이너가 사용할 java config 클래스를 지정한다.
    return null;
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    // DispatcherServlet의 IoC 컨테이너가 사용할 java config 클래스를 지정한다.
    return new Class[] {AppConfig.class};
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
}
