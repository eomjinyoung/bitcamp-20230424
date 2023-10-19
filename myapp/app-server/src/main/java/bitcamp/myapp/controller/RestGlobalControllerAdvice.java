package bitcamp.myapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.beans.PropertyEditorSupport;

@RestControllerAdvice
public class RestGlobalControllerAdvice {

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(NoHandlerFoundException.class)
  public RestResult handle404(NoHandlerFoundException exception) {
    String message = "존재하지 않는 URL입니다. : " +  exception.getRequestURL();
    return RestResult.builder()
            .status(RestResult.FAILURE)
            .error(message)
            .build();
  }


  //@InitBinder
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
