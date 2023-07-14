package bitcamp.util;

public class ManagedThread extends Thread {
  static int no = 1;

  int key;
  ResourcePool<ManagedThread> pool;
  JobBox jobBox = new JobBox();

  public ManagedThread(ResourcePool<ManagedThread> pool) {
    this.pool = pool;
    key = no++;
  }

  public void setJob(Job job) {
    jobBox.setJob(job);
  }

  @Override
  synchronized public void run() {
    while (true) {
      try {
        synchronized (jobBox) {
          jobBox.wait();
        }
        System.out.printf("%d 번 스레드 실행!\n", key);
        jobBox.job.execute();
        pool.returnResource(this);

      } catch (Exception e) {
        System.err.println("스레드 실행 중 오류 발생!");
        e.printStackTrace();
      }
    }
  }
}
