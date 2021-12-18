package document;

import document.elements.BasicText;
import document.elements.BoldText;
import document.elements.Heading;
import document.elements.HyperText;
import document.elements.ItalicText;
import document.elements.Paragraph;
import document.elements.TextElementVisitor;

/**
 * Generates a simple string representation of the document. In a simple string representation, the
 * text of each element is added one at a time using a space between each element.
 */
public class BasicStringVisitor implements TextElementVisitor<String> {

  @Override
  public String visitBasicTest(BasicText element) {
    return element.getText().trim() + " ";
  }

  @Override
  public String visitBoldText(BoldText element) {
    return element.getText().trim() + " ";
  }

  @Override
  public String visitHeadingText(Heading element) {
    return element.getText().trim() + " ";
  }

  @Override
  public String visitHyperText(HyperText element) {
    return element.getText().trim() + " ";
  }

  @Override
  public String visitItalicText(ItalicText element) {
    return element.getText().trim() + " ";
  }

  @Override
  public String visitParagraph(Paragraph element) {
    StringBuilder sb = new StringBuilder();
    for (BasicText e : element.getContent()) {
      sb.append(e.accept(this));
    }
    return sb + " ";
  }
}
