package polynomial;

public class Term {

  private int power;
  private int coefficient;
  public Term next;

  public Term(int coefficient, int power) throws IllegalArgumentException {
    if (power < 0) {
      throw new IllegalArgumentException("Power should be positive");
    }
    this.coefficient = coefficient;
    this.power = power;
  }

  public int getCoefficient() {
    return this.coefficient;
  }

  public int getPower() {
    return this.power;
  }

}
