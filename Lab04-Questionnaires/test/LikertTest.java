import org.junit.Before;
import org.junit.Test;

import questions.Likert;
import questions.TrueFalse;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the Likert Type of questions.
 */
public class LikertTest {

  private Likert likertQuestion;

  @Before
  public void setUp() throws Exception {
    likertQuestion = new Likert("Is inverting a binary tree a useful skill for a job?");
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidQuestion() {
    likertQuestion = new Likert("");
    assertEquals("", likertQuestion.getText());
  }

  @Test
  public void answer() {
    assertEquals("Correct", likertQuestion.answer("1"));
    assertEquals("Correct", likertQuestion.answer("2"));
    assertEquals("Correct", likertQuestion.answer("3"));
    assertEquals("Correct", likertQuestion.answer("4"));
    assertEquals("Correct", likertQuestion.answer("5"));
  }

  @Test
  public void invalidAnswer() {
    assertEquals("Incorrect", likertQuestion.answer("7"));
    assertEquals("Incorrect", likertQuestion.answer("0"));
    assertEquals("Incorrect", likertQuestion.answer(""));
  }

  @Test
  public void getText() {
    assertEquals("Is inverting a binary tree a useful skill for a job?",
            likertQuestion.getText());
  }

  @Test
  public void getQuestionType() {
    assertEquals("Likert", likertQuestion.getQuestionType());
  }

  @Test
  public void compareTo() {
    Likert question = new Likert("The onboarding process was easy");
    assertEquals(-11, likertQuestion.compareTo(question));
  }

  @Test
  public void compareToOtherType() {
    TrueFalse question = new TrueFalse("Today is Monday?", "False");
    assertEquals(1, likertQuestion.compareTo(question));
  }

}