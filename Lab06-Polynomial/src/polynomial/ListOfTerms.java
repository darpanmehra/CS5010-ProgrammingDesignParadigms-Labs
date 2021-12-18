package polynomial;


/**
 * This interface is for the the List of terms Union ADT.
 */
public interface ListOfTerms {

  /**
   * This method inserts a new Term in the List of Terms.
   * @param term is the term to be added in the ADT
   * @return List of terms after adding the term.
   */
  ListOfTerms addTerm(Term term);

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
  ListOfTerms addPolynomial(ListOfTerms other);

  /**
   * This method is the helper method to add the polynomial if the term in it is an Element Node.
   * @param other is the element node of the other polynomial to be added.
   * @return List of terms of the polynomial after addition of the particular element node.
   */
  ListOfTerms addToElementNode(ElementNode other);

  /**
   * This method is the helper method to add the polynomial if the term in it is an empty node.
   * @param empty is the empty node of the of other polynomial to be added.
   * @return List of terms of the polynomial after addition of the particular empty node.
   */
  ListOfTerms addToEmptyNode(EmptyNode empty);

  /**
   * Get the term in the ADT.
   * @return Term of the ADT
   */
  Term getTerm();

  /**
   * Check if the passed argument is same as the current polynomial.
   * @param other is the passed polynomial.
   * @return boolean if the passed argument is same as the current polynomial.
   */
  boolean isSame(ListOfTerms other);

  /**
   * If the current polynomial element node is same as the passed element node.
   * @param element is the passed element of the polynomial to be checked.
   * @return boolean for similarity.
   */
  default boolean isSameTermElementNode(ElementNode element) {
    return false;
  }

  /**
   * If the current polynomial empty node is same as the passed element node.
   * @param empty is the passed empty node of the polynomial to be checked.
   * @return boolean for similarity.
   */
  default boolean isSameTermEmptyNode(EmptyNode empty) {
    return false;
  }
}
