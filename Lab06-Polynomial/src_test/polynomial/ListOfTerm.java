package polynomial;

/**
 * This interface is for the the List of terms Union ADT.
 */
public interface ListOfTerm {

  /**
   * This method inserts a new Term in the List of Terms.
   * @param term is the term to be added in the ADT
   * @return List of terms after adding the term.
   */
  ListOfTerm insert(Term term);

  /**
   * This method is to get the degree of the polynomial.
   *
   * @return int degree of the polynomial.
   */
  int getDegree();

  /**
   * This method is to get the coefficient of the terms for which the power argument is passed.
   *
   * @param power is the power of the term for which the coefficient is to be returned.
   * @return int coefficient of the term.
   */
  int getCoefficient(int power);

  /**
   * This method is to evalate the polynomial using the x value passed.
   *
   * @param x is the value using which the polynomial is evaluated.
   * @return double result of the polynomial when the x is passed.
   */
  double evaluate(double x);

  /**
   * This method is used to add one polynomial to the other polynomial.
   * @param other is the other polynomial to be added.
   * @return List of terms of the polynomial after addition.
   */
  ListOfTerm addPoly(ListOfTerm other);

  /**
   * This method is the helper method to add the polynomial if the term in it is an Element Node.
   * @param other is the element node of the other polynomial to be added.
   * @return List of terms of the polynomial after addition of the particular element node.
   */
  ListOfTerm addToElementNode(ElementNode other);

  /**
   * This method is the helper method to add the polynomial if the term in it is an empty node.
   * @param empty is the empty node of the of other polynomial to be added.
   * @return List of terms of the polynomial after addition of the particular empty node.
   */
  ListOfTerm addToEmptyNode(EmptyNode empty);


}
