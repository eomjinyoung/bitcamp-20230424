package bitcamp.myapp.controller;

import bitcamp.myapp.service.MemberService;
import bitcamp.myapp.service.NcpObjectStorageService;
import bitcamp.myapp.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Part;
import java.util.Map;

@Controller
@RequestMapping("/member")
public class MemberController {

  {
    System.out.println("MemberController 생성됨!");
  }

  @Autowired
  MemberService memberService;

  @Autowired
  NcpObjectStorageService ncpObjectStorageService;

  @GetMapping("form")
  public String add() {
    return "/WEB-INF/jsp/member/form.jsp";
  }

  @PostMapping("add")
  public String add(
          Member member,
          Part photofile,
          Map<String,Object> model) throws Exception {

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
      model.put("message", "회원 등록 오류!");
      model.put("refresh", "2;url=list");
      throw e;
    }
  }

  @GetMapping("delete")
  public String delete(
          int no,
          Map<String,Object> model) throws Exception {

    try {
      if (memberService.delete(no) == 0) {
        throw new Exception("해당 번호의 회원이 없습니다.");
      } else {
        return "redirect:list";
      }
    } catch (Exception e) {
      model.put("refresh", "2;url=list");
      throw e;
    }
  }

  @GetMapping("detail")
  public String detail(
          int no,
          Map<String,Object> model) throws Exception {
    model.put("member", memberService.get(no));
    return "/WEB-INF/jsp/member/detail.jsp";
  }

  @GetMapping("list")
  public String list(Map<String,Object> model) throws Exception {
    model.put("list", memberService.list());
    return "/WEB-INF/jsp/member/list.jsp";
  }

  @PostMapping("update")
  public String update(
          Member member,
          Part photofile,
          Map<String,Object> model) throws Exception {
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
      model.put("refresh", "2;url=list");
      throw e;
    }
  }
}
