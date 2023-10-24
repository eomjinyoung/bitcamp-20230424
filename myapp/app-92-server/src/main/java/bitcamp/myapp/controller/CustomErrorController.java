package bitcamp.myapp.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class CustomErrorController implements ErrorController {

    @RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String errorHtml(HttpServletRequest request,
                            HttpServletResponse response) {
        return "/error.html";
    }

    @RequestMapping
    @ResponseBody
    public RestResult error(HttpServletRequest request) {
        return RestResult.builder()
                .status(RestResult.FAILURE)
                .error("REST API 가 존재하지 않습니다. - " + request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI))
                .build();
    }
}
