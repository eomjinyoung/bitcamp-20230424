package bitcamp.myapp.service;

import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;

@Service
public class DefaultBoardService implements BoardService {

  BoardDao boardDao;
  PlatformTransactionManager txManager;

  public DefaultBoardService(BoardDao boardDao, PlatformTransactionManager txManager) {
    this.boardDao = boardDao;
    this.txManager = txManager;
  }

  @Override
  public int add(Board board) throws Exception {
    DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    def.setName("tx1");
    def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    TransactionStatus status = txManager.getTransaction(def);

    try {
      int count = boardDao.insert(board);
      if (board.getAttachedFiles().size() > 0) {
        boardDao.insertFiles(board);
      }
      txManager.commit(status);
      return count;

    } catch (Exception e) {
      txManager.rollback(status);
      throw e;
    }
  }

  @Override
  public List<Board> list(int category) throws Exception {
    return null;
  }

  @Override
  public Board get(int boardNo) throws Exception {
    return null;
  }

  @Override
  public int update(Board board) throws Exception {
    return 0;
  }

  @Override
  public int delete(int boardNo) throws Exception {
    return 0;
  }

  @Override
  public int deleteAttachedFile(int fileNo) throws Exception {
    return 0;
  }
}
