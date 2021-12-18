package questions;

/**
 * Likert Questions can be answered on a fixed 5-point scale (Strongly Agree, Agree, Neither Agree
 * nor Disagree, Disagree, Strongly Disagree).
 */
public class Likert extends AbstractQuestion implements Question {

  private String question;

  /**
   * Constructor for the Likert Type of Questions.
   *
   * @param question is the question to be asked.
   */
  public Likert(String question) {
    //Reference: https://stackabuse.com/java-check-if-string-is-null-empty-or-blank/
    if (question == null || question.isEmpty() || question.trim().isEmpty()) {
      throw new IllegalArgumentException("Question is either null, empty or black");
    }
    this.question = question;
    this.questionType = QuestionType.Likert.toString();
  }

  /**
   * Determines if the answer is correct for a given question. If the answer is correct, this method
   * returns "Correct"; and "Incorrect" otherwise.
   *
   * @param answer the answer given
   * @return "Correct" or "Incorrect"
   */
  @Override
  public String answer(String answer) {
    if (answer.equals("1") || answer.equals("2") || answer.equals("3")
            || answer.equals("4") || answer.equals("5")) {
      return CORRECT;
    } else {
      return INCORRECT;
    }
  }

  /**
   * Returns the text of the question.
   *
   * @return the text
   */
  @Override
  public String getText() {
    return this.question;
  }

  /**
   * CompareTo method for ordering of questions as per the type.
   *
   * @param o question to compare.
   */
  @Override
  public int compareTo(Question o) {
    AbstractQuestion that = (AbstractQuestion) o;
    String questionType = that.getQuestionType();

    if (questionType.equals(this.getQuestionType())) {
      return this.question.compareTo(that.getText());
    } else if (questionType.equals(QuestionType.MultipleSelect.toString())
            || questionType.equals(QuestionType.MultipleChoice.toString())
            || questionType.equals(QuestionType.TrueFalse.toString())) {
      return 1;
    }
    return 0;
  }
}
