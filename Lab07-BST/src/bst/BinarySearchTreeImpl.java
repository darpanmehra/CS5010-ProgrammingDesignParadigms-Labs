package bst;

/**
 * Binary Search Tree implementation.
 *
 * @param <T> binary search tree element type.
 */
public class BinarySearchTreeImpl<T extends Comparable<T>> implements BinarySearchTree<T> {

  private BinarySearchListOfNodes<T> root;

  public BinarySearchTreeImpl() {
    root = new EmptyNode<>();
  }

  @Override
  public void add(T data) {
    if (data == null) {
      throw new IllegalArgumentException("Data cannot be null");
    }
    this.root = root.add(data);
  }

  @Override
  public int size() {
    return root.size();
  }

  @Override
  public int height() {
    return root.height();
  }

  @Override
  public boolean present(T data) {
    if (data == null) {
      throw new IllegalArgumentException("Data cannot be null");
    }
    return root.present(data);
  }

  @Override
  public T minimum() {
    return root.minimum();
  }

  @Override
  public T maximum() {
    return root.maximum();
  }

  @Override
  public String preOrder() {
    return "[" + root.preOrder() + "]";
  }

  @Override
  public String inOrder() {
    return "[" + root.inOrder() + "]";
  }

  @Override
  public String postOrder() {
    return "[" + root.postOrder() + "]";
  }

  @Override
  public String toString() {
    return "[" + root.toString() + "]";
  }

}
