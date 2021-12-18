package polynomial;

class EmptyNode implements ListOfTerm {

  @Override
  public ListOfTerm insert(Term a) {
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
  public ListOfTerm addPoly(ListOfTerm other) {
    return other.addToEmptyNode(this);
  }

  @Override
  public ListOfTerm addToElementNode(ElementNode elementNode) {
    return elementNode;
  }

  @Override
  public ListOfTerm addToEmptyNode(EmptyNode emptyNode) {
    return this;
  }

  @Override
  public String toString() {
    return "0";
  }
}
