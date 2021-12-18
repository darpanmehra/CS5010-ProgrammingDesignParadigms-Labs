import neu.lab0.helloworld.Book;
import neu.lab0.helloworld.Person;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * A JUnit test class for the Book class.
 */

public class BookTest {

  private Book manifest;
  private Person anne;
  private Person john;

  @Before
  public void setUp() {

    anne = new Person("Anne", "C", 1962);
    john = new Person("John", "D", 1987);
    manifest = new Book("The Manifest", anne, 35.56f);
  }

  /**
   * A JUnit test class to test the Titles Equals.
   */

  @Test
  public void testTitleEquals() {
    assertEquals("The Manifest", manifest.getTitle());

  }

  /**
   * A JUnit test class to test the Titles Not Equals.
   */
  @Test
  public void testTitleNotEquals() {
    assertNotEquals("Th manifest", manifest.getTitle());

  }

  /**
   * A JUnit test class to test if Price is Not Null.
   */
  @Test
  public void testPriceNotNull() {
    assertNotNull("Price cannot be Null", manifest.getPrice());
  }

  /**
   * A JUnit test class to test for Book Prices.
   */
  @Test
  public void testPrice() {
    assertEquals(35.56, manifest.getPrice(), 0.0001);
  }

  /**
   * A JUnit test class to test for Correct Author.
   */
  @Test
  public void testAuthor() {
    assertEquals(anne, manifest.getAuthor());
  }

  /**
   * A JUnit test class to test for Correct Author.
   */
  @Test
  public void testAuthorSecond() {
    assertNotEquals(john, manifest.getAuthor());
  }

}