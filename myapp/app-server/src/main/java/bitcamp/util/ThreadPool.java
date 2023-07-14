package bitcamp.util;

import java.util.ArrayList;

public class ThreadPool implements ResourcePool<ManagedThread>{

  ArrayList<ManagedThread> list = new ArrayList<>();

  //  public ThreadPool() {
  //    for (int i = 0; i < 3; i++) {
  //      ManagedThread t = new ManagedThread(this);
  //      t.start();
  //      list.add(t);
  //    }
  //  }

  @Override
  public ManagedThread getResource() {
    ManagedThread t = null;

    if (list.size() == 0) {
      t = new ManagedThread(this);
      System.out.printf("새 스레드 생성: %d\n", t.key);
      t.start();

      try {
        Thread.sleep(100);
      } catch (Exception e) {}

      return t;
    }

    t = list.remove(0);
    System.out.printf("기존 스레드 리턴: %d\n", t.key);
    return t;
  }

  @Override
  public void returnResource(ManagedThread resource) {
    list.add(resource);
    System.out.printf("스레드 반납: %d\n", resource.key);
  }
}
