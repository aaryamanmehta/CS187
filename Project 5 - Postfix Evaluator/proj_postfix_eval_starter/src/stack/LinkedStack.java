package stack;

/**
 * A {@link LinkedStack} is a stack that is implemented using a Linked List structure to allow for
 * unbounded size.
 *
 * @param <T> the elements stored in the stack
 */
public class LinkedStack<T> implements StackInterface<T> {

  public int size;
  public LLNode<T> topNode;

  /** {@inheritDoc} */
  @Override
  public T pop() throws StackUnderflowException {
    // TODO: Implement the stack operation for `pop`!
    if (isEmpty() == false) {
      T tempElement = topNode.getData();
      topNode = topNode.getNext();
      size = size - 1;
      return tempElement;
    }
    throw new StackUnderflowException("Cannot pop an empty stack");
  }

  /** {@inheritDoc} */
  @Override
  public T top() throws StackUnderflowException {
    // TODO: Implement the stack operation for `top`!
    if (isEmpty() == false) {
      return topNode.getData();
    }
    throw new StackUnderflowException("Cannot return top element in an empty stack");
  }

  /** {@inheritDoc} */
  @Override
  public boolean isEmpty() {
    // TODO: Implement the stack operation for `isEmpty`!
    return topNode == null;
  }

  /** {@inheritDoc} */
  @Override
  public int size() {
    // TODO: Implement the stack operation for `size`!
    return size;
  }

  /** {@inheritDoc} */
  @Override
  public void push(T elem) {
    // TODO: Implement the stack operation for `push`!
    LLNode<T> pushNode = new LLNode<T>(elem);
    pushNode.setNext(topNode);
    topNode = pushNode;
    size = size + 1;
  }
}
