package document;

import java.util.ArrayList;
import java.util.List;

import document.elements.TextElement;
import document.elements.TextElementVisitor;

/**
 * A class that represents a document. It contains a list of the elements of the document in the
 * order that they appear in the document. This class is provided as a starting point for the
 * Visitor lab in CS 5010.
 */
public class Document {

  private List<TextElement> content;

  /**
   * Default constructor initializes the fields of the class.
   */
  public Document() {
    content = new ArrayList<>();
  }

  /**
   * Add an element to the document. Elements are added in order.
   *
   * @param e the element to add
   */
  public void add(TextElement e) {
    content.add(e);
  }

  /**
   * Count the number of words in the document.
   */
  public int countWords() {
    int count = 0;
    TextElementVisitor<Integer> wordCountVisitor = new WordCountVisitor();
    for (TextElement element : content) {
      count = count + element.accept(wordCountVisitor);
    }
    return count;
  }

  /**
   * Takes one of these "string visitors" as a parameter and returns a String as per the visitor.
   *
   * @param visitor the visitor to use
   * @param <R>     the return type of the visitor
   * @return the result of the visitor
   */
  public <R> String toText(TextElementVisitor<R> visitor) {
    StringBuilder sb = new StringBuilder();
    for (TextElement element : content) {
      sb.append(element.accept(visitor));
    }
    return sb.toString().trim();
  }

}
