package questions;

/**
 * Abstract class for Questions. It has a method to get the type of the question.
 */
public abstract class AbstractQuestion implements Question {

  String questionType;

  /**
   * Returns the question type.
   *
   * @return question type.
   */
  public String getQuestionType() {
    return this.questionType;
  }

}
