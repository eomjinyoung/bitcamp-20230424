package bitcamp.config;

import bitcamp.app2.Controller04_1_Interceptor2;
import bitcamp.app2.Controller04_1_Interceptor3;
import bitcamp.app2.Controller04_1_Interceptor4;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.util.UrlPathHelper;

@ComponentScan("bitcamp.app2")
@EnableWebMvc
// => Web MVC 관련 객체를 등록하고 설정한다.
// => WebMvcConfigurer 구현체를 찾아 메서드를 호출한다.
public class App2Config implements WebMvcConfigurer {

  @Bean
  public ViewResolver viewResolver() {
    System.out.println("App2Config.viewResolver() 호출됨!");
    InternalResourceViewResolver vr = new InternalResourceViewResolver();
    vr.setPrefix("/WEB-INF/jsp2/");
    vr.setSuffix(".jsp");
    vr.setViewClass(JstlView.class);
    return vr;
  }

  @Override
  public void configurePathMatch(PathMatchConfigurer configurer) {
    System.out.println("App2Config.configurePathMatch() 호출됨!");
    UrlPathHelper pathHelper = new UrlPathHelper();
    pathHelper.setRemoveSemicolonContent(false);

    // DispatcherServlet의 MVC Path 관련 설정을 변경한다.
    configurer.setUrlPathHelper(pathHelper);
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    System.out.println("App2Config.addInterceptors() 호출됨!");
    // 1) 모든 요청에 대해 실행할 인터셉터 등록하기
    // => 인터셉터를 적용할 URL을 지정하지 않으면
    // 현재 프론트 컨트롤러의 모든 요청에 대해 적용된다.
    //
    //registry.addInterceptor(new Controller04_1_Interceptor1());

    // 2) 특정 요청에 대해 실행할 인터셉터 등록하기
    // => 패턴: /c04_1/*
    // 적용 가능:
    // /c04_1/x
    // /c04_1/y
    // 적용 불가:
    // /x
    // /c03_1/x
    // /c04_1/a/x
    // /c04_1/a/b/x
    // 즉, /c04_1/ 바로 밑의 있는 자원에 대해서만 인터셉터를 적용한다.
    //
    registry
            .addInterceptor(new Controller04_1_Interceptor2())
            .addPathPatterns("/c04_1/*");


    // 3) 특정 요청에 대해 실행할 인터셉터 등록하기
    // => 패턴: /c04_1/**
    // 적용 가능:
    // /c04_1/x
    // /c04_1/y
    // /c04_1/a/x
    // /c04_1/a/b/x
    // 적용 불가:
    // /x
    // /c03_1/x
    // 즉, /c04_1/ 의 모든 하위 경로에 있는 자원에 대해서만 인터셉터를 적용한다.
    //
    registry
            .addInterceptor(new Controller04_1_Interceptor3())
            .addPathPatterns("/c04_1/**");

    // 4) 특정 요청에 대해 인터셉터 적용을 제외하기
    // => 패턴: /c04_1/** (include), /c04_1/a/** (exclude)
    // 적용 가능:
    // /c04_1/x
    // /c04_1/y
    // /c04_1/b/x
    // /c04_1/b/c/x
    // 적용 불가:
    // /x
    // /c03_1/x
    // /c04_1/a/x
    // /c04_1/a/b/x
    // 즉, /c04_1/ 의 모든 하위 경로에 있는 자원에 대해서만 인터셉터를 적용한다.
    // 단 /c04_1/a/ 의 모든 하위 경로에 있는 자원은 제외한다.
    registry
            .addInterceptor(new Controller04_1_Interceptor4())
            .addPathPatterns("/c04_1/**")
            .excludePathPatterns("/c04_1/a/**");
  }
}
