package polynomial;

public class PolynomialImpl implements Polynomial {

  public Term term;

  public PolynomialImpl() {
    this.term = null;
  }

  public PolynomialImpl(String input) {

    if (input.equals("")) {
      this.term = null;
      return;
    }

    String[] splits = input.split(" ");
    for (String current : splits) {
      if (current.contains((CharSequence) "^")) {
        int index = 0;
        for (int k = 0; k < current.length(); k++) {
          if (current.charAt(k) == 'x') {
            index = k;
            break;
          }
        }
        int coefficient = 1;
        //Determine whether the coefficient is 1.
        if (index > 0) {
          coefficient = Integer.parseInt(current.substring(0, index));
        }
        int power = Integer.parseInt(current.substring(index + 2));
        if (power < 0) {
          throw new IllegalArgumentException("Power cannot be negative.");
        }
        this.addTerm(coefficient, power);
      } else {
        this.addTerm(Integer.parseInt(current), 0); //Constants
      }
    }
  }


  //Done
  @Override
  public Polynomial add(Polynomial other) throws IllegalArgumentException {

    if (!(other instanceof  PolynomialImpl)){
      throw new IllegalArgumentException("Not of PolynomialImpl type");
    }

    PolynomialImpl result = new PolynomialImpl();
    PolynomialImpl first = this;
    PolynomialImpl otherTerm = (PolynomialImpl) other;

    Term currentTerm = null;
    if (first.getTerm() == null) {
      result = otherTerm;
      return result;
    }
    if (first.getTerm() != null) {
      currentTerm = first.getTerm();
    }
    else {
      result = otherTerm;
      return result;
    }
    while (currentTerm != null) {
      result.addTerm(currentTerm.getCoefficient(),currentTerm.getPower());
      currentTerm = currentTerm.next;
    }
    currentTerm = null;
    if (otherTerm == null) {
      result = this;
      return result;
    }
    if (otherTerm.getTerm() != null) {
      currentTerm = otherTerm.getTerm();
    }
    else {
      result = this;
      return result;
    }
    while (currentTerm != null) {
      result.addTerm(currentTerm.getCoefficient(),currentTerm.getPower());
      currentTerm = currentTerm.next;
    }
    return result;
  }

  //Done
  @Override
  public void addTerm(int coefficient, int power) throws IllegalArgumentException {

    if (power < 0){
      throw new IllegalArgumentException("Power cannot be less than 0");
    }

    if (this.term == null) {
      this.term = new Term(coefficient, power);
      return;
    }

    Term currentTerm = this.term;
    if (currentTerm.getPower() < power) {
      this.term = new Term(coefficient, power);
      this.term.next = currentTerm;
      return;
    }

    //Loop over to get the insert position
    Term last = null;
    while (currentTerm.next != null && currentTerm.next.getPower() > power) {
      last = currentTerm;
      currentTerm = currentTerm.next;
    }

    Term toProcess = currentTerm.next;
    if (currentTerm.getPower() == power) {
      if (last == null) {
        if (currentTerm.getCoefficient() + coefficient == 0) {
          this.term = currentTerm.next;
        } else {
          this.term = new Term(currentTerm.getCoefficient() + coefficient, power);
          this.term.next = toProcess;
        }
      }
    } else if (toProcess == null) { //Term to be added at the last
      currentTerm.next = new Term(coefficient, power);
    } else {

      if (toProcess.getPower() != power) {
        currentTerm.next = new Term(coefficient, power);
        currentTerm.next.next = toProcess;
      } else {
        if (toProcess.getCoefficient() + coefficient == 0) {
          currentTerm.next = toProcess.next;
        } else {
          Term temp = toProcess.next;
          currentTerm.next = new Term(toProcess.getCoefficient() + coefficient, power);
          currentTerm.next.next = temp;
        }

      }
    }
  }

  @Override
  public boolean isSame(Polynomial poly) {
    return false;
  }

  //Done
  @Override
  public double evaluate(double x) {
    double result = 0;
    Term current = this.term;
    while (current != null){
      int coefficient = current.getCoefficient();
      int power = current.getPower();

      if (power == 0){
        result += coefficient;
      }else{
        double variableValue = Math.pow(x, power);
        result += coefficient*variableValue;
      }
      current = current.next;
    }
    return result;
  }

  //Done
  @Override
  public int getCoefficient(int power) {
    Term current = this.term;
    while (current != null){
      if (current.getPower() == power){
        return  current.getCoefficient();
      }
      else{
       current = current.next;
      }
    }
    return 0;
  }

  //Done
  @Override
  public int getDegree() {
    return this.term.getPower();
  }

  /**
   * Get the string that represents the current polynomial.
   *
   * @return the related string of the polynomial.
   */
  @Override
  public String toString() {
    if (this == null) {
      return "0";
    }
    if (this.term == null) {
      return "0";
    }
    Term currentTerm = this.term;
    String result = "";

    //First Term
    result += String.valueOf(currentTerm.getCoefficient());
    if (currentTerm.getPower() != 0) {
      result += "x^";
      result += String.valueOf(currentTerm.getPower());
    }
    currentTerm = currentTerm.next;
    //All consecutive terms
    while (currentTerm != null) {
      //Add a space
      result += " ";

      //Next term co-efficient
      if (currentTerm.getCoefficient() > 0) {
        result += "+";
      }
      result += String.valueOf(currentTerm.getCoefficient());

      //Next term power
      if (currentTerm.getPower() != 0) {
        result += "x^";
        result += String.valueOf(currentTerm.getPower());
      }
      currentTerm = currentTerm.next;
    }
    return result;
  }

  private Term getTerm() {
    return this.term;
  }
}
