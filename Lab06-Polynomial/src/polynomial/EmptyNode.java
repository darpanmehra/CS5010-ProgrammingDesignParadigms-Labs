package polynomial;

class EmptyNode implements ListOfTerms {

  @Override
  public ListOfTerms addTerm(Term a) {
    return new ElementNode(a, this);
  }

  @Override
  public int getDegree() {
    return 0;
  }

  @Override
  public int getCoefficient(int power) {
    return 0;
  }

  @Override
  public double evaluate(double x) {
    return 0;
  }

  @Override
  public ListOfTerms addPolynomial(ListOfTerms head) {
    return head.addToEmptyNode(this);
  }

  @Override
  public ListOfTerms addToElementNode(ElementNode element) {
    return element;
  }

  @Override
  public ListOfTerms addToEmptyNode(EmptyNode empty) {
    return this;
  }

  @Override
  public Term getTerm() {
    return this.getTerm();
  }

  @Override
  public boolean isSame(ListOfTerms head) {
    return this.isSameTermEmptyNode(this);
  }

  @Override
  public boolean isSameTermEmptyNode(EmptyNode empty) {
    return true;
  }

  @Override
  public String toString() {
    return "0";
  }
}
