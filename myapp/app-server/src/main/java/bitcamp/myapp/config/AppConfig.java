package bitcamp.myapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.util.UrlPathHelper;

// Application을 실행하는데 필요한 객체를 설정하는 일을 한다.
//
@ComponentScan(
        basePackages = "bitcamp.myapp.controller",
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.REGEX,
                pattern = "bitcamp.myapp.controller.MemberController"
        )
)
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer {
  public AppConfig() {
    System.out.println("AppConfig() 호출됨!");
  }

  @Bean
  public MultipartResolver multipartResolver() {
    return new StandardServletMultipartResolver();
  }

  @Bean
  public ViewResolver viewResolver() {
    InternalResourceViewResolver vr = new InternalResourceViewResolver();
    vr.setViewClass(JstlView.class);
    vr.setPrefix("/WEB-INF/jsp/");
    vr.setSuffix(".jsp");
    return vr;
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

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    System.out.println("AppConfig.addInterceptors() 호출됨!");
//    registry
//            .addInterceptor(new MyInterceptor())
//            .addPathPatterns("/c04_1/**");
  }
}
