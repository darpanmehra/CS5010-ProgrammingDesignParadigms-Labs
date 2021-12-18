import org.junit.Before;
import org.junit.Test;

import questions.Likert;
import questions.MultipleSelect;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the Multiple Select type of Questions.
 */
public class MultipleSelectTest {

  private MultipleSelect multiSelectQuestion;

  @Before
  public void setUp() throws Exception {
    multiSelectQuestion = new MultipleSelect("What is the value of pi?", "2 4",
            "23/7", "22/7", "3.18..", "3.14..", "√-1");
  }

  @Test(expected = IllegalArgumentException.class)
  public void questionWithLessThan3Options() {
    multiSelectQuestion = new MultipleSelect("What is the value of pi?", "1",
            "22/7");
    assertEquals("Correct", multiSelectQuestion.answer("1"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void questionWithMoreThan8Options() {
    multiSelectQuestion = new MultipleSelect("What is the value of pi?", "1",
            "22/7", "28/7", "42/7", "22/1", "22/9", "22/8", "22/7", "23.5/7", "25/7");
    assertEquals("Correct", multiSelectQuestion.answer("1"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidQuestion() {
    multiSelectQuestion = new MultipleSelect("", "2", "1", "2", "3", "4");
    assertEquals("", multiSelectQuestion.getText());
  }

  @Test
  public void answer() {
    assertEquals("Correct", multiSelectQuestion.answer("2 4"));
    assertEquals("Incorrect", multiSelectQuestion.answer("2 5"));
  }

  @Test
  public void getText() {
    assertEquals("What is the value of pi?", multiSelectQuestion.getText());
  }

  @Test
  public void getQuestionType() {
    assertEquals("MultipleSelect", multiSelectQuestion.getQuestionType());
  }

  @Test
  public void compareTo() {
    MultipleSelect question = new MultipleSelect("Which of these are true", "",
            "Crow is a bird", "Monkey is an animal", "Shark is a bird",
            "Elephant is a bird");
    assertEquals(-8, multiSelectQuestion.compareTo(question));
  }

  @Test
  public void compareToWithOtherType() {
    Likert question = new Likert("The onboarding process was easy");
    assertEquals(-1, multiSelectQuestion.compareTo(question));
  }
}