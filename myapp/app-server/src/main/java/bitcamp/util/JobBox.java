package bitcamp.util;

public class JobBox {
  Job job;

  synchronized public void setJob(Job job) {
    this.job = job;
    this.notify();
  }
}
