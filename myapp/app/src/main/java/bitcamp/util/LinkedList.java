package bitcamp.util;

public class LinkedList {

  Node head;
  Node tail;
  int size;

  public static void main(String[] args) {
    LinkedList list = new LinkedList();
    list.add(100);
    list.add(200);
    list.add(300);
    list.add(400);
    list.add(500);

    print(list);

    System.out.println(list.remove(300));
    System.out.println(list.remove(500));
    System.out.println(list.remove(100));
    System.out.println(list.remove(200));
    System.out.println(list.remove(400));
    System.out.println(list.remove(600));
    list.add(1000);
    list.add(2000);
    print(list);


    //    System.out.println(list.retrieve(100));
    //    System.out.println(list.retrieve(300));
    //    System.out.println(list.retrieve(500));
    //    System.out.println(list.retrieve(600));
  }

  static void print(LinkedList list) {
    Object[] arr = list.getList();
    for (Object obj : arr) {
      System.out.print(obj);
      System.out.print(", ");
    }
    System.out.println();
  }

  public void add(Object value) {
    Node node = new Node();
    node.value = value;

    if (head == null) {
      head = node;
    } else if (this.tail != null) {
      this.tail.next = node;
    }

    this.tail = node;
    this.size++;
  }

  public Object[] getList() {
    Object[] arr = new Object[this.size];

    Node cursor = this.head;
    for (int i = 0; i < this.size; i++) {
      arr[i] = cursor.value;
      cursor = cursor.next;
    }

    return arr;
  }

  public Object retrieve(Object value) {
    Node cursor = this.head;

    while (cursor != null) {
      if (cursor.value.equals(value)) {
        return cursor.value;
      }
      cursor = cursor.next;
    }

    return null;
  }

  public boolean remove(Object value) {
    Node prev = null;
    Node cursor = this.head;

    while (cursor != null) {
      if (cursor.value.equals(value)) {
        if (prev == null) {
          // 삭제할 노드가 시작 노드라면
          head = cursor.next;

          // 삭제할 노드가 끝 노드라면
          if (head == null) {
            tail = null;
          }

        } else if (cursor.next == null) {
          // 삭제할 노드가 끝 노드라면
          tail = prev;
          tail.next = null;

        } else {
          // 중간 노드라면, 다음 노드의 주소를 이전 노드에 저장한다.
          prev.next = cursor.next;
        }
        size--;

        // 가비지 객체를 초기화시켜서 가비지가 인스턴스를 가리키지 않도록 한다.
        cursor.next = null;
        cursor.value = null;

        return true;
      }

      // 현재 커서가 가리키는 노드를 prev에 보관한다.
      prev = cursor;

      // 현재 커서를 다음 노드로 이동한다.
      cursor = cursor.next;
    }

    return false;
  }
}
