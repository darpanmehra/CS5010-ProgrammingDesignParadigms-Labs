package polynomial;

import java.util.Scanner;

/**
 * This class represents the Polynomial implementation. It has several methods to perform operations
 * on the polynomial such as adding a term, evaluating, get degree and get coefficient of term.
 */
public class PolynomialImpl implements Polynomial {

  private ListOfTerms head;

  public PolynomialImpl() {
    head = new EmptyNode();
  }

  /**
   * Constructor for Polynomial implementation with String passed.
   *
   * @param input is the input string which is to be parsed into a polynomial.
   */
  public PolynomialImpl(String input) {
    if (input == null) {
      throw new IllegalArgumentException("Input cannot be null");
    }
    int coefficientValue = 0;
    int powerValue = 0;
    head = new EmptyNode();
    Scanner scan = new Scanner(input);
    scan.useDelimiter(" ");
    while (scan.hasNext()) {
      String[] eachTerm = scan.next().split("x\\^");
      coefficientValue = Integer.parseInt(eachTerm[0]);
      if (eachTerm.length == 1) {
        powerValue = 0;
      } else {
        powerValue = Integer.parseInt(eachTerm[1]);
      }
      head = head.addTerm(new Term(coefficientValue, powerValue));
    }
  }

  @Override
  public Polynomial add(Polynomial other) throws IllegalArgumentException {
    if (!(other instanceof PolynomialImpl)) {
      throw new IllegalArgumentException();
    }
    PolynomialImpl o = (PolynomialImpl) other;
    return new PolynomialImpl(this.head.addPolynomial(o.head).toString());
  }

  @Override
  public void addTerm(int coefficient, int power) throws IllegalArgumentException {
    if (power < 0) {
      throw new IllegalArgumentException("Power should be greater than or equal to 0");
    }
    head = head.addTerm(new Term(coefficient, power));
  }

  @Override
  public boolean isSame(Polynomial poly) {
    if (!(poly instanceof PolynomialImpl)) {
      return false;
    }
    if (this.getDegree() != poly.getDegree()) {
      return false;
    }
    for (int i = 0; i <= head.getDegree(); i++) {
      if (this.getCoefficient(i) != poly.getCoefficient(i)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public double evaluate(double x) {
    return head.evaluate(x);
  }

  @Override
  public int getCoefficient(int power) {
    return head.getCoefficient(power);
  }

  @Override
  public int getDegree() {
    return head.getDegree();
  }

  @Override
  public String toString() {
    if (head.toString().charAt(0) == '+') {
      return head.toString().substring(1).trim();
    }
    return head.toString().trim();
  }

}

