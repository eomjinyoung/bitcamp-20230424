package bitcamp.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BufferedDataInputStream extends FileInputStream {

  byte[] buf = new byte[8192];
  int size; // 배열에 저장되어 있는 바이트의 수
  int cursor; // 바이트 읽은 배열의 위치

  public BufferedDataInputStream(String name) throws FileNotFoundException {
    super(name);
  }

  @Override
  public int read() throws IOException {
    if (size == -1) {
      return -1;
    }

    if (cursor == size) { // 바이트 배열에 저장되어 있는 데이터를 모두 읽었다면,
      if ((size = super.read(buf)) == -1) { // 다시 파일에서 바이트 배열로 데이터를 왕창 읽어 온다.
        return -1;
      }
      cursor = 0;
    }
    return buf[cursor++] & 0x000000ff;
  }

  @Override
  public int read(byte[] arr) throws IOException {
    for (int i = 0; i < arr.length; i++) {
      int b = this.read();
      if (b == -1) {
        return i;
      }
      arr[i] = (byte) b;
    }
    return arr.length;
  }

  public short readShort() throws IOException {
    return (short)(this.read() << 8 | this.read());
  }

  public int readInt() throws IOException {
    return this.read() << 24 | this.read() << 16 | this.read() << 8 | this.read();
  }

  public long readLong() throws IOException {
    return (long)this.read() << 56
        | (long)this.read() << 48
        | (long)this.read() << 40
        | (long)this.read() << 32
        | (long)this.read() << 24
        | (long)this.read() << 16
        | (long)this.read() << 8
        | this.read();
  }

  public char readChar() throws IOException {
    return (char)(this.read() << 8 | this.read());
  }

  public String readUTF() throws IOException {
    int length = this.read() << 8 | this.read();
    byte[] buf = new byte[length];
    this.read(buf);
    return new String(buf, "UTF-8");
  }

}











