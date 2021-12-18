package questions;

/**
 * True/False type of question can be answered in one of two ways: true or false.
 */
public class TrueFalse extends AbstractQuestion implements Question {

  private final String question;
  private final String correctAnswer;

  /**
   * Constructor for the True/ False type of question.
   *
   * @param question is the question that is to be asked.
   * @param answer   is the correct answer of the question.
   */
  public TrueFalse(String question, String answer) {
    if (question == null || question.isEmpty() || question.trim().isEmpty()) {
      throw new IllegalArgumentException("Question is either null, empty or black");
    }
    this.question = question;
    this.correctAnswer = answer;
    this.questionType = QuestionType.TrueFalse.toString();
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
    if (answer.equals(correctAnswer)) {
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
    } else {
      return -1;
    }
  }
}
