import org.junit.Before;
import org.junit.Test;

import questions.Likert;
import questions.TrueFalse;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the True/False type of questions.
 */
public class TrueFalseTest {

  private TrueFalse trueFalseQuestion;

  @Before
  public void setUp() throws Exception {
    trueFalseQuestion = new TrueFalse("Today is Monday?", "False");
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidQuestion() {
    trueFalseQuestion = new TrueFalse("", "False");
    assertEquals("", trueFalseQuestion.getText());
  }

  @Test
  public void answer() {
    assertEquals("Correct", trueFalseQuestion.answer("False"));
    assertEquals("Incorrect", trueFalseQuestion.answer("True"));
  }

  @Test
  public void getText() {
    assertEquals("Today is Monday?", trueFalseQuestion.getText());
  }

  @Test
  public void getQuestionType() {
    assertEquals("TrueFalse", trueFalseQuestion.getQuestionType());
  }

  @Test
  public void compareTo() {
    TrueFalse question = new TrueFalse("Today is tuesday?", "True");
    assertEquals(-39, trueFalseQuestion.compareTo(question));
  }

  @Test
  public void compareToOtherType() {
    Likert likertQuestion = new Likert("Is inverting a binary tree a useful skill for a job?");
    assertEquals(-1, trueFalseQuestion.compareTo(likertQuestion));
  }
}