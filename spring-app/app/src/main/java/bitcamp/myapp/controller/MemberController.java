package bitcamp.myapp.controller;

import bitcamp.myapp.service.MemberService;
import bitcamp.myapp.service.NcpObjectStorageService;
import bitcamp.myapp.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

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
  public void form() {
  }

  @PostMapping("add")
  public String add(Member member, MultipartFile photofile) throws Exception {
    System.out.println(member);
    if (photofile.getSize() > 0) {
      String uploadFileUrl = ncpObjectStorageService.uploadFile(
              "bitcamp-nc7-bucket-118", "member/", photofile);
      member.setPhoto(uploadFileUrl);
    }
    memberService.add(member);
    return "redirect:list";
  }

  @GetMapping("delete")
  public String delete(int no) throws Exception {
    if (memberService.delete(no) == 0) {
      throw new Exception("해당 번호의 회원이 없습니다.");
    } else {
      return "redirect:list";
    }
  }

  @GetMapping("{no}")
  public String detail(@PathVariable int no, Model model) throws Exception {
    model.addAttribute("member", memberService.get(no));
    return "member/detail";
  }

  @GetMapping("list")
  public void list(Model model) throws Exception {
    model.addAttribute("list", memberService.list());
  }

  @PostMapping("update")
  public String update(Member member, MultipartFile photofile) throws Exception {
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
  }
}
