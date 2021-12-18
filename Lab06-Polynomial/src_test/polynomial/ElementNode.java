package polynomial;

class ElementNode implements ListOfTerm {

  private final Term term;
  private final ListOfTerm rest;

  public ElementNode(Term term, ListOfTerm rest) {
    this.term = term;
    this.rest = rest;
  }

  @Override
  public ListOfTerm insert(Term term) {
    if (term.compareTo(this.term) < 0) {
      return new ElementNode(term, this);
    } else if (term.compareTo(this.term) == 0) {
      if (term.getCoefficient() + this.term.getCoefficient() == 0) {
        return this.rest;
      }
      return new ElementNode(this.term.addSameTerms(term), this.rest);
    } else {
      return new ElementNode(this.term, this.rest.insert(term));
    }
  }

  @Override
  public int getDegree() {
    return this.term.getPower();
  }

  @Override
  public int getCoefficient(int power) {
    if (this.term.getPower() == power) {
      return this.term.getCoefficient();
    } else {
      return this.rest.getCoefficient(power);
    }
  }

  @Override
  public double evaluate(double x) {
    double variableValue = 1;
    for (int i = 0; i < this.term.getPower(); i++) {
      variableValue *= x;
    }
    return (this.term.getCoefficient() * variableValue) + rest.evaluate(x);
  }

  @Override
  public ListOfTerm addPoly(ListOfTerm other) {
    return other.addToElementNode(this);
  }

  @Override
  public ListOfTerm addToElementNode(ElementNode other) {
    if (other.term.compareTo(this.term) == 0) {
      if (other.term.getCoefficient() + this.term.getCoefficient() == 0) {
        return this.rest.addPoly(other.rest);
      }
      return new ElementNode(this.term.addSameTerms(other.term),
              this.rest.addPoly(other.rest));
    } else {
      return new ElementNode(this.term, this.rest.addPoly(other));
    }
  }

  @Override
  public ListOfTerm addToEmptyNode(EmptyNode emptyNode) {
    return this;
  }

  @Override
  public String toString() {
    String thisTermToString = this.term.toString();
    String restToString = rest.toString();
    if (restToString.equals("0")) {
      return thisTermToString;
    }
    return thisTermToString + " " + restToString;
    //return String.format("%n %n", thisTermToString, restToString);
  }

}
