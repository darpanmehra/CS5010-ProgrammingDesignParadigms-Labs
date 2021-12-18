import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.NoSuchElementException;

import lookandsay.LookAndSayIterator;
import lookandsay.RIterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests for the LookAndSayIterator class.
 */
public class LookAndSayIteratorTest {

  private RIterator<BigInteger> lookAndSayIterator;

  @Before
  public void setUp() throws Exception {
    BigInteger start = BigInteger.valueOf(1);
    BigInteger end = BigInteger.valueOf(100000000);
    lookAndSayIterator = new LookAndSayIterator(start, end);
  }

  @Test
  public void testInvalidStart() throws Exception {
    BigInteger start = BigInteger.valueOf(-1);
    BigInteger end = BigInteger.valueOf(100000000);
    try {
      lookAndSayIterator = new LookAndSayIterator(start, end);
      fail("Should have thrown an exception");
    } catch (IllegalArgumentException e) {
      assertEquals("Start must be greater than 0", e.getMessage());
    }
  }

  @Test
  public void testInvalidEnd() throws Exception {
    BigInteger start = BigInteger.valueOf(11);
    BigInteger end = BigInteger.valueOf(9);
    try {
      lookAndSayIterator = new LookAndSayIterator(start, end);
      fail("Should have thrown an exception");
    } catch (IllegalArgumentException e) {
      assertEquals("Start must be less than end", e.getMessage());
    }
  }

  @Test
  public void testZeroInStart() throws Exception {
    BigInteger start = BigInteger.valueOf(10);
    BigInteger end = BigInteger.valueOf(1111111);
    try {
      lookAndSayIterator = new LookAndSayIterator(start, end);
      fail("Should have thrown an exception");
    } catch (IllegalArgumentException e) {
      assertEquals("Start must not contain 0", e.getMessage());
    }
  }

  @Test
  public void testInvalidStartInSingleConstructor() throws Exception {
    BigInteger start = BigInteger.valueOf(-1);
    try {
      lookAndSayIterator = new LookAndSayIterator(start);
    } catch (IllegalArgumentException e) {
      assertEquals("Start must be greater than 0", e.getMessage());
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStartZeroInSingleConstructor() {
    BigInteger start = BigInteger.valueOf(10);
    lookAndSayIterator = new LookAndSayIterator(start);
  }

  @Test
  public void testDefaultConstructor() throws Exception {
    lookAndSayIterator = new LookAndSayIterator();
    assertEquals(BigInteger.valueOf(1), lookAndSayIterator.next());
    assertEquals(BigInteger.valueOf(11), lookAndSayIterator.next());
    assertEquals(BigInteger.valueOf(1), lookAndSayIterator.prev());
  }

  @Test
  public void prev() {
    assertEquals(BigInteger.valueOf(1), lookAndSayIterator.next());
    assertEquals(BigInteger.valueOf(11), lookAndSayIterator.next());
    assertEquals(BigInteger.valueOf(21), lookAndSayIterator.next());
    assertEquals(BigInteger.valueOf(1211), lookAndSayIterator.next());

    assertEquals(BigInteger.valueOf(21), lookAndSayIterator.prev());
    assertEquals(BigInteger.valueOf(11), lookAndSayIterator.prev());
    assertEquals(BigInteger.valueOf(1), lookAndSayIterator.prev());
  }

  @Test(expected = NoSuchElementException.class)
  public void testExceptionOnPrev() {
    assertEquals(BigInteger.valueOf(1), lookAndSayIterator.next());
    assertEquals(BigInteger.valueOf(11), lookAndSayIterator.next());
    assertEquals(BigInteger.valueOf(21), lookAndSayIterator.next());
    assertEquals(BigInteger.valueOf(1211), lookAndSayIterator.next());

    assertEquals(BigInteger.valueOf(21), lookAndSayIterator.prev());
    assertEquals(BigInteger.valueOf(11), lookAndSayIterator.prev());
    assertEquals(BigInteger.valueOf(1), lookAndSayIterator.prev());
    lookAndSayIterator.prev();
  }

  @Test
  public void hasPrevious() {
    assertEquals(BigInteger.valueOf(1), lookAndSayIterator.next());
    assertEquals(BigInteger.valueOf(11), lookAndSayIterator.next());
    assertEquals(BigInteger.valueOf(21), lookAndSayIterator.next());
    assertEquals(BigInteger.valueOf(1211), lookAndSayIterator.next());

    assertTrue(lookAndSayIterator.hasPrevious());
    assertEquals(BigInteger.valueOf(21), lookAndSayIterator.prev());

    assertTrue(lookAndSayIterator.hasPrevious());
    assertEquals(BigInteger.valueOf(11), lookAndSayIterator.prev());

    assertTrue(lookAndSayIterator.hasPrevious());
    assertEquals(BigInteger.valueOf(1), lookAndSayIterator.prev());

    assertFalse(lookAndSayIterator.hasPrevious());
  }

  @Test
  public void hasNext() {
    BigInteger start = BigInteger.valueOf(1);
    BigInteger end = BigInteger.valueOf(111221);
    lookAndSayIterator = new LookAndSayIterator(start, end);

    assertTrue(lookAndSayIterator.hasNext());
    assertEquals(BigInteger.valueOf(1), lookAndSayIterator.next());

    assertTrue(lookAndSayIterator.hasNext());
    assertEquals(BigInteger.valueOf(11), lookAndSayIterator.next());

    assertTrue(lookAndSayIterator.hasNext());
    assertEquals(BigInteger.valueOf(21), lookAndSayIterator.next());

    assertTrue(lookAndSayIterator.hasNext());
    assertEquals(BigInteger.valueOf(1211), lookAndSayIterator.next());

    assertFalse(lookAndSayIterator.hasNext());
  }

  @Test
  public void next() {
    assertEquals(BigInteger.valueOf(1), lookAndSayIterator.next());
    assertEquals(BigInteger.valueOf(11), lookAndSayIterator.next());
    assertEquals(BigInteger.valueOf(21), lookAndSayIterator.next());
    assertEquals(BigInteger.valueOf(1211), lookAndSayIterator.next());
  }

  @Test(expected = NoSuchElementException.class)
  public void testExceptionOnNext() {
    BigInteger start = BigInteger.valueOf(1);
    BigInteger end = BigInteger.valueOf(1211);
    lookAndSayIterator = new LookAndSayIterator(start, end);

    assertEquals(BigInteger.valueOf(1), lookAndSayIterator.next());
    assertEquals(BigInteger.valueOf(11), lookAndSayIterator.next());
    assertEquals(BigInteger.valueOf(21), lookAndSayIterator.next());
    assertEquals(BigInteger.valueOf(1211), lookAndSayIterator.next());
    lookAndSayIterator.next();
  }
}