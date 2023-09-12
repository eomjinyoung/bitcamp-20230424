package bitcamp.myapp.controller;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

import java.beans.PropertyEditorSupport;

@ControllerAdvice
public class GlobalControllerAdvice {

  @ExceptionHandler
  public ModelAndView exceptionHandler(Exception ex) {
    ModelAndView mv = new ModelAndView();
    mv.addObject("exception", ex);
    mv.addObject("message", ex.getMessage());
    mv.setViewName("error");
    return mv;
  }

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(java.util.Date.class, new PropertyEditorSupport() {
      @Override
      public void setAsText(String text) throws IllegalArgumentException {
        try {
          setValue(java.sql.Date.valueOf(text));
        } catch (Exception e) {
          throw new IllegalArgumentException(e);
        }
      }
    });
  }
}
