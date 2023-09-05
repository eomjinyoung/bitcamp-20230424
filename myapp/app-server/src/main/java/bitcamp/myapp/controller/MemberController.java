package bitcamp.myapp.controller;

import bitcamp.myapp.service.MemberService;
import bitcamp.myapp.service.NcpObjectStorageService;
import bitcamp.myapp.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@Controller
public class MemberController {

  @Autowired
  MemberService memberService;

  @Autowired
  NcpObjectStorageService ncpObjectStorageService;

  @RequestMapping("/member/add")
  public String add(HttpServletRequest request, HttpServletResponse response) throws Exception {
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

  @RequestMapping("/member/delete")
  public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {

    try {
      if (memberService.delete(Integer.parseInt(request.getParameter("no"))) == 0) {
        throw new Exception("해당 번호의 회원이 없습니다.");
      } else {
        return "redirect:list";
      }

    } catch (Exception e) {
      request.setAttribute("refresh", "2;url=list");
      throw e;
    }
  }

  @RequestMapping("/member/detail")
  public String detail(HttpServletRequest request, HttpServletResponse response) throws Exception {
    request.setAttribute("member", memberService.get(Integer.parseInt(request.getParameter("no"))));
    return "/WEB-INF/jsp/member/detail.jsp";
  }

  @RequestMapping("/member/list")
  public String list(HttpServletRequest request, HttpServletResponse response) throws Exception {
    request.setAttribute("list", memberService.list());
    return "/WEB-INF/jsp/member/list.jsp";
  }

  @RequestMapping("/member/update")
  public String update(HttpServletRequest request, HttpServletResponse response) throws Exception {
    try {
      Member member = new Member();
      member.setNo(Integer.parseInt(request.getParameter("no")));
      member.setName(request.getParameter("name"));
      member.setEmail(request.getParameter("email"));
      member.setPassword(request.getParameter("password"));
      member.setGender(request.getParameter("gender").charAt(0));

      Part photoPart = request.getPart("photo");
      if (photoPart.getSize() > 0) {
        String uploadFileUrl = ncpObjectStorageService.uploadFile(
                "bitcamp-nc7-bucket-118", "member/", photoPart);
        member.setPhoto(uploadFileUrl);
      }

      if (memberService.update(member) == 0) {
        throw new Exception("회원이 없습니다.");
      } else {
        return "redirect:list";
      }

    } catch (Exception e) {
      request.setAttribute("refresh", "2;url=list");
      throw e;
    }
  }
}
