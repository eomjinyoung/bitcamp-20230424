package bitcamp.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

  public HomeController() {
    System.out.println("HomeController 생성됨!");
  }

  @RequestMapping("/")
  public String home() throws Exception {
    return "/WEB-INF/jsp/index.jsp";
  }
}
