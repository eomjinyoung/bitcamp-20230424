package bitcamp.myapp.controller;

import bitcamp.myapp.service.MemberService;
import bitcamp.myapp.service.NcpObjectStorageService;
import bitcamp.myapp.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

@Controller
public class MemberController {

  @Autowired
  MemberService memberService;

  @Autowired
  NcpObjectStorageService ncpObjectStorageService;

  @RequestMapping("/member/form")
  public String add() {
    return "/WEB-INF/jsp/member/form.jsp";
  }

  @RequestMapping("/member/add")
  public String add(
          Member member,
          @RequestParam("photofile") Part photofile,
          HttpServletRequest request) throws Exception {

    try {
      System.out.println(member);
      if (photofile.getSize() > 0) {
        String uploadFileUrl = ncpObjectStorageService.uploadFile(
                "bitcamp-nc7-bucket-118", "member/", photofile);
        member.setPhoto(uploadFileUrl);
      }
      memberService.add(member);
      return "redirect:list";

    } catch (Exception e) {
      request.setAttribute("message", "회원 등록 오류!");
      request.setAttribute("refresh", "2;url=list");
      throw e;
    }
  }

  @RequestMapping("/member/delete")
  public String delete(@RequestParam("no") int no, HttpServletRequest request) throws Exception {

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
  public String detail(
          @RequestParam("no") int no,
          HttpServletRequest request) throws Exception {
    request.setAttribute("member", memberService.get(no));
    return "/WEB-INF/jsp/member/detail.jsp";
  }

  @RequestMapping("/member/list")
  public String list(HttpServletRequest request) throws Exception {
    request.setAttribute("list", memberService.list());
    return "/WEB-INF/jsp/member/list.jsp";
  }

  @RequestMapping("/member/update")
  public String update(
          Member member,
          @RequestParam("photofile") Part photofile,
          HttpServletRequest request) throws Exception {
    try {
      if (photofile.getSize() > 0) {
        String uploadFileUrl = ncpObjectStorageService.uploadFile(
                "bitcamp-nc7-bucket-118", "member/", photofile);
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
