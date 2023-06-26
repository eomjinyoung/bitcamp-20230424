package bitcamp.util;

public interface List<E> {
  boolean add(E value);
  E get(int index);
  Object[] toArray();
  <T> T[] toArray(T[] arr);
  boolean remove(E value);
  E remove(int index);
  int size();
  Iterator<E> iterator();
}