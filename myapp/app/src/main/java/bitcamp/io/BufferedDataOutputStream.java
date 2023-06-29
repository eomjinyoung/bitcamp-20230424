package bitcamp.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedDataOutputStream extends FileOutputStream {

  public BufferedDataOutputStream(String name) throws FileNotFoundException {
    super(name);
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
