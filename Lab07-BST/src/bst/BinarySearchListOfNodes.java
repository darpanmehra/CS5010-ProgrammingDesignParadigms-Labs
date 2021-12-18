package bst;

/**
 * An interface for a binary search tree List of Nodes.
 *
 * @param <T> the type of element in the tree.
 */
public interface BinarySearchListOfNodes<T extends Comparable<T>> {

  BinarySearchListOfNodes<T> add(T data);

  /**
   * Returns the number of nodes in the tree.
   *
   * @return the number of nodes in the tree.
   */
  int size();

  /**
   * Returns the height of the tree.
   *
   * @return the height of the tree.
   */
  int height();

  /**
   * Returns if the tree contains the given data.
   *
   * @param data is the data to search for.
   * @return boolean if node is present or not  .
   */
  boolean present(T data);

  /**
   * Returns the node which is the minimum.
   *
   * @return the node which is minimum.
   */
  T minimum();

  /**
   * Returns the node which is the maximum.
   *
   * @return the node which is maximum.
   */
  T maximum();

  /**
   * Returns the pre-order traversal of tree.
   *
   * @return the pre-order traversal of tree.
   */
  String preOrder();

  /**
   * Returns the in-order traversal of tree.
   *
   * @return the in-order traversal of tree.
   */
  String inOrder();

  /**
   * Returns the post-order traversal of tree.
   *
   * @return the post-order traversal of tree.
   */
  String postOrder();

}
