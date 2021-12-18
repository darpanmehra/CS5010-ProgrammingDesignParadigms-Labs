package questions;

/**
 * Multiple choice type of questions offers several options, but there are multiple correct answers.
 * This type of questions can have only 3 to 8 options per questions.
 */
public class MultipleSelect extends AbstractQuestion implements Question {

  private final String question;
  private final String correctAnswer;

  /**
   * Constructor for the Multiple Select type of questions.
   *
   * @param question is the question to be asked.
   * @param answer   is the correct answer to the question.
   * @param options  is the Array of options available to choose from.
   */
  public MultipleSelect(String question, String answer, String... options) {
    if (question == null || question.isEmpty() || question.trim().isEmpty()) {
      throw new IllegalArgumentException("Question is either null, empty or black");
    }
    if (options.length < 3 || options.length > 8) {
      throw new IllegalArgumentException("Options should be between 3 to 8");
    }
    this.question = question;
    this.correctAnswer = answer;
    this.questionType = QuestionType.MultipleSelect.toString();
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
    } else if (questionType.equals(QuestionType.MultipleChoice.toString())
            || questionType.equals(QuestionType.TrueFalse.toString())) {
      return 1;
    } else if (questionType.equals(QuestionType.Likert.toString())) {
      return -1;
    }
    return 0;
  }
}
