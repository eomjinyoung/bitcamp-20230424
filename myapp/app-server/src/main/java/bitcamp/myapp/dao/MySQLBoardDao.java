package bitcamp.myapp.dao;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import bitcamp.myapp.vo.Board;
import bitcamp.util.DataSource;

public class MySQLBoardDao implements BoardDao {

  SqlSessionFactory sqlSessionFactory;
  DataSource ds;
  int category;

  public MySQLBoardDao(SqlSessionFactory sqlSessionFactory, DataSource ds, int category) {
    this.sqlSessionFactory = sqlSessionFactory;
    this.ds = ds;
    this.category = category;
  }

  @Override
  public void insert(Board board) {
    board.setCategory(this.category);
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    sqlSession.insert("bitcamp.myapp.dao.BoardDao.insert", board);
  }

  @Override
  public List<Board> findAll() {
    SqlSession sqlSession = sqlSessionFactory.openSession(true);
    return sqlSession.selectList("bitcamp.myapp.dao.BoardDao.findAll", this.category);
  }


  @Override
  public Board findBy(int no) {
    SqlSession sqlSession = sqlSessionFactory.openSession(true);

    Map<String,Object> paramMap = new HashMap<>();
    paramMap.put("categoryNo", this.category);
    paramMap.put("boardNo", no);

    return sqlSession.selectOne("bitcamp.myapp.dao.BoardDao.findBy", paramMap);
  }

  @Override
  public int update(Board board) {
    try (PreparedStatement stmt = ds.getConnection(false).prepareStatement(
        "update myapp_board set"
            + " title=?,"
            + " content=?"
            + " where category=? and board_no=? and writer=?")) {

      stmt.setString(1, board.getTitle());
      stmt.setString(2, board.getContent());
      stmt.setInt(3, this.category);
      stmt.setInt(4, board.getNo());
      stmt.setInt(5, board.getWriter().getNo());

      return stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int updateCount(Board board) {
    board.setCategory(this.category);
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.update("bitcamp.myapp.dao.BoardDao.updateCount", board);
  }

  @Override
  public int delete(Board board) {
    try (PreparedStatement stmt = ds.getConnection(false).prepareStatement(
        "delete from myapp_board"
            + " where category=? and board_no=? and writer=?")) {

      stmt.setInt(1, this.category);
      stmt.setInt(2, board.getNo());
      stmt.setInt(3, board.getWriter().getNo());

      return stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
