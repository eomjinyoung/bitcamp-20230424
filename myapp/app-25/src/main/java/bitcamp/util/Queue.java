package bitcamp.util;

public class Queue<E> extends LinkedList<E> {

  public static void main(String[] args) {
    Queue<String> q = new Queue<>();
    q.offer("홍길동");
    q.offer("임꺽정");
    q.offer("유관순");
    q.offer("안중근");
    q.offer("윤봉길");

    Iterator<String> iterator = q.iterator();
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
    }
  }

  public void offer(E value) {
    this.add(value);
  }

  public E poll() {
    if (this.size() == 0) {
      return null;
    }
    return this.remove(0);
  }

  @Override
  public Iterator<E> iterator() {
    return new Iterator<>() {
      @Override
      public boolean hasNext() {
        return /*Queue.this.*/size() > 0;
      }
      @Override
      public E next() {
        return /*Queue.this.*/poll();
      }
    };
  }
}












