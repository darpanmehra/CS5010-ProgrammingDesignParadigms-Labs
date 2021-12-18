package document;

import document.elements.BasicText;
import document.elements.BoldText;
import document.elements.Heading;
import document.elements.HyperText;
import document.elements.ItalicText;
import document.elements.Paragraph;
import document.elements.TextElementVisitor;

/**
 * Generates an HTML version of the document.
 */
public class HtmlStringVisitor implements TextElementVisitor<String> {

  @Override
  public String visitBasicTest(BasicText element) {
    return element.getText().trim() + "\n";
  }

  @Override
  public String visitBoldText(BoldText element) {
    return "<b>" + element.getText().trim() + "</b>\n";
  }

  @Override
  public String visitHeadingText(Heading element) {
    return "<h" + element.getLevel() + ">" + element.getText().trim() + "</h" + element.getLevel()
            + ">\n";
  }

  @Override
  public String visitHyperText(HyperText element) {
    return "<a href=\"" + element.getUrl().trim() + "\">" + element.getText().trim().trim()
            + "</a>\n";
  }

  @Override
  public String visitItalicText(ItalicText element) {
    return "<i>" + element.getText().trim() + "</i>\n";
  }

  @Override
  public String visitParagraph(Paragraph element) {
    StringBuilder sb = new StringBuilder();
    sb.append("<p>");
    for (BasicText e : element.getContent()) {
      sb.append(e.accept(this));
    }
    sb.append("</p>\n");
    return sb.toString();
  }
}
