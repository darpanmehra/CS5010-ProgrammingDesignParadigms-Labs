import org.junit.Before;
import org.junit.Test;

import bst.BinarySearchTree;
import bst.BinarySearchTreeImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * A class for testing the BinarySearchTree class.
 */
public class BinarySearchTreeTest {

  private BinarySearchTree<Integer> tree;

  @Before
  public void setUp() throws Exception {
    this.tree = new BinarySearchTreeImpl<>();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIncorrectArguments() {
    this.tree.add(null);
  }

  @Test
  public void addDuplicate() {
    this.tree.add(1);
    this.tree.add(1);
    assertEquals(1, this.tree.size());
  }

  @Test
  public void addSmaller() {
    this.tree.add(4);
    this.tree.add(0);
    this.tree.add(1);
    this.tree.add(2);
    assertEquals("[0 1 2 4]", tree.inOrder());
  }

  @Test
  public void addLarger() {
    this.tree.add(0);
    this.tree.add(1);
    this.tree.add(2);
    this.tree.add(4);
    assertEquals("[0 1 2 4]", tree.inOrder());
  }

  @Test
  public void testEmptyTree() {
    assertEquals("[]", tree.inOrder());
    assertEquals("[]", tree.preOrder());
    assertEquals("[]", tree.postOrder());
  }

  @Test
  public void add_oneElement_sizeIsOne() {
    this.tree.add(1);
    this.tree.add(2);
    this.tree.add(3);
    assertEquals(3, this.tree.size());
  }

  @Test
  public void testSize() {
    this.tree.add(1);
    this.tree.add(20);
    this.tree.add(13);
    this.tree.add(134);
    this.tree.add(55);
    this.tree.add(535);
    assertEquals(6, this.tree.size());
  }

  @Test
  public void emptyTreeSize() {
    assertEquals(0, tree.size());
  }

  @Test
  public void height() {
    this.tree.add(1);
    assertEquals(1, this.tree.height());
  }

  @Test
  public void emptyTreeHeight() {
    assertEquals(0, tree.height());
  }

  @Test
  public void imbalancedTreeHeight() {
    tree.add(5);
    tree.add(10);
    tree.add(15);
    tree.add(20);
    tree.add(25);
    tree.add(24);
    tree.add(30);
    tree.add(35);
    tree.add(40);
    tree.add(45);
    tree.add(50);

    assertEquals("[5 10 15 20 25 24 30 35 40 45 50]", tree.preOrder());

    assertEquals(10, tree.height());
  }

  @Test
  public void present() {
    this.tree.add(1);
    this.tree.add(20);
    this.tree.add(13);
    this.tree.add(134);
    assertTrue(this.tree.present(1));
    assertTrue(this.tree.present(20));
    assertTrue(this.tree.present(13));
    assertFalse(this.tree.present(2));
    assertFalse(this.tree.present(3));
  }

  @Test
  public void minimum() {
    this.tree.add(1);
    this.tree.add(20);
    this.tree.add(13);
    this.tree.add(134);
    assertEquals(Integer.valueOf(1), tree.minimum());
  }

  @Test
  public void emptyTreeMinimum() {
    assertEquals(null, tree.minimum());
  }

  @Test
  public void maximum() {
    this.tree.add(1);
    this.tree.add(20);
    this.tree.add(13);
    this.tree.add(134);
    assertEquals(Integer.valueOf(134), tree.maximum());
  }

  @Test
  public void emptyTreeMaximum() {
    assertEquals(null, tree.maximum());
  }

  @Test
  public void preOrder() {
    tree.add(12);
    tree.add(9);
    tree.add(13);
    tree.add(1322);
    tree.add(94);
    tree.add(113);
    tree.add(914);
    tree.add(143);
    tree.add(1);
    tree.add(2);
    assertEquals("[12 9 1 2 13 1322 94 113 914 143]", this.tree.preOrder());
  }

  @Test
  public void emptyTreePreOrder() {
    assertEquals("[]", tree.preOrder());
  }

  @Test
  public void inOrder() {
    tree.add(12);
    tree.add(9);
    tree.add(13);
    tree.add(1322);
    tree.add(94);
    tree.add(113);
    tree.add(914);
    tree.add(143);
    tree.add(1);
    tree.add(2);
    assertEquals("[1 2 9 12 13 94 113 143 914 1322]", this.tree.inOrder());
  }

  @Test
  public void emptyTreeInOrder() {
    assertEquals("[]", tree.inOrder());
  }

  @Test
  public void postOrder() {
    tree.add(12);
    tree.add(9);
    tree.add(13);
    tree.add(1322);
    tree.add(94);
    tree.add(113);
    assertEquals("[9 113 94 1322 13 12]", this.tree.postOrder());
  }

  @Test
  public void emptyTreePostOrder() {
    assertEquals("[]", tree.postOrder());
  }

  @Test
  public void emptyToString() {
    assertEquals("[]", tree.toString());
  }
}