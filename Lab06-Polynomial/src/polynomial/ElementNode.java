package polynomial;

class ElementNode implements ListOfTerms {

  private final Term term;
  private final ListOfTerms rest;

  public ElementNode(Term term, ListOfTerms rest) {
    this.term = term;
    this.rest = rest;
  }

  @Override
  public ListOfTerms addTerm(Term other) {
    if (other.compareTo(this.term) < 0) {
      return new ElementNode(other, this);
    } else if (other.compareTo(this.term) == 0) {
      if (other.getCoefficient() + this.term.getCoefficient() == 0) {
        return this.rest;
      }
      return new ElementNode(this.term.add(other), this.rest);
    } else {
      return new ElementNode(this.term, this.rest.addTerm(other));
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
    return this.term.evaluateTerm(x) + this.rest.evaluate(x);
  }

  @Override
  public ListOfTerms addPolynomial(ListOfTerms head) {
    return head.addToElementNode(this);
  }

  @Override
  public ListOfTerms addToElementNode(ElementNode element) {
    if (element.term.compareTo(this.term) != 0) {
      return new ElementNode(this.term, this.rest.addPolynomial(element));
    } else {
      if (element.term.getCoefficient() + this.term.getCoefficient() == 0) {
        return this.rest.addPolynomial(element.rest);
      }
      return new ElementNode(this.term.add(element.term),
              this.rest.addPolynomial(element.rest));
    }
  }

  @Override
  public ListOfTerms addToEmptyNode(EmptyNode emptyNode) {
    return this;
  }

  @Override
  public Term getTerm() {
    return this.term;
  }

  @Override
  public boolean isSame(ListOfTerms head) {
    return head.isSameTermElementNode(this);
  }

  @Override
  public boolean isSameTermElementNode(ElementNode elementNode) {
    return this.term.equals(elementNode.term) && this.rest.isSame(elementNode.rest);
  }

  @Override
  public boolean isSameTermEmptyNode(EmptyNode emptyNode) {
    return ListOfTerms.super.isSameTermEmptyNode(emptyNode);
  }

  @Override
  public String toString() {
    String termString = term.toString();
    String res = rest.toString();
    if (res.equals("0")) {
      return termString;
    } else {
      return termString + " " + res;
    }
  }

}
