package bitcamp.myapp.service;

import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.Board;

import java.util.List;

public interface BoardService {
  int add(Board board);
  List<Board> list(int category);
  Board get(int boardNo);
  int update(Board board);
  int delete(int boardNo);
  int increaseViewCount(int boardNo);

  AttachedFile getAttachedFile(int fileNo);
  int deleteAttachedFile(int fileNo);
}
