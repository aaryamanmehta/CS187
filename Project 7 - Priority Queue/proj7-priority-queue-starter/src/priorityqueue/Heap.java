package priorityqueue;

import java.util.Comparator;

public class Heap<T> implements PriorityQueueADT<T> {

  private int numElements;
  private T[] heap;
  private boolean isMaxHeap;
  private Comparator<T> comparator;
  private final static int INIT_SIZE = 5;

  /**
   * Constructor for the heap.
   * @param comparator comparator object to define a sorting order for the heap elements.
   * @param isMaxHeap Flag to set if the heap should be a max heap or a min heap.
   */
  public Heap(Comparator<T> comparator, boolean isMaxHeap) {
    //TODO: Implement this method.
    this.comparator = comparator;
    this.isMaxHeap = isMaxHeap;
    heap = (T[]) new Object[INIT_SIZE];
    numElements = 0;
  }

  /**
   * This results in the entry at the specified index "bubbling up" to a location
   * such that the property of the heap are maintained. This method should run in
   * O(log(size)) time.
   * Note: When enqueue is called, an entry is placed at the next available index in 
   * the array and then this method is called on that index. 
   *
   * @param index the index to bubble up
   */
  public void bubbleUp(int index) {
    //TODO: Implement this method.
    T fixNewElement = heap[numElements - 1];
    while (index > 0 && compare(heap[getParentOf(index)], fixNewElement) < 0) {
      heap[index] = heap[getParentOf(index)];
      index = getParentOf(index);
    }
    heap[index] = fixNewElement; 
  }

  /**
   * This method results in the entry at the specified index "bubbling down" to a
   * location such that the property of the heap are maintained. This method
   * should run in O(log(size)) time.
   * Note: When remove is called, if there are elements remaining in this
   *  the bottom most element of the heap is placed at
   * the 0th index and bubbleDown(0) is called.
   * 
   * @param index
   */
  public void bubbleDown(int index) {
    //TODO: Implement this method.
    T firstElement = heap[0];
    int i = 0;
    boolean notLeaf = true;
    while (notLeaf == true) {
      int childIndex = getLeftChildOf(i);
      if (childIndex <= index) {
        T childElement = heap[getLeftChildOf(i)];
        if (getRightChildOf(i) <= index && compare(heap[getRightChildOf(i)], childElement) > 0) {
          childIndex = getRightChildOf(i);
          childElement = heap[getRightChildOf(i)];
        }
        if (compare(childElement, firstElement) > 0) {
          heap[i] = childElement;
          i = childIndex;
        }
        else {
          notLeaf = false;
        }
      } 
      else {
        notLeaf = false;
      }
    }
    heap[i] = firstElement;
  }

  /**
   * Test for if the queue is empty.
   * @return true if queue is empty, false otherwise.
   */
  public boolean isEmpty() {
    //TODO: Implement this method.
    boolean isEmpty = false;
    if (numElements == 0) {
      isEmpty = true;
    }
    else {
      isEmpty = false;
    }
    return isEmpty;
  }

  /**
   * Number of data elements in the queue.
   * @return the size
   */
  public int size(){
    //TODO: Implement this method.
    int size = numElements;
    return size;
  }

  /**
   * Compare method to implement max/min heap behavior.  It calls the comparae method from the 
   * comparator object and multiply its output by 1 and -1 if max and min heap respectively.
   * @param element1 first element to be compared
   * @param element2 second element to be compared
   * @return positive int if {@code element1 > element2}, 0 if {@code element1 == element2}, negative int otherwise
   */
  public int compare(T element1 , T element2) {
    int result = 0;
    int compareSign =  -1;
    if (isMaxHeap) {
      compareSign = 1;
    }
    result = compareSign * comparator.compare(element1, element2);
    return result;
  }

  /**
   * Return the element with highest (or lowest if min heap) priority in the heap 
   * without removing the element.
   * @return T, the top element
   * @throws QueueUnderflowException if empty
   */
  public T peek() throws QueueUnderflowException {
    //TODO: Implement this method.
    T data = heap[0];
    if (isEmpty() == true) {
      throw new QueueUnderflowException();
    }
    return data;
  }  

  /**
   * Removes and returns the element with highest (or lowest if min heap) priority in the heap.
   * @return T, the top element
   * @throws QueueUnderflowException if empty
   */
  public T dequeue() throws QueueUnderflowException {
    //TODO: Implement this method.
    T data = heap[0];
    if (isEmpty() == true) {
      throw new QueueUnderflowException();
    }
    int lastIndex = numElements - 1;
    T last = heap[lastIndex];
    if (lastIndex > 0) {
      heap[0] = last;
      bubbleDown(lastIndex);
    }
    heap[lastIndex] = null;
    numElements--;
    return data;
  }

  /**
   * Enqueue the element.
   * @param the new element
   */
  public void enqueue(T newElement) {
    //TODO: Implement this method.
    expandCapacity();
    heap[numElements++] = newElement;
    bubbleUp(numElements - 1);
  }

  private int getLeftChildOf(int parentIndex) {
    if (parentIndex < 0) {
      throw new IndexOutOfBoundsException();
    }
    return (parentIndex * 2) + 1;
  }

  private int getRightChildOf(int parentIndex) {
    if (parentIndex < 0) {
      throw new IndexOutOfBoundsException();
    }
    return (parentIndex * 2) + 2;
  }

  private int getParentOf(int childIndex) {
    if (childIndex < 1) {
      throw new IndexOutOfBoundsException();
    }
    return (childIndex - 1) / 2;
  }

  private void expandCapacity() {
    if (numElements == heap.length) {
      T[] temp = (T[]) new Object[heap.length * 2];
      for (int i = 0; i < numElements; i++) {
        temp[i] = heap[i];
      }
      heap = temp;
    }
  }
}