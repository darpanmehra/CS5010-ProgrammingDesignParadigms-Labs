package bst;

/**
 * EmptyNode is a class that represents an empty node in a binary search tree.
 *
 * @param <T> binary search tree element type.
 */
public class EmptyNode<T extends Comparable<T>> implements BinarySearchListOfNodes<T> {

  @Override
  public BinarySearchListOfNodes<T> add(T data) {
    return new ElementNode<>(data);
  }

  @Override
  public int size() {
    return 0;
  }

  @Override
  public int height() {
    return 0;
  }

  @Override
  public boolean present(T data) {
    return false;
  }

  @Override
  public T minimum() {
    return null;
  }

  @Override
  public T maximum() {
    return null;
  }

  @Override
  public String preOrder() {
    return "";
  }

  @Override
  public String inOrder() {
    return this.preOrder();
  }

  @Override
  public String postOrder() {
    return this.preOrder();
  }

  @Override
  public String toString() {
    return "";
  }

}
