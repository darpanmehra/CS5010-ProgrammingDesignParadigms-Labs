import org.junit.Before;
import org.junit.Test;

import questions.Likert;
import questions.MultipleChoice;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the Multiple Choice Type Questions.
 */
public class MultipleChoiceTest {
  private MultipleChoice mcq;

  @Before
  public void setUp() throws Exception {
    mcq = new MultipleChoice("Which of these is a prime number?", "5",
            "4", "6", "8", "12", "13");
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidQuestion() {
    mcq = new MultipleChoice("", "2", "1", "2", "3", "4");
    assertEquals("", mcq.getText());
  }

  @Test(expected = IllegalArgumentException.class)
  public void questionWithLessThan3Options() {
    mcq = new MultipleChoice("What is the value of pi?", "1",
            "22/7");
    assertEquals("Correct", mcq.answer("1"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void questionWithMoreThan8Options() {
    mcq = new MultipleChoice("What is the value of pi?", "1",
            "22/7", "28/7", "42/7", "22/1", "22/9", "22/8", "22/7", "23.5/7", "25/7");
    assertEquals("Correct", mcq.answer("1"));
  }

  @Test
  public void answer() {
    assertEquals("Correct", mcq.answer("5"));
  }

  @Test
  public void incorrectAnswer() {
    assertEquals("Incorrect", mcq.answer("1"));
  }

  @Test
  public void getText() {
    assertEquals("Which of these is a prime number?", mcq.getText());
  }

  @Test
  public void getQuestionType() {
    assertEquals("MultipleChoice", mcq.getQuestionType());
  }

  @Test
  public void compareTo() {
    MultipleChoice mcq2 = new MultipleChoice("2+2 equals?", "4", "1", "2",
            "3", "4");
    assertEquals(37, mcq.compareTo(mcq2));
  }

  @Test
  public void compareToOtherType() {
    Likert question = new Likert("The onboarding process was easy");
    assertEquals(-1, mcq.compareTo(question));
  }
}