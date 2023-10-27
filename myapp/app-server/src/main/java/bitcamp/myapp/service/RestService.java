package bitcamp.myapp.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Configuration
public interface RestService {

  @FeignClient(name = "facebook", url = "${restapi.facebook.url}")
  public static interface FacebookService {
    @GetMapping("/me")
    public Map<String, Object> me(
            @RequestParam String access_token,
            @RequestParam String fields);
  }

  @FeignClient(name = "jwt", url = "${restapi.jwt.url}")
  public static interface JwtService {
    @PostMapping(value = "/token",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String token(@RequestHeader String authorization);
  }
}
