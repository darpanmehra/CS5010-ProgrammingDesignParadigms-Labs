package document.elements;

/**
 * Representation for any of the text elements of a document.
 */
public interface TextElement {

  /**
   * Returns the text of the element.
   * 
   * @return the text
   */
  public String getText();

  /**
   * Making the elements of the document "visitable".
   * @param visitor the visitor
   * @param <R> the return type
   * @return the result of the visit
   */
  public <R> R accept(TextElementVisitor<R> visitor);
}
