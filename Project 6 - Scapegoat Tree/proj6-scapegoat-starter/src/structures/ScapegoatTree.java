package structures;

public class ScapegoatTree<T extends Comparable<T>> extends BinarySearchTree<T> {
  private int upperBound;


  @Override
  public void add(T t) {
    // TODO: Implement the add() method
    if (t == null) {
			throw new NullPointerException();
		}
    upperBound++;
    BSTNode<T> temp = new BSTNode<T>(t, null, null);
    root = addToSubtree(root, temp);
    double check = Math.log(upperBound) / Math.log(3.0/2.0);
    if (height() > check) {
      BSTNode<T> scapegoatChild = temp;
      BSTNode<T> scapegoatParent = temp.parent;
      while ((double)subtreeSize(scapegoatChild)/ subtreeSize(scapegoatParent) <= (double)2/3) {
				scapegoatParent = scapegoatParent.parent;
				scapegoatChild = scapegoatChild.parent;
			}
			ScapegoatTree<T> subtree = new ScapegoatTree<T>();
			subtree.root = scapegoatParent;;
      BSTNode<T> firstParent = scapegoatParent.parent;
      subtree.balance();
      if (firstParent.getLeft() == scapegoatParent) {
        firstParent.setLeft(subtree.root);
      }
			else firstParent.setRight(subtree.root);
    }
  }

  @Override
	public boolean remove(T element) {
    if (element == null) {
			throw new NullPointerException();
		}
		boolean result = contains(element);
		if (result) {
			root = removeFromSubtree(root, element);
		}
		if (upperBound > 2*size()) {
			balance();
			upperBound = size();
		}
		return result;
	}
	
  public static void main(String[] args) {
    BSTInterface<String> tree = new ScapegoatTree<String>();
    /*
    You can test your Scapegoat tree here.
    for (String r : new String[] {"0", "1", "2", "3", "4"}) {
      tree.add(r);
      System.out.println(toDotFormat(tree.getRoot()));
    }
    */
  }
}
