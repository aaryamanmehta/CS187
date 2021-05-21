package app;

import java.util.Iterator;

public class RecursiveList<T> implements ListInterface<T> {

  private int size;
  private Node<T> head = null;

  public RecursiveList() {
    this.head = null;
    this.size = 0;
  }

  public RecursiveList(Node<T> first) {
    this.head = first;
    this.size = 1;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public void insertFirst(T elem) {
    //TODO: Implement this method.
    Node<T> tempNode = new Node<T>(elem, head);
    tempNode.setNext(head);
    head = tempNode;
    size = size + 1;
  }

  @Override
  public void insertLast(T elem) {
    //TODO: Implement this method.
    Node<T> tempNode = new Node<T>(elem, null);
    if (head == null) {
      head = tempNode;
    }
    else {
      insertLastHelper(head, tempNode);
    }
    size = size + 1;
  }

  private void insertLastHelper(Node<T> currNode, Node<T> insertNode) {
    //TODO: Implement this method.
    if (currNode.getNext() == null) {
      currNode.setNext(insertNode);
      return;
    }
    insertLastHelper(currNode.getNext(), insertNode);
  }

  @Override
  public void insertAt(int index, T elem) {
    //TODO: Implement this method.
    if (elem == null) {
      throw new NullPointerException();
    }
    if (index > size || index < 0) {
      throw new IndexOutOfBoundsException();
    }
    Node<T> tempNode = new Node<T>(elem, head);
    switch (index) {
      case 0:
      tempNode.setNext(head);
      head = tempNode;
      break;
      default:
      insertHelper(head, index, tempNode);
      break;
    }
    size = size + 1;
  }

  private void insertHelper(Node<T> currNode, int index, Node<T> tempNode) {
    //TODO: Implement this method.
    switch (index) {
      case 1:
      tempNode.setNext(currNode.getNext());
      currNode.setNext(tempNode);
      break;
      default:
      insertHelper(currNode.getNext(), index - 1, tempNode);
    }
  }

  @Override
  public T removeFirst() {
    //TODO: Implement this method.
    T removedItem = null;
    if (isEmpty()) {
      throw new IllegalStateException();
    }
    if (head == null) {
      return null;
    }
    removedItem = head.getData();
    head = head.getNext();
    size = size - 1;
    return removedItem;
  }

  @Override
  public T removeLast() {
    //TODO: Implement this method.
    T removedItem = null;
    if (isEmpty()) {
      throw new IllegalStateException();
    }
    if (head == null) {
      return removedItem;
    }
    if (head.getNext() == null) {
      return removeFirst();
    }
    else {
      return removeLastHelper(head);
    }
  }

  private T removeLastHelper (Node<T> currNode) {
    if (currNode.getNext().getNext() == null) {
      T removedItem = currNode.getNext().getData();
      currNode.setNext(null);
      size = size - 1;
      return removedItem;
    }
    return removeLastHelper (currNode.getNext());
  }

  @Override
  public T removeAt(int i) {
    //TODO: Implement this method.
    T removedItem = null;
    if (i >= size || i < 0) {
      throw new IndexOutOfBoundsException();
    }
    switch (i) {
      case 0:
      removedItem = head.getData();
      head = head.getNext();
      break;
      default:
      removedItem = removeHelper(head, i);
      break;
    }
    size = size - 1;
    return removedItem;
  }

  private T removeHelper (Node<T> currNode, int i) {
    /* switch (i) {
      case 1:
      T removedItem = currNode.getNext().getData();
      currNode.setNext(currNode.getNext().getNext());
      return removedItem;
      break;
    }
    */
    if (i == 1) {
      T removedItem = currNode.getNext().getData();
      currNode.setNext(currNode.getNext().getNext());
      return removedItem;
    }
    return removeHelper(currNode.getNext(), i - 1);
  }

  @Override
  public T getFirst() {
    //TODO: Implement this method.
    T item = null;
    if (isEmpty()) {
      throw new IllegalStateException();
    }
    item = get(0);
    return item;
  }

  @Override
  public T getLast() {
    //TODO: Implement this method.
    T item = null;
    if (isEmpty()) {
      throw new IllegalStateException();
    }
    item = get(size - 1);
    return item;
  }

  @Override
  public T get(int i) {
    //TODO: Implement this method.
    T item = null;
    if (i >= size || i < 0) {
      throw new IndexOutOfBoundsException();
    }
    item = getHelper(head, i);
    return item;
  }

  private T getHelper(Node<T> currNode, int i) {
   /* switch (i) {
      case 0:
      return currNode.getData();
      break;
    }
    */
    if (i == 0) {
      return currNode.getData();
    }
    return getHelper(currNode.getNext(), i - 1);
  }

  @Override
  public void remove(T elem) {
    //TODO: Implement this method.
    int i = indexOf(elem);
      /* switch (i) {
        case -1:
        throw new ItemNotFoundException();
        break;
      }
      */
    if (i == -1) {
      throw new ItemNotFoundException();
    }
    removeAt(i);
  }

  @Override
  public int indexOf(T elem) {
    //TODO: Implement this method.
    int index = -1;
    if (elem == null) {
      throw new NullPointerException();
    }
    index = indexOfHelper(elem, head, index);
    return index;
  }

  private int indexOfHelper(T findElem, Node<T> findNode, int index){
    if (findNode.getNext() == null){
      return -1;
    }
    if (findElem == null){ 
      throw new NullPointerException();
    }
    if (findNode.getData().equals(findElem)){
      return index;
    }
    else {
      return indexOfHelper(findElem, findNode.getNext(), index + 1);
    }
  }

  @Override
  public boolean isEmpty() {
    //TODO: Implement this method.
    boolean empty = false;
    if (head == null) {
      empty = true;
    }
    return empty;
  }


  public Iterator<T> iterator() {
    //TODO: Implement this method.
    Iterator<T> iter = null;
    iter = new LinkedNodeIterator<T>(head);
    return iter;
  }
}
