package polynomial;

import java.util.Objects;

/**
 * Term represents each term of the polynomial. e.g. 4x^2 where coefficient is 4 and power is 2.
 */
public class Term implements Comparable<Term> {

  private int coefficient;
  private int power;

  /**
   * Constructor to construct the Term object.
   *
   * @param coefficient is the coefficient of the term.
   * @param power       is the power of the term.
   */
  Term(int coefficient, int power) {
    if (power < 0) {
      throw new IllegalArgumentException("Negative power not allowed.");
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

  @Override
  public String toString() {
    String result = "";
    if (this.coefficient > 0) {
      result += "+";
    }
    result += String.valueOf(coefficient);
    if (power != 0) {
      result += "x^";
      result += String.valueOf(power);
    }
    return result;
  }

  @Override
  public int compareTo(Term o) {
    return -1 * Integer.compare(this.power, o.power);
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Term) {
      Term that = (Term) o;
      return this.coefficient == that.coefficient && this.power == that.power;
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(coefficient, power);
  }

  protected Term addSameTerms(Term b) {
    if (this.compareTo(b) == 0) {
      return new Term(this.coefficient + b.coefficient, this.power);
    } else {
      throw new IllegalArgumentException("Terms have different powers.s");
    }
  }

}
