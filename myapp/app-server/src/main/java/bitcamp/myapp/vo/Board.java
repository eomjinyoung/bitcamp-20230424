package bitcamp.myapp.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Data
public class Board implements Serializable {
  private static final long serialVersionUID = 1L;

  private int no;
  private String title;
  private String content;
  private Member writer;
  private String password;
  private int viewCount;

  @JsonFormat(
          shape=JsonFormat.Shape.STRING,
          pattern="yyyy-MM-dd")
  private Timestamp createdDate;

  private int category;
  private List<AttachedFile> attachedFiles;
}
