import org.junit.Before;
import org.junit.Test;

import document.BasicStringVisitor;
import document.Document;
import document.HtmlStringVisitor;
import document.MarkdownStringVisitor;
import document.WordCountVisitor;
import document.elements.BasicText;
import document.elements.BoldText;
import document.elements.Heading;
import document.elements.HyperText;
import document.elements.ItalicText;
import document.elements.Paragraph;
import document.elements.TextElement;
import document.elements.TextElementVisitor;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the Document class.
 */
public class DocumentTest {

  Document document;
  TextElement basicText;
  TextElement boldText;
  TextElement heading;
  TextElement hyperText;
  TextElement italicText;
  Paragraph paragraph;

  TextElementVisitor<Integer> wordCountVisitor;
  TextElementVisitor<String> stringTextElementVisitor;
  TextElementVisitor<String> htmlVisitor;
  TextElementVisitor<String> markdownVisitor;

  @Before
  public void setUp() {
    document = new Document();
    basicText = new BasicText("Hello world");
    boldText = new BoldText("Bold world");
    heading = new Heading("Heading text", 1);
    hyperText = new HyperText("Apple", "http://apple.com");
    italicText = new ItalicText("This is italic text");

    paragraph = new Paragraph();
    document = new Document();

    wordCountVisitor = new WordCountVisitor();
    stringTextElementVisitor = new BasicStringVisitor();
    htmlVisitor = new HtmlStringVisitor();
    markdownVisitor = new MarkdownStringVisitor();

  }

  @Test
  public void testWordCountVisitor() {
    assertEquals(2, (int) basicText.accept(wordCountVisitor));
    assertEquals(2, (int) boldText.accept(wordCountVisitor));
    assertEquals(2, (int) heading.accept(wordCountVisitor));
    assertEquals(1, (int) hyperText.accept(wordCountVisitor));
    assertEquals(4, (int) italicText.accept(wordCountVisitor));


    TextElement heading2 = new Heading("Heading text visitor", 2);
    TextElement heading3 = new Heading("Heading text visitor three", 3);
    assertEquals(3, (int) heading2.accept(wordCountVisitor));
    assertEquals(4, (int) heading3.accept(wordCountVisitor));
  }

  @Test
  public void testWordCountDocumentVisitor() {

    assertEquals(0, document.countWords());

    document.add(basicText);
    assertEquals(2, document.countWords());

    document.add(boldText);
    assertEquals(4, document.countWords());

    TextElement heading2 = new Heading("Heading text visitor", 2);
    TextElement heading3 = new Heading("Heading text visitor three", 3);
    document.add(heading2);
    document.add(heading3);
    document.add(heading);
    assertEquals(13, document.countWords());

    document.add(hyperText);
    assertEquals(14, document.countWords());

    document.add(italicText);
    assertEquals(18, document.countWords());

    BasicText basicText2 = new BasicText("Hello world");
    paragraph.add(basicText2);
    document.add(paragraph);
    assertEquals(20, document.countWords());

  }

  @Test
  public void testBasicStringVisitor() {
    assertEquals("Hello world ", basicText.accept(stringTextElementVisitor));
    assertEquals("Bold world ", boldText.accept(stringTextElementVisitor));
    assertEquals("Heading text ", heading.accept(stringTextElementVisitor));
    assertEquals("Apple ", hyperText.accept(stringTextElementVisitor));
    assertEquals("This is italic text ", italicText.accept(stringTextElementVisitor));

    TextElement heading2 = new Heading("Heading text visitor", 2);
    TextElement heading3 = new Heading("Heading text visitor three", 3);
    assertEquals("Heading text visitor ", heading2.accept(stringTextElementVisitor));
    assertEquals("Heading text visitor three ", heading3.accept(stringTextElementVisitor));

    BasicText basicText2 = new BasicText("Hello world");
    BasicText basicText3 = new BasicText("Hello world two");
    paragraph.add(basicText2);
    paragraph.add(basicText3);
    assertEquals("Hello world Hello world two  ",
            paragraph.accept(stringTextElementVisitor));
  }

  @Test
  public void testBasicStringDocumentVisitor() {

    assertEquals("", document.toText(stringTextElementVisitor));

    document.add(basicText);
    assertEquals("Hello world", document.toText(stringTextElementVisitor));

    document.add(boldText);
    assertEquals("Hello world Bold world", document.toText(stringTextElementVisitor));

    document.add(heading);
    assertEquals("Hello world Bold world Heading text",
            document.toText(stringTextElementVisitor));

    document.add(hyperText);
    assertEquals("Hello world Bold world Heading text Apple",
            document.toText(stringTextElementVisitor));

    document.add(italicText);
    assertEquals("Hello world Bold world Heading text Apple This is italic text",
            document.toText(stringTextElementVisitor));

    BasicText basicText2 = new BasicText("Hello world");
    BasicText basicText3 = new BasicText("Hello world two");
    paragraph.add(basicText2);
    paragraph.add(basicText3);
    document.add(paragraph);
    assertEquals("Hello world Bold world Heading text Apple " +
                    "This is italic text Hello world Hello world two",
            document.toText(stringTextElementVisitor));

  }

  @Test
  public void testBasicStringDocumentVisitorWithDifferentHeadings() {
    TextElement heading2 = new Heading("Heading text visitor", 2);
    TextElement heading3 = new Heading("Heading text visitor three", 3);
    document.add(heading2);
    document.add(heading3);
    document.add(heading);
    assertEquals("Heading text visitor Heading text visitor three Heading text",
            document.toText(stringTextElementVisitor));
  }

  @Test
  public void testHtmlStringVisitor() {
    assertEquals("Hello world\n", basicText.accept(htmlVisitor));
    assertEquals("<b>Bold world</b>\n", boldText.accept(htmlVisitor));
    assertEquals("<h1>Heading text</h1>\n", heading.accept(htmlVisitor));
    assertEquals("<a href=\"http://apple.com\">Apple</a>\n",
            hyperText.accept(htmlVisitor));
    assertEquals("<i>This is italic text</i>\n", italicText.accept(htmlVisitor));

    TextElement heading2 = new Heading("Heading text visitor", 2);
    TextElement heading3 = new Heading("Heading text visitor three", 3);
    assertEquals("<h2>Heading text visitor</h2>\n", heading2.accept(htmlVisitor));
    assertEquals("<h3>Heading text visitor three</h3>\n", heading3.accept(htmlVisitor));

    BasicText basicText2 = new BasicText("Hello world");
    BasicText basicText3 = new BasicText("Hello world two");
    paragraph.add(basicText2);
    paragraph.add(basicText3);
    assertEquals("<p>Hello world\n" +
                    "Hello world two\n" +
                    "</p>\n",
            paragraph.accept(htmlVisitor));
  }

  @Test
  public void testHtmlStringDocumentVisitor() {

    assertEquals("", document.toText(htmlVisitor));

    document.add(basicText);
    assertEquals("Hello world", document.toText(htmlVisitor));

    document.add(boldText);
    assertEquals("Hello world\n<b>Bold world</b>", document.toText(htmlVisitor));

    document.add(heading);
    assertEquals("Hello world\n<b>Bold world</b>\n<h1>Heading text</h1>",
            document.toText(htmlVisitor));

    document.add(hyperText);
    assertEquals("Hello world\n<b>Bold world</b>\n<h1>Heading text</h1>\n" +
                    "<a href=\"http://apple.com\">Apple</a>",
            document.toText(htmlVisitor));

    document.add(italicText);
    assertEquals("Hello world\n<b>Bold world</b>\n<h1>Heading text</h1>\n" +
                    "<a href=\"http://apple.com\">Apple</a>\n<i>This is italic text</i>",
            document.toText(htmlVisitor));

    BasicText basicText2 = new BasicText("Hello world");
    BasicText basicText3 = new BasicText("Hello world two");
    paragraph.add(basicText2);
    paragraph.add(basicText3);
    document.add(paragraph);
    assertEquals("Hello world\n" +
                    "<b>Bold world</b>\n" +
                    "<h1>Heading text</h1>\n" +
                    "<a href=\"http://apple.com\">Apple</a>\n" +
                    "<i>This is italic text</i>\n" +
                    "<p>Hello world\n" +
                    "Hello world two\n" +
                    "</p>",
            document.toText(htmlVisitor));
  }

  @Test
  public void testHtmlStringDocumentVisitorWithDifferentHeadings() {
    TextElement heading2 = new Heading("Heading text visitor", 2);
    TextElement heading3 = new Heading("Heading text visitor three", 3);
    document.add(heading2);
    document.add(heading3);
    document.add(heading);
    assertEquals("<h2>Heading text visitor</h2>\n" +
                    "<h3>Heading text visitor three</h3>\n" +
                    "<h1>Heading text</h1>",
            document.toText(htmlVisitor));
  }

  @Test
  public void testMarkdownStringVisitor() {
    assertEquals("Hello world\n", basicText.accept(markdownVisitor));
    assertEquals("**Bold world**\n", boldText.accept(markdownVisitor));
    assertEquals("# Heading text\n", heading.accept(markdownVisitor));
    assertEquals("[Apple](http://apple.com)\n", hyperText.accept(markdownVisitor));
    assertEquals("*This is italic text*\n", italicText.accept(markdownVisitor));

    TextElement heading2 = new Heading("Heading text visitor", 2);
    TextElement heading3 = new Heading("Heading text visitor three", 3);
    assertEquals("## Heading text visitor\n", heading2.accept(markdownVisitor));
    assertEquals("### Heading text visitor three\n", heading3.accept(markdownVisitor));

    BasicText basicText2 = new BasicText("Hello world");
    BasicText basicText3 = new BasicText("Hello world two");
    paragraph.add(basicText2);
    paragraph.add(basicText3);
    assertEquals("\nHello world\nHello world two\n\n", paragraph.accept(markdownVisitor));
  }

  @Test
  public void testMarkdownStringDocumentVisitor() {

    assertEquals("", document.toText(markdownVisitor));

    document.add(basicText);
    assertEquals("Hello world", document.toText(markdownVisitor));

    document.add(boldText);
    assertEquals("Hello world\n**Bold world**", document.toText(markdownVisitor));

    document.add(heading);
    assertEquals("Hello world\n" +
                    "**Bold world**\n" +
                    "# Heading text",
            document.toText(markdownVisitor));

    document.add(hyperText);
    assertEquals("Hello world\n**Bold world**\n# Heading text\n[Apple](http://apple.com)",
            document.toText(markdownVisitor));

    document.add(italicText);
    assertEquals("Hello world\n**Bold world**\n# Heading text\n[Apple](http://apple.com)\n"
                    + "*This is italic text*",
            document.toText(markdownVisitor));

    BasicText basicText2 = new BasicText("Hello world");
    BasicText basicText3 = new BasicText("Hello world two");
    paragraph.add(basicText2);
    paragraph.add(basicText3);
    document.add(paragraph);
    assertEquals("Hello world\n" +
                    "**Bold world**\n" +
                    "# Heading text\n" +
                    "[Apple](http://apple.com)\n" +
                    "*This is italic text*\n" +
                    "\n" +
                    "Hello world\n" +
                    "Hello world two",
            document.toText(markdownVisitor));
  }

  @Test
  public void testMarkdownStringDocumentVisitorWithDifferentHeadings() {
    TextElement heading2 = new Heading("Heading text visitor", 2);
    TextElement heading3 = new Heading("Heading text visitor three", 3);
    document.add(heading2);
    document.add(heading3);
    document.add(heading);
    assertEquals("## Heading text visitor\n" +
                    "### Heading text visitor three\n" +
                    "# Heading text",
            document.toText(markdownVisitor));
  }
}