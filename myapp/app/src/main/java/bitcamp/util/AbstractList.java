package bitcamp.util;

public abstract class AbstractList<E> implements List<E> {

  protected int size;

  @Override
  public int size() {
    return this.size;
  }

  protected boolean isValid(int index) {
    return index >= 0 && index < this.size;
  }

  @Override
  public Iterator<E> iterator() {

    // 방법1: top level class
    //    return new ListIterator<>(this);

    // 방법2: static nested class
    //    return new ListIterator2<>(this);
  }

  // static nested class
  static class ListIterator2<T> implements Iterator<T> {
    List<T> list;
    int cursor;

    public ListIterator2(List<T> list) {
      this.list = list;
    }

    @Override
    public boolean hasNext() {
      return cursor < list.size();
    }

    @Override
    public T next() {
      return list.get(cursor++);
    }
  }

  // non-static nested class = inner class
  class ListIterator3<T> implements Iterator<T> {

    // 바깥 클래스의 인스턴스 주소를 받아서 보관할 인스턴스 변수가 자동으로 추가된다.
    //    List<T> list;

    int cursor;

    // 바깥 클래스의 인스턴스 주소를 받는 생성자가 자동으로 추가된다.
    //    public ListIterator2(List<T> list) {
    //      this.list = list;
    //    }

    @Override
    public boolean hasNext() {
      return cursor < AbstractList.this.size();
    }

    @SuppressWarnings("unchecked")
    @Override
    public T next() {
      return (T) AbstractList.this.get(cursor++);
    }
  }
}
