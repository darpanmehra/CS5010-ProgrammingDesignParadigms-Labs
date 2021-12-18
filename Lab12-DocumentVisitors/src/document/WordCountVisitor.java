package document;

import document.elements.BasicText;
import document.elements.BoldText;
import document.elements.Heading;
import document.elements.HyperText;
import document.elements.ItalicText;
import document.elements.Paragraph;
import document.elements.TextElementVisitor;

/**
 * Visitor that counts the number of words in a document.
 */
public class WordCountVisitor implements TextElementVisitor<Integer> {

  @Override
  public Integer visitBasicTest(BasicText element) {
    return element.getText().split(" ").length;
  }

  @Override
  public Integer visitBoldText(BoldText element) {
    return element.getText().split(" ").length;
  }

  @Override
  public Integer visitHeadingText(Heading element) {
    return element.getText().split(" ").length;
  }

  @Override
  public Integer visitHyperText(HyperText element) {
    return element.getText().split(" ").length;
  }

  @Override
  public Integer visitItalicText(ItalicText element) {
    return element.getText().split(" ").length;
  }

  @Override
  public Integer visitParagraph(Paragraph element) {
    return element.getText().split(" ").length;
  }
}