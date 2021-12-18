package polynomial;

import java.util.Objects;


/**
 * Representing each term which makes up a polynomial with its coefficient and power.
 */
public class Term implements Comparable<Term> {

  private int coefficient;
  private int power;

  /**
   * Initializing a term object with its attribute.
   *
   * @param coefficient coefficient for the term of polynomial.
   * @param power       power of the term.
   */
  public Term(int coefficient, int power) {
    if (power < 0) {
      throw new IllegalArgumentException("Power should be greater or equal to 0");
    }
    this.coefficient = coefficient;
    this.power = power;
  }

  protected int getCoefficient() {
    return this.coefficient;
  }

  protected int getPower() {
    return this.power;
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

  @Override
  public String toString() {
    if (power == 0) {
      return String.format("%+d", coefficient).trim();
    } else {
      return String.format("%+dx^%d", coefficient, power).trim();
    }
  }

  @Override
  public int compareTo(Term o) {
    return -1 * Integer.compare(this.power, o.power);
  }

  protected Term add(Term that) {

    if (this.compareTo(that) == 0) {

      return new Term(this.coefficient + that.coefficient, this.power);

    } else {
      throw new IllegalArgumentException("Powers are not same.");
    }
  }

  protected double evaluateTerm(double x) {
    return coefficient * Math.pow(x, power);
  }
}
