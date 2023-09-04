package bitcamp.myapp.service;

import bitcamp.myapp.vo.Board;

import java.util.List;

public interface BoardService {
  int add(Board board) throws Exception;
  List<Board> list(int category) throws Exception;
  Board get(int boardNo) throws Exception;
  int update(Board board) throws Exception;
  int delete(int boardNo) throws Exception;

  int deleteAttachedFile(int fileNo) throws Exception;
}
