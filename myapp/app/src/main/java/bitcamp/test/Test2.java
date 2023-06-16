package bitcamp.test;

import bitcamp.myapp.vo.Member;

public class Test2 {
  public static void main(String[] args) {
    Member m1 = new Member();
    m1.setNo(1);
    m1.setName("홍길동");
    m1.setEmail("hong@test.com");
    m1.setPassword("1111");
    m1.setGender('M');

    Member m2 = new Member();
    m2.setNo(1);
    m2.setName("홍길동");
    m2.setEmail("hong@test.com");
    m2.setPassword("1111");
    m2.setGender('M');

    System.out.println(m1 == m2); // 각 레퍼런스에 저장된 인스턴스 주소를 비교
    System.out.println(m1.equals(m2));
    // equals()는 수퍼 클래스 Object의 메서드이다.
    // Object의 equals()는 두 인스턴스가 같은지 비교한다.

  }
}
