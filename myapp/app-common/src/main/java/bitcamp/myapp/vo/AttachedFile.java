package bitcamp.myapp.vo;

import java.io.Serializable;

public class AttachedFile implements Serializable {
  private static final long serialVersionUID = 1L;

  int no;
  String originName;
  String filePath;

  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getOriginName() {
    return originName;
  }
  public void setOriginName(String originName) {
    this.originName = originName;
  }
  public String getFilePath() {
    return filePath;
  }
  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }


}
