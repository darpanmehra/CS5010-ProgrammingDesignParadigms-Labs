import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import questions.Likert;
import questions.MultipleChoice;
import questions.MultipleSelect;
import questions.Question;
import questions.TrueFalse;

import static org.junit.Assert.assertEquals;


/**
 * Test the question bank created.
 */
public class QuestionBankTest {

  private List<Question> questionBank = new ArrayList<>();

  @Test
  public void questionBankLikert() {
    Likert q1 = new Likert("What was your level of satisfaction with our product?");
    Likert q2 = new Likert("It was easy to navigate the website.");
    Likert q3 = new Likert("How satisfied were you with our new menu?");
    Likert q4 = new Likert("How would you rate your recent customer service call?");
    questionBank.add(q1);
    questionBank.add(q2);
    questionBank.add(q3);
    questionBank.add(q4);

    List expected = new ArrayList();
    expected.add(q3);
    expected.add(q4);
    expected.add(q2);
    expected.add(q1);

    Collections.sort(questionBank);
    assertEquals(expected, questionBank);
  }

  @Test
  public void questionBankTrueFalse() {
    TrueFalse q1 = new TrueFalse("Sun is white", "True");
    TrueFalse q2 = new TrueFalse("Moon has rings", "False");
    TrueFalse q3 = new TrueFalse("Nike sells phones", "False");
    questionBank.add(q1);
    questionBank.add(q2);
    questionBank.add(q3);

    List expected = new ArrayList();
    expected.add(q2);
    expected.add(q3);
    expected.add(q1);

    Collections.sort(questionBank);
    assertEquals(expected, questionBank);
  }

  @Test
  public void questionBankMultipleChoice() {
    MultipleChoice q1 = new MultipleChoice("Which of these is a prime number?", "5",
            "4", "6", "8", "12", "13");
    MultipleChoice q2 = new MultipleChoice("Which of these is even?", "1",
            "2", "3", "5");
    MultipleChoice q3 = new MultipleChoice("Which of these are odd", "3",
            "2", "4", "5");
    questionBank.add(q1);
    questionBank.add(q2);
    questionBank.add(q3);

    List expected = new ArrayList();
    expected.add(q3);
    expected.add(q1);
    expected.add(q2);

    Collections.sort(questionBank);
    assertEquals(expected, questionBank);
  }

  @Test
  public void questionBankMultipleSelect() {
    MultipleSelect q1 = new MultipleSelect("What is the value of pi?", "2 4",
            "23/7", "22/7", "3.18..", "3.14..", "√-1");
    MultipleSelect q2 = new MultipleSelect("Where is NEU", "1 3",
            "Boston", "Dubai", "Canada", "India", "Singapore");
    MultipleSelect q3 = new MultipleSelect("What is red in color", "2 4",
            "Moon", "Apple", "Light", "Traffic Signal", "Almonds");

    questionBank.add(q1);
    questionBank.add(q2);
    questionBank.add(q3);

    List expected = new ArrayList();
    expected.add(q3);
    expected.add(q1);
    expected.add(q2);

    Collections.sort(questionBank);
    assertEquals(expected, questionBank);
  }

  @Test
  public void questionBankTest() {
    Question q1 = new Likert("What was your level of satisfaction with our product?");
    Question q5 = new Likert("Anyone here?");
    Question q2 = new TrueFalse("Moon has rings", "False");
    Question q3 = new MultipleChoice("Which of these is a prime number?", "5",
            "4", "6", "8", "12", "13");
    Question q4 = new MultipleSelect("Where is NEU", "1 3",
            "Boston", "Dubai", "Canada", "India", "Singapore");
    questionBank.add(q1);
    questionBank.add(q2);
    questionBank.add(q3);
    questionBank.add(q4);
    questionBank.add(q5);

    List expected = new ArrayList();
    expected.add(q2);
    expected.add(q3);
    expected.add(q4);
    expected.add(q5);
    expected.add(q1);

    Collections.sort(questionBank);
    assertEquals(expected, questionBank);
  }
}
