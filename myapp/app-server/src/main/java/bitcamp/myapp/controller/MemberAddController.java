package bitcamp.myapp.controller;

import bitcamp.myapp.service.MemberService;
import bitcamp.myapp.service.NcpObjectStorageService;
import bitcamp.myapp.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@Controller("/member/add")
public class MemberAddController {

  @Autowired
  MemberService memberService;

  @Autowired
  NcpObjectStorageService ncpObjectStorageService;

  @RequestMapping
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    if (request.getMethod().equals("GET")) {
      return "/WEB-INF/jsp/member/form.jsp";
    }

    try {
      Member m = new Member();
      m.setName(request.getParameter("name"));
      m.setEmail(request.getParameter("email"));
      m.setPassword(request.getParameter("password"));
      m.setGender(request.getParameter("gender").charAt(0));

      Part photoPart = request.getPart("photo");
      if (photoPart.getSize() > 0) {
        String uploadFileUrl = ncpObjectStorageService.uploadFile(
                "bitcamp-nc7-bucket-118", "member/", photoPart);
        m.setPhoto(uploadFileUrl);
      }
      memberService.add(m);
      return "redirect:list";

    } catch (Exception e) {
      request.setAttribute("message", "회원 등록 오류!");
      request.setAttribute("refresh", "2;url=list");
      throw e;
    }
  }
}
