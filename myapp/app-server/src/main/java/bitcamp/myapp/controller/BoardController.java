package bitcamp.myapp.controller;

import bitcamp.myapp.service.BoardService;
import bitcamp.myapp.service.NcpObjectStorageService;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

  private final BoardService boardService;

  private final NcpObjectStorageService ncpObjectStorageService;


  @PostMapping("{category}")
  public RestResult add(@PathVariable int category, Board board, MultipartFile[] files, HttpSession session) throws Exception {
    Member loginUser = (Member) session.getAttribute("loginUser");
    board.setWriter(loginUser);

    ArrayList<AttachedFile> attachedFiles = new ArrayList<>();
    for (MultipartFile part : files) {
      if (part.getSize() > 0) {
        String uploadFileUrl = ncpObjectStorageService.uploadFile(
                "bitcamp-nc7-bucket-118", "board/", part);
        AttachedFile attachedFile = new AttachedFile();
        attachedFile.setFilePath(uploadFileUrl);
        attachedFiles.add(attachedFile);
      }
    }
    board.setAttachedFiles(attachedFiles);
    boardService.add(board);

    return RestResult.builder()
            .status(RestResult.SUCCESS)
            .data(board)
            .build();
  }

  @DeleteMapping("{category}/{no}")
  public RestResult delete(@PathVariable int category, @PathVariable int no, HttpSession session) throws Exception {
    Member loginUser = (Member) session.getAttribute("loginUser");

    Board b = boardService.get(no);
    if (b == null || b.getWriter().getNo() != loginUser.getNo()) {
      return RestResult.builder()
              .status(RestResult.FAILURE)
              .error("게시글이 존재하지 않거나 삭제 권한이 없습니다.")
              .build();
    }

    boardService.delete(b.getNo());

    return RestResult.builder().status(RestResult.SUCCESS).build();
  }

  @GetMapping("{category}/{no}")
  public RestResult detail(@PathVariable int category, @PathVariable int no) throws Exception {
    Board board = boardService.get(no);

    if (board == null) {
      return RestResult.builder()
              .status(RestResult.FAILURE)
              .error("해당 번호의 게시글이 없습니다.")
              .build();
    }

    boardService.increaseViewCount(no);
    return RestResult.builder()
            .status(RestResult.SUCCESS)
            .data(board)
            .build();
  }

  @GetMapping("{category}")
  public RestResult list(@PathVariable int category) throws Exception {
    return RestResult.builder()
            .status(RestResult.SUCCESS)
            .data(boardService.list(category))
            .build();
  }

  @PutMapping("{category}/{no}")
  public RestResult update(@PathVariable int category, @PathVariable int no, Board board, MultipartFile[] files, HttpSession session) throws Exception {
    Member loginUser = (Member) session.getAttribute("loginUser");

    Board b = boardService.get(board.getNo());
    if (b == null || b.getWriter().getNo() != loginUser.getNo()) {
      return RestResult.builder()
              .status(RestResult.FAILURE)
              .error("게시글이 존재하지 않거나 변경 권한이 없습니다.")
              .build();
    }

    ArrayList<AttachedFile> attachedFiles = new ArrayList<>();
    for (MultipartFile part : files) {
      if (part.getSize() > 0) {
        String uploadFileUrl = ncpObjectStorageService.uploadFile(
                "bitcamp-nc7-bucket-118", "board/", part);
        AttachedFile attachedFile = new AttachedFile();
        attachedFile.setFilePath(uploadFileUrl);
        attachedFiles.add(attachedFile);
      }
    }
    board.setAttachedFiles(attachedFiles);

    boardService.update(board);

    return RestResult.builder()
            .status(RestResult.SUCCESS)
            .data(board)
            .build();
  }

  @DeleteMapping("{category}/{boardNo}/files/{fileNo}") //
  public RestResult fileDelete(
          @PathVariable int category,
          @PathVariable int boardNo,
          @PathVariable int fileNo,
          HttpSession session) throws Exception {
    Member loginUser = (Member) session.getAttribute("loginUser");

    AttachedFile attachedFile = boardService.getAttachedFile(fileNo);
    if (attachedFile == null || attachedFile.getBoardNo() != boardNo) {
      return RestResult.builder()
              .status(RestResult.FAILURE)
              .error("첨부파일이 없거나 해당 게시글의 첨부파일이 아닙니다.")
              .build();
    }

    Board board = boardService.get(boardNo);
    if (board.getWriter().getNo() != loginUser.getNo()) {
      return RestResult.builder()
              .status(RestResult.FAILURE)
              .error("게시글 변경 권한이 없습니다!")
              .build();
    }

    boardService.deleteAttachedFile(fileNo);

    return RestResult.builder().status(RestResult.SUCCESS).build();
  }
}











