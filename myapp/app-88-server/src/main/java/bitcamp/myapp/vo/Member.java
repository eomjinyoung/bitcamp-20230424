package bitcamp.myapp.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.sql.Date;

@Data
public class Member {

  public static final char MALE = 'M';
  public static final char FEMALE = 'W';

  private int no;
  private String name;
  private String email;
  @JsonIgnore
  private String password;
  private char gender;

  @JsonFormat(
          shape=JsonFormat.Shape.STRING,
          pattern="yyyy-MM-dd")
  private Date createdDate;

  private String photo;
}
