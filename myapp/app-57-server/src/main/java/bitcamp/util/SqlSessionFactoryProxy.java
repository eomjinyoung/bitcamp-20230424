package bitcamp.util;

import java.sql.Connection;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.TransactionIsolationLevel;

public class SqlSessionFactoryProxy implements SqlSessionFactory {

  SqlSessionFactory original;

  ThreadLocal<SqlSession> sqlSessionBox = new ThreadLocal<>();

  public SqlSessionFactoryProxy(SqlSessionFactory original) {
    this.original = original;
  }

  public void clean() {
    SqlSession sqlSession = sqlSessionBox.get();
    if (sqlSession != null) {
      sqlSession.close();
      sqlSession.rollback();
      sqlSessionBox.remove();
      System.out.println("스레드에서 SqlSession 제거!");
    }
  }

  public SqlSession openSession() {
    return openSession(true);
  }

  public SqlSession openSession(boolean autoCommit) {
    SqlSession sqlSession = sqlSessionBox.get();
    if (sqlSession == null) {
      sqlSession = original.openSession(autoCommit);
      sqlSessionBox.set(sqlSession);
    }
    return sqlSession;
  }

  public SqlSession openSession(Connection connection) {
    return original.openSession(connection);
  }

  public SqlSession openSession(TransactionIsolationLevel level) {
    return original.openSession(level);
  }

  public SqlSession openSession(ExecutorType execType) {
    return original.openSession(execType);
  }

  public SqlSession openSession(ExecutorType execType, boolean autoCommit) {
    return original.openSession(execType, autoCommit);
  }

  public SqlSession openSession(ExecutorType execType, TransactionIsolationLevel level) {
    return original.openSession(execType, level);
  }

  public SqlSession openSession(ExecutorType execType, Connection connection) {
    return original.openSession(execType, connection);
  }

  public Configuration getConfiguration() {
    return original.getConfiguration();
  }
}
