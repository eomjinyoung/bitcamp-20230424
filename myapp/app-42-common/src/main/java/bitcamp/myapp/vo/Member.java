package bitcamp.myapp.vo;

import java.io.Serializable;

public class Member implements Serializable, CsvObject, AutoIncrement {
  private static final long serialVersionUID = 1L;

  public static int userId = 1;

  public static final char MALE = 'M';
  public static final char FEMALE = 'W';

  private int no;
  private String name;
  private String email;
  private String password;
  private char gender;

  public Member() {}

  public Member(int no) {
    this.no = no;
  }

  public static Member fromCsv(String csv) {
    String[] values = csv.split(",");

    Member member = new Member(Integer.parseInt(values[0]));
    member.setName(values[1]);
    member.setEmail(values[2]);
    member.setPassword(values[3]);
    member.setGender(values[4].charAt(0));

    if (Member.userId <= member.getNo()) {
      Member.userId = member.getNo() + 1;
    }

    return member;
  }

  @Override
  public String toCsvString() {
    return String.format("%d,%s,%s,%s,%c",
        this.getNo(),
        this.getName(),
        this.getEmail(),
        this.getPassword(),
        this.getGender());
  }

  @Override
  public void updateKey() {
    if (Member.userId <= this.no) {
      Member.userId = this.no + 1;
    }
  }

  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (this.getClass() != obj.getClass()) {
      return false;
    }
    Member m = (Member) obj;
    if (this.getNo() != m.getNo()) {
      return false;
    }
    return true;
  }

  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public char getGender() {
    return gender;
  }
  public void setGender(char gender) {
    this.gender = gender;
  }

}
