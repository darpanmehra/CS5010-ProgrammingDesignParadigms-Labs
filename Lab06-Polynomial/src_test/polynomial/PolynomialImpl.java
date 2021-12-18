package polynomial;

import java.util.Scanner;

/**
 * This class represents the Polynomial implementation. It has several methods to perform operations
 * on the polynomial such as adding a term, evaluating, get degree and get coefficient of term.
 */
public class PolynomialImpl implements Polynomial {

  private ListOfTerm head;

  /**
   * Constructor for Polynomial implementation with no arguments.
   */
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
      throw new IllegalArgumentException("String is null");
    }
    this.head = new EmptyNode();
    Scanner sc = new Scanner(input);
    while (sc.hasNext()) {
      String[] eachTerm = sc.next().split("x\\^");

      int coefficient = Integer.parseInt(eachTerm[0]);
      int power;

      if (eachTerm.length == 1) {
        power = 0;
      } else {
        power = Integer.parseInt(eachTerm[1]);
      }
      head = head.insert(new Term (coefficient, power));
    }
  }

  //Done
  @Override
  public Polynomial add(Polynomial other) throws IllegalArgumentException {
    if (other == null) {
      throw new IllegalArgumentException("Invalid input");
    }
    if (!(other instanceof PolynomialImpl)) {
      throw new IllegalArgumentException();
    }
    PolynomialImpl o = (PolynomialImpl) other;
    return new PolynomialImpl(this.head.addPoly(o.head).toString());
  }

  //Done
  @Override
  public void addTerm(int coefficient, int power) throws IllegalArgumentException {
    if (power < 0) {
      throw new IllegalArgumentException("Power cannot be less than 0");
    }
    head = head.insert(new Term(coefficient, power) );
  }

  //Done
  @Override
  public boolean isSame(Polynomial poly) {
    if (!(poly instanceof PolynomialImpl)) {
      throw new IllegalArgumentException();
    }
    //If degree not same then they are anyway not same
    if (poly.getDegree() != head.getDegree()) {
      return false;
    }
    //In the order of polynomial, check if the coefficients are same
    for (int i = 0; i <= head.getDegree(); i++) {
      if (this.getCoefficient(i) != poly.getCoefficient(i)) {
        return false;
      }
    }
    return true;
  }

  //Done
  @Override
  public double evaluate(double x) {
    return head.evaluate(x);
  }

  //Done
  @Override
  public int getCoefficient(int power) {
    return head.getCoefficient(power);
  }

  //Done
  @Override
  public int getDegree() {
    return head.getDegree();
  }

  //Done
  @Override
  public String toString() {
    if (head.toString().charAt(0) == '+') {
      return head.toString().substring(1).trim();
    }
    return head.toString().trim();
  }

}

