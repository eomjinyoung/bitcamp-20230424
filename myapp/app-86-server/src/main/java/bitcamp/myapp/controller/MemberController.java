package bitcamp.myapp.controller;

import bitcamp.myapp.service.MemberService;
import bitcamp.myapp.service.NcpObjectStorageService;
import bitcamp.myapp.vo.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;

  private final NcpObjectStorageService ncpObjectStorageService;

  @PostMapping
  public RestResult add(Member member, MultipartFile photofile) throws Exception {
    if (photofile.getSize() > 0) {
      String uploadFileUrl = ncpObjectStorageService.uploadFile(
              "bitcamp-nc7-bucket-118", "member/", photofile);
      member.setPhoto(uploadFileUrl);
    }
    memberService.add(member);
    return RestResult.builder()
            .status(RestResult.SUCCESS)
            .data(member)
            .build();
  }

  @DeleteMapping("{no}")
  public RestResult delete(@PathVariable int no) throws Exception {
    if (memberService.delete(no) == 0) {
      return RestResult.builder()
              .status(RestResult.FAILURE)
              .error("해당 번호의 회원이 없습니다.")
              .build();
    }
    return RestResult.builder().status(RestResult.SUCCESS).build();
  }

  @GetMapping("{no}")
  public RestResult detail(@PathVariable int no) throws Exception {
    Member member = memberService.get(no);

    if (member == null) {
      return RestResult.builder()
              .status(RestResult.FAILURE)
              .error("해당 번호의 회원이 없습니다.")
              .build();
    }

    return RestResult.builder()
            .status(RestResult.SUCCESS)
            .data(member)
            .build();
  }

  @GetMapping
  public RestResult list() throws Exception {
    return RestResult.builder()
            .status(RestResult.SUCCESS)
            .data(memberService.list())
            .build();
  }

  @PutMapping("{no}")
  public RestResult update(@PathVariable int no, Member member, MultipartFile photofile) throws Exception {
    if (photofile.getSize() > 0) {
      String uploadFileUrl = ncpObjectStorageService.uploadFile(
              "bitcamp-nc7-bucket-118", "member/", photofile);
      member.setPhoto(uploadFileUrl);
    }

    if (memberService.update(member) == 0) {
      return RestResult.builder()
              .status(RestResult.FAILURE)
              .error("회원이 없습니다.")
              .build();
    }

    return RestResult.builder()
            .status(RestResult.SUCCESS)
            .build();
  }
}
