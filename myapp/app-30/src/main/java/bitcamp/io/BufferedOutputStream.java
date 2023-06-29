package bitcamp.io;

import java.io.IOException;
import java.io.OutputStream;

public class BufferedOutputStream extends OutputStream {

  OutputStream original;

  byte[] buf = new byte[8192];
  int cursor;

  public BufferedOutputStream(OutputStream original) {
    this.original = original;
  }

  @Override
  public void write(int b) throws IOException {
    if (cursor == buf.length) { // 버퍼가 다 찼다면,
      original.write(buf); // 버퍼에 들어있는 데이터를 한 번에 출력한다.
      cursor = 0; // 다시 커서를 초기화시킨다.
    }
    buf[cursor++] = (byte) b; // 버퍼에 빈 공간이 있다면 버퍼에 저장한다.
  }

  @Override
  public void flush() throws IOException {
    original.write(buf, 0, cursor);
    cursor = 0;
  }

  @Override
  public void close() throws IOException {
    this.flush();
    original.close();
  }

  @Override
  public void write(byte[] arr) throws IOException {
    for (int i = 0; i < arr.length; i++) {
      this.write(arr[i]);
    }
  }

  public void writeShort(int v) throws IOException {
    this.write(v >> 8);
    this.write(v);
  }

  public void writeInt(int v) throws IOException {
    this.write(v >> 24);
    this.write(v >> 16);
    this.write(v >> 8);
    this.write(v);
  }

  public void writeLong(long v) throws IOException {
    this.write((int)(v >> 56));
    this.write((int)(v >> 48));
    this.write((int)(v >> 40));
    this.write((int)(v >> 32));
    this.write((int)(v >> 24));
    this.write((int)(v >> 16));
    this.write((int)(v >> 8));
    this.write((int) v);
  }

  public void writeChar(int v) throws IOException {
    this.write(v >> 8);
    this.write(v);
  }

  public void writeUTF(String str) throws IOException {
    byte[] bytes = str.getBytes("UTF-8");
    this.write(bytes.length >> 8);
    this.write(bytes.length);
    this.write(bytes);
  }
}
