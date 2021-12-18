package document;

import document.elements.BasicText;
import document.elements.BoldText;
import document.elements.Heading;
import document.elements.HyperText;
import document.elements.ItalicText;
import document.elements.Paragraph;
import document.elements.TextElementVisitor;

/**
 * Generates a Markdown version of the document.
 */
public class MarkdownStringVisitor implements TextElementVisitor<String> {

  @Override
  public String visitBasicTest(BasicText element) {
    return element.getText().trim() + "\n";
  }

  @Override
  public String visitBoldText(BoldText element) {
    return "**" + element.getText().trim() + "**\n";
  }

  @Override
  public String visitHeadingText(Heading element) {
    switch (element.getLevel()) {
      case 1:
        return "# " + element.getText().trim() + "\n";
      case 2:
        return "## " + element.getText().trim() + "\n";
      case 3:
        return "### " + element.getText().trim() + "\n";
      default:
        return " " + element.getText().trim() + "\n";
    }
  }

  @Override
  public String visitHyperText(HyperText element) {
    return "[" + element.getText().trim() + "](" + element.getUrl().trim() + ")\n";
  }

  @Override
  public String visitItalicText(ItalicText element) {
    return "*" + element.getText().trim() + "*\n";
  }

  @Override
  public String visitParagraph(Paragraph element) {
    StringBuilder sb = new StringBuilder();
    sb.append("\n");
    for (BasicText text : element.getContent()) {
      sb.append(text.accept(this));
    }
    sb.append("\n");
    return sb.toString();
  }
}
