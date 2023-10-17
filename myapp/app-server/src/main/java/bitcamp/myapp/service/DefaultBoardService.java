package bitcamp.myapp.service;

import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultBoardService implements BoardService {

  private final BoardDao boardDao;

  @Transactional // 이 메서드는 트랜잭션 상태에서 실행하라고 지정
  @Override
  public int add(Board board) {
    int count = boardDao.insert(board);
    if (board.getAttachedFiles().size() > 0) {
      boardDao.insertFiles(board);
    }
    return count;
  }

  @Override
  public List<Board> list(int category) {
    return boardDao.findAll(category);
  }

  @Override
  public Board get(int boardNo) {
    return boardDao.findBy(boardNo);
  }

  @Transactional
  @Override
  public int update(Board board) {
    int count = boardDao.update(board);
    if (count > 0 && board.getAttachedFiles().size() > 0) {
      boardDao.insertFiles(board);
    }
    return count;
  }

  @Transactional
  @Override
  public int delete(int boardNo) {
    boardDao.deleteFiles(boardNo);
    return boardDao.delete(boardNo);
  }

  @Transactional
  @Override
  public int increaseViewCount(int boardNo) {
    return boardDao.updateCount(boardNo);
  }

  @Override
  public AttachedFile getAttachedFile(int fileNo) {
    return boardDao.findFileBy(fileNo);
  }

  @Override
  public int deleteAttachedFile(int fileNo) {
    return boardDao.deleteFile(fileNo);
  }
}
