package bst;

/**
 * ElementNode class of a binary search tree.
 *
 * @param <T> binary search tree element type.
 */
public class ElementNode<T extends Comparable<T>> implements BinarySearchListOfNodes<T> {

  private final T element;
  private BinarySearchListOfNodes<T> rightNode;
  private BinarySearchListOfNodes<T> leftNode;

  /**
   * Constructor for ElementNode.
   *
   * @param element element to be stored in the node.
   */
  public ElementNode(T element) {
    this.element = element;
    this.leftNode = new EmptyNode<>();
    this.rightNode = new EmptyNode<>();
  }

  @Override
  public BinarySearchListOfNodes<T> add(T data) {
    if (data.compareTo(this.element) < 0) {
      leftNode = leftNode.add(data);
    } else if (data.compareTo(this.element) > 0) {
      rightNode = rightNode.add(data);
    }
    return this;
  }

  @Override
  public int size() {
    int leftSize = leftNode.size() + 1;
    int rightSize = rightNode.size() + 1;
    return (leftSize + rightSize) - 1;
  }

  @Override
  public int height() {
    int leftHeight = leftNode.height() + 1;
    int rightHeight = rightNode.height() + 1;
    return Math.max(leftHeight, rightHeight);
  }

  @Override
  public boolean present(T data) {
    if (data.compareTo(this.element) == 0) {
      return true;
    } else if (data.compareTo(this.element) < 0) {
      return leftNode.present(data);
    } else if (data.compareTo(this.element) > 0) {
      return rightNode.present(data);
    }
    return false;
  }

  @Override
  public T minimum() {
    T minimum = leftNode.minimum();
    if (minimum != null) {
      return minimum;
    } else {
      return this.element;
    }
  }

  @Override
  public T maximum() {
    T maximum = rightNode.maximum();
    if (maximum != null) {
      return maximum;
    } else {
      return this.element;
    }
  }

  @Override
  public String preOrder() {
    //Root
    String result = this.element.toString();
    //Left
    String leftStr = this.leftNode.preOrder();
    if (leftStr.length() > 0) {
      result = result + " " + leftStr;
    }
    //Right
    String rightStr = this.rightNode.preOrder();
    if (rightStr.length() > 0) {
      return result + " " + rightStr;
    }
    return result;
  }

  @Override
  public String inOrder() {
    String result = "";

    //Left
    String leftStr = this.leftNode.inOrder();
    if (leftStr.length() > 0) {
      result = result + leftStr + " ";
    }
    //Root
    result += this.element.toString();
    //Right
    String rightStr = this.rightNode.inOrder();
    if (rightStr.length() > 0) {
      return result + " " + rightStr;
    }
    return result;
  }

  @Override
  public String postOrder() {
    String result = "";

    //Left
    String leftStr = this.leftNode.postOrder();
    if (leftStr.length() > 0) {
      result = result + leftStr + " ";
    }

    //Right
    String rightStr = this.rightNode.postOrder();
    if (rightStr.length() > 0) {
      result = result + rightStr + " ";
    }

    //Root
    result += this.element.toString();

    return result;
  }

  @Override
  public String toString() {
    return inOrder();
  }
}
