package bitcamp.util;

public interface List {
  boolean add(Object value);
  Object get(int index);
  Object[] toArray();
  boolean remove(Object value);
  Object remove(int index);
  int size();
}
