package bitcamp.io;

import java.io.IOException;
import java.io.OutputStream;

public class DataOutputStream extends OutputStream {

  OutputStream original;

  public DataOutputStream(OutputStream original) {
    this.original = original;
  }

  @Override
  public void write(int b) throws IOException {
    original.write(b);
  }

  @Override
  public void flush() throws IOException {
    original.flush();
  }

  @Override
  public void close() throws IOException {
    this.flush();
    original.close();
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
