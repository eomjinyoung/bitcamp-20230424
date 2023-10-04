package bitcamp.myapp.vo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class Member implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final char MALE = 'M';
  public static final char FEMALE = 'W';

  private int no;
  private String name;
  private String email;
  private String password;
  private char gender;
  private Date createdDate;
  private String photo;

}
