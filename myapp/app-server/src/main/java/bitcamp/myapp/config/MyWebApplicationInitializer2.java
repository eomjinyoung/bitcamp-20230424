package bitcamp.myapp.config;

import org.springframework.web.context.AbstractContextLoaderInitializer;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class MyWebApplicationInitializer2 extends AbstractContextLoaderInitializer {

  @Override
  public void onStartup(ServletContext sc) throws ServletException {
    System.out.println("MyWebApplicationInitializer2.onStartup() 호출됨!");

//    // 수퍼 클래스에 정의된 작업은 그대로 수행하고,
//    // => ContextLoaderListener를 준비하는 작업
//    // => 그런데 createRootApplicationContext() 메서드가 null을 리턴한다면 ContextLoaderListener는 준비하지 않는다.
//    super.onStartup(sc);
//
//    // 추가적으로 다음의 작업을 수행한다.
//    // => DispatcherServlet 을 준비하는 작업
//
//    // DispatcherServlet이 사용할 IoC 컨테이너를 준비한다.
//    AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
//    context.register(AppConfig.class);
//
//    // DispatcherServlet을 생성하여 서블릿 컨테이너에 등록한다.
//    DispatcherServlet servlet = new DispatcherServlet(context);
//    ServletRegistration.Dynamic registration = sc.addServlet("app", servlet);
//    registration.setLoadOnStartup(1);
//    registration.addMapping("/app/*");
//    registration.setMultipartConfig(new MultipartConfigElement("temp", 10000000, 15000000, 1000000));
  }

  @Override
  protected WebApplicationContext createRootApplicationContext() {
    // ContextLoaderListener가 사용할 IoC 컨테이너를 리턴한다.
    // 만약 리턴하지 않으면 ContextLoaderListener는 생성되지 않는다.
    return null;
  }
}
