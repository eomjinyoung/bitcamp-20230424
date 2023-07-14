package bitcamp.util;

public class ManagedThread extends Thread {
  //  static int no = 1;

  //  int key;
  ResourcePool<ManagedThread> pool;
  Job job;

  public ManagedThread(ResourcePool<ManagedThread> pool) {
    this.pool = pool;
    //    key = no++;
  }

  public void setJob(Job job) {
    this.job = job;
    synchronized (this) {
      this.notify();
    }
  }

  @Override
  synchronized public void run() {
    while (true) {
      try {
        synchronized (this) {
          this.wait();
        }
        //        System.out.printf("%d 번 스레드 실행!\n", key);
        this.job.execute();
        pool.returnResource(this);

      } catch (Exception e) {
        System.err.println("스레드 실행 중 오류 발생!");
        e.printStackTrace();
      }
    }
  }
}
