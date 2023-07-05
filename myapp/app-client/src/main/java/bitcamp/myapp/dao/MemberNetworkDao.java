package bitcamp.myapp.dao;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.List;
import bitcamp.myapp.vo.Member;

public class MemberNetworkDao implements MemberDao {

  String dataName;
  DataInputStream in;
  DataOutputStream out;

  public MemberNetworkDao(String dataName, DataInputStream in, DataOutputStream out) {
    this.dataName = dataName;
    this.in = in;
    this.out = out;
  }

  @Override
  public void insert(Member member) {
  }

  @Override
  public List<Member> list() {
    return null;
  }

  @Override
  public Member findBy(int no) {
    return null;
  }

  @Override
  public int update(Member member) {
    return 0;
  }

  @Override
  public int delete(int no) {
    return 0;
  }
}
