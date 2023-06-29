package bitcamp.io;

import java.io.IOException;
import java.io.InputStream;

public class DataInputStream extends InputStream {

  InputStream original;

  public DataInputStream(InputStream original) {
    this.original = original;
  }

  @Override
  public int read() throws IOException {
    return original.read();
  }

  @Override
  public void close() throws IOException {
    original.close();
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











