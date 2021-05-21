package structures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<T extends Comparable<T>> implements BSTInterface<T> {
  protected BSTNode<T> root;

  public boolean isEmpty() {
    return root == null;
  }

  public int size() {
    return subtreeSize(root);
  }

  protected int subtreeSize(BSTNode<T> node) {
    if (node == null) {
      return 0;
    } else {
      return 1 + subtreeSize(node.getLeft()) + subtreeSize(node.getRight());
    }
  }

  public boolean contains(T t) {
    // TODO: Implement the contains() method
    return get(t) != null;
  }

  public boolean remove(T t) {
    if (t == null) {
      throw new NullPointerException();
    }
    boolean result = contains(t);
    if (result) {
      root = removeFromSubtree(root, t);
    }
    return result;
  }

  protected BSTNode<T> removeFromSubtree(BSTNode<T> node, T t) {
    // node must not be null
    int result = t.compareTo(node.getData());
    if (result < 0) {
      node.setLeft(removeFromSubtree(node.getLeft(), t));
      return node;
    } else if (result > 0) {
      node.setRight(removeFromSubtree(node.getRight(), t));
      return node;
    } else { // result == 0
      if (node.getLeft() == null) {
        return node.getRight();
      } else if (node.getRight() == null) {
        return node.getLeft();
      } else { // neither child is null
        T predecessorValue = getHighestValue(node.getLeft());
        node.setLeft(removeRightmost(node.getLeft()));
        node.setData(predecessorValue);
        return node;
      }
    }
  }

  private T getHighestValue(BSTNode<T> node) {
    // node must not be null
    if (node.getRight() == null) {
      return node.getData();
    } else {
      return getHighestValue(node.getRight());
    }
  }

  private BSTNode<T> removeRightmost(BSTNode<T> node) {
    // node must not be null
    if (node.getRight() == null) {
      return node.getLeft();
    } else {
      node.setRight(removeRightmost(node.getRight()));
      return node;
    }
  }

  public T get(T t) {
    // TODO: Implement the get() method
    if (t == null) {
      throw new NullPointerException();
    }
    return getHelper(t, root);
  }

  private T getHelper(T t, BSTNode<T> node) {
    if (node == null) {
      return null;
    }
    else if (t.compareTo(node.getData()) < 0) {
      return getHelper(t, node.getLeft());
    }
    else if (t.compareTo(node.getData()) > 0) {
      return getHelper(t, node.getRight());
    }
    else {
      return node.getData();
    }
  }

  public void add(T t) {
    if (t == null) {
      throw new NullPointerException();
    }
    root = addToSubtree(root, new BSTNode<T>(t, null, null));
  }

  protected BSTNode<T> addToSubtree(BSTNode<T> node, BSTNode<T> toAdd) {
    if (node == null) {
      return toAdd;
    }
    int result = toAdd.getData().compareTo(node.getData());
    if (result <= 0) {
      node.setLeft(addToSubtree(node.getLeft(), toAdd));
    } else {
      node.setRight(addToSubtree(node.getRight(), toAdd));
    }
    return node;
  }

  @Override
  public T getMinimum() {
    // TODO: Implement the getMinimum() method
    if (isEmpty() == true) {
      return null;
    }
    return MinimumHelper(root);
  }

  public T MinimumHelper(BSTNode<T> node) {
    if (node.getLeft() == null) {
      return node.getData();
    }
    return MinimumHelper(node.getLeft());
  }

  @Override
  public T getMaximum() {
    // TODO: Implement the getMaximum() method
    if (isEmpty() == true) {
      return null;
    }
    return MaximumHelper(root);
  }

  public T MaximumHelper(BSTNode<T> node) {
    if (node.getRight() == null) {
      return node.getData();
    }
    return MaximumHelper(node.getRight());
  }

  @Override
	public int height() {
		return heightHelper(root);
	}
	
	private int heightHelper(BSTNode<T> node) {
		if (node == null) {
      return -1;
    }
		return 1 + Math.max(heightHelper(node.getLeft()), heightHelper(node.getRight()));
	}


  public Iterator<T> preorderIterator() {
    // TODO: Implement the preorderIterator() method
		Queue<T> queue = new LinkedList<T>();
		preorderTraverse(queue, root);
		return queue.iterator();
  }

  private void preorderTraverse(Queue<T> queue, BSTNode<T> node) {
    if (node != null) {
      queue.add(node.getData());
      preorderTraverse(queue, node.getLeft());
      preorderTraverse(queue, node.getRight());
    }
  }

  public Iterator<T> inorderIterator() {
    Queue<T> queue = new LinkedList<T>();
    inorderTraverse(queue, root);
    return queue.iterator();
  }


  private void inorderTraverse(Queue<T> queue, BSTNode<T> node) {
    if (node != null) {
      inorderTraverse(queue, node.getLeft());
      queue.add(node.getData());
      inorderTraverse(queue, node.getRight());
    }
  }

  public Iterator<T> postorderIterator() {
    // TODO: Implement the postorderIterator() method
		Queue<T> queue = new LinkedList<T>();
		postorderTraverse(queue, root);
		return queue.iterator();
  }

  private void postorderTraverse(Queue<T> queue, BSTNode<T> node) {
    if (node != null) {
      postorderTraverse(queue, node.getLeft());
      postorderTraverse(queue, node.getRight());
      queue.add(node.getData());
    }
  }

  @Override
  public boolean equals(BSTInterface<T> other) {
    // TODO: Implement the equals() method
		return equalsHelper(root, other.getRoot());
  }

  public boolean equalsHelper(BSTNode<T> node, BSTNode<T> other) {
    if (node == null & other == null) {
      return true;
    }
    if (node == null || other == null) {
      return false;
    }
    else {
      boolean check1 = equalsHelper(node.getLeft(), other.getLeft());
      boolean check2 = equalsHelper(node.getRight(), other.getRight());
      if (!node.getData().equals(other.getData())) {
        return false;
      } 
      return check1 && check2;
    }
  }

  @Override
  public boolean sameValues(BSTInterface<T> other) {
    // TODO: Implement the sameValues() method
    Iterator<T> nodeIterator = this.inorderIterator();
    Iterator<T> otherIterator = this.inorderIterator();
    while (nodeIterator.hasNext() == true && otherIterator.hasNext() == true) {
      if (!nodeIterator.next().equals(otherIterator.next())) {
        return false;
      }
    }
    return !nodeIterator.hasNext() && !otherIterator.hasNext();
  }

  @Override
  public boolean isBalanced() {
    // TODO: Implement the isBalanced() method
    return Math.pow(2, height()) <= size() && size() < Math.pow(2, height() + 1);
  }

  @Override
  @SuppressWarnings("unchecked")

	/* public void balance() {
    if (isEmpty() == true) {
			return;
		}
		Iterator<T> iterator = this.inorderIterator();
		ArrayList<T> ArrayList = new ArrayList<T>();
		while (iterator.hasNext()) {
			ArrayList.add(iterator.next());
		}
    root = null;
		balanceHelper(ArrayList);
	}
	
	private void balanceHelper(ArrayList<T> ArrayList) {
    if (ArrayList.isEmpty() == true) {
			return;
		}
    if (ArrayList.size() == 1) {
			this.add(ArrayList.get(0));
			return;
		}
		int middle = (ArrayList.size() - 1) / 2;
    this.add(ArrayList.get(middle));
		ArrayList.remove(middle);
		ArrayList<T> left = new ArrayList<T>(ArrayList.subList(0, (ArrayList.size() - 1) / 2));
		ArrayList<T> right = new ArrayList<T>(ArrayList.subList((ArrayList.size() - 1) / 2, ArrayList.size()));
		balanceHelper(left);
		balanceHelper(right);
	}
*/ // Some issues with ArrayList implementation according to autograder, most likely to do with middle implementation, not sure exactly what or how to fix it

public void balance() {
  Iterator<T> iterator = this.inorderIterator();
  T[] Array = (T[]) new Comparable[size()];
  for (int i = 0; iterator.hasNext(); i++) {
    Array[i] = iterator.next();
  }
  root = balanceHelper(Array, 0, Array.length - 1);
}

private BSTNode<T> balanceHelper(T[] Array, int lower, int upper) {
  if (lower > upper) {
    return null;
  } 
  int middle = (upper + lower) / 2;
  BSTNode<T> balancedNode = new BSTNode<T>(Array[middle], balanceHelper(Array, lower, middle - 1), balanceHelper(Array, middle + 1, upper));
  return balancedNode;
}

  @Override
  public BSTNode<T> getRoot() {
    // DO NOT MODIFY
    return root;
  }

  public static <T extends Comparable<T>> String toDotFormat(BSTNode<T> root) {
    // header
    int count = 0;
    String dot = "digraph G { \n";
    dot += "graph [ordering=\"out\"]; \n";
    // iterative traversal
    Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
    queue.add(root);
    BSTNode<T> cursor;
    while (!queue.isEmpty()) {
      cursor = queue.remove();
      if (cursor.getLeft() != null) {
        // add edge from cursor to left child
        dot += cursor.getData().toString() + " -> " + cursor.getLeft().getData().toString() + ";\n";
        queue.add(cursor.getLeft());
      } else {
        // add dummy node
        dot += "node" + count + " [shape=point];\n";
        dot += cursor.getData().toString() + " -> " + "node" + count + ";\n";
        count++;
      }
      if (cursor.getRight() != null) {
        // add edge from cursor to right child
        dot +=
            cursor.getData().toString() + " -> " + cursor.getRight().getData().toString() + ";\n";
        queue.add(cursor.getRight());
      } else {
        // add dummy node
        dot += "node" + count + " [shape=point];\n";
        dot += cursor.getData().toString() + " -> " + "node" + count + ";\n";
        count++;
      }
    }
    dot += "};";
    return dot;
  }

  public static void main(String[] args) {
    for (String r : new String[] {"a", "b", "c", "d", "e", "f", "g"}) {
      BSTInterface<String> tree = new BinarySearchTree<String>();
      for (String s : new String[] {"d", "b", "a", "c", "f", "e", "g"}) {
        tree.add(s);
      }
      Iterator<String> iterator = tree.inorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();
      iterator = tree.preorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();
      iterator = tree.postorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();

      System.out.println(tree.remove(r));

      iterator = tree.inorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();
    }

    BSTInterface<String> tree = new BinarySearchTree<String>();
    for (String r : new String[] {"a", "b", "c", "d", "e", "f", "g"}) {
      tree.add(r);
    }
    System.out.println(toDotFormat(tree.getRoot()));
    System.out.println(tree.size());
    System.out.println(tree.height());
    System.out.println(tree.isBalanced());
    tree.balance();
    System.out.println(tree.size());
    System.out.println(tree.height());
    System.out.println(tree.isBalanced());
  }
}
