package bitcamp.myapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

@EnableTransactionManagement
@SpringBootApplication
public class App implements WebMvcConfigurer {

  public static void main(String[] args) throws Exception {
    SpringApplication.run(App.class, args);
  }

  @Override
  public void configurePathMatch(PathMatchConfigurer configurer) {
    System.out.println("AppConfig.configurePathMatch() 호출됨!");
    UrlPathHelper pathHelper = new UrlPathHelper();

    // @MatrixVariable 기능 활성화
    pathHelper.setRemoveSemicolonContent(false);

    // DispatcherServlet의 MVC Path 관련 설정을 변경한다.
    configurer.setUrlPathHelper(pathHelper);
  }

//  @Override
//  public void addInterceptors(InterceptorRegistry registry) {
//    System.out.println("AppConfig.addInterceptors() 호출됨!");
////    registry
////            .addInterceptor(new MyInterceptor())
////            .addPathPatterns("/c04_1/**");
//  }

}
