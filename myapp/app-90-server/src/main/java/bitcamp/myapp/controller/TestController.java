package bitcamp.myapp.controller;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TestController {

  @GetMapping(value="/ex01/exam05_1", produces = "text/plain;charset=UTF-8")
  @ResponseBody
  public String exam051() throws Exception {
    Thread.sleep(10000);
    return "console.log('Hello!111111');";
  }

  @GetMapping(value="/ex01/exam05_2", produces = "text/plain;charset=UTF-8")
  @ResponseBody
  public String exam052() throws Exception {

    return "console.log('Hello!222222');";
  }

  @GetMapping(value="/ex01/exam05_x", produces = "text/plain;charset=UTF-8")
  @ResponseBody
  public String exam05x() throws Exception {
    Thread.sleep(10000);
    return "var rate = 35000;";
  }
}
