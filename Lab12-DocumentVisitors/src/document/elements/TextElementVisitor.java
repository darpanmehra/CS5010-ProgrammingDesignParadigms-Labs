package document.elements;

/**
 * Visitor interface for the TextElement class.
 * @param <R> the return type of the visit method
 */
public interface TextElementVisitor<R> {

  R visitBasicTest(BasicText element);

  R visitBoldText(BoldText element);

  R visitHeadingText(Heading element);

  R visitHyperText(HyperText element);

  R visitItalicText(ItalicText element);

  R visitParagraph(Paragraph element);

}
