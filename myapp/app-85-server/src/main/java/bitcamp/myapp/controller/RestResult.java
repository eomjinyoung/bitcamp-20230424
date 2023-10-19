package bitcamp.myapp.controller;

// 클라이언트에 응답할 데이터를 담는 객체

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestResult {
  public static final String SUCCESS = "success";
  public static final String FAILURE = "failure";

  private String status;
  private Object data;
  private String error;

  public static void main(String[] args) {
    RestResult result = RestResult.builder().status("success").data("data").error("error").build();
    System.out.println(result);
  }
}
