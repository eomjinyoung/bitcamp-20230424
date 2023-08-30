package bitcamp.myapp.controller;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.service.NcpObjectStorageService;
import bitcamp.myapp.vo.Member;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@Component("/member/add")
public class MemberAddController implements PageController {

  MemberDao memberDao;
  SqlSessionFactory sqlSessionFactory;
  NcpObjectStorageService ncpObjectStorageService;

  public MemberAddController(
          MemberDao memberDao,
          SqlSessionFactory sqlSessionFactory,
          NcpObjectStorageService ncpObjectStorageService) {
    this.memberDao = memberDao;
    this.sqlSessionFactory = sqlSessionFactory;
    this.ncpObjectStorageService = ncpObjectStorageService;
  }

  @Override
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
      memberDao.insert(m);
      sqlSessionFactory.openSession(false).commit();
      return "redirect:list";

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      request.setAttribute("message", "회원 등록 오류!");
      request.setAttribute("refresh", "2;url=list");
      throw e;
    }
  }
}
