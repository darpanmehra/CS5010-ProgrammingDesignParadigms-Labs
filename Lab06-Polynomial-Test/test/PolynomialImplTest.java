import org.junit.Before;
import org.junit.Test;

import polynomial.Polynomial;
import polynomial.PolynomialImpl;

import static org.junit.Assert.*;

public class PolynomialImplTest {

  private Polynomial poly;

  @Before
  public void setUp() throws Exception {
    this.poly = new PolynomialImpl();
  }


  @Test
  public void add() {
    this.poly.addTerm(5,2);
    this.poly.addTerm(5,3);

    Polynomial another = new PolynomialImpl();
    another.addTerm(3,3);
    another.addTerm(3,1);

    Polynomial result = this.poly.add(another);
    assertEquals("8x^3 +5x^2 +3x^1", result.toString());
  }

  @Test
  public void testAddNegative(){
    poly.addTerm(5,2);
    poly.addTerm(4,1);
    poly.addTerm(-2,0);

    Polynomial another = new PolynomialImpl();
    another.addTerm(-3,2);
    another.addTerm(3,1);
    another.addTerm(-5,0);

    Polynomial result = this.poly.add(another);
    assertEquals("2x^2 +7x^1 -7", result.toString());
  }

  @Test
  public void addTerm() {
    poly.addTerm(5,2);
    assertEquals(5, poly.getCoefficient(2));
    assertEquals(0, poly.getCoefficient(3));
  }

  @Test
  public void isSame() {
    poly.addTerm(5, 2);
    poly.addTerm(10, 3);
    poly.addTerm(-7, 3);
    poly.addTerm(1, 0);

    Polynomial other = new PolynomialImpl();
    other.addTerm(5, 2);
    other.addTerm(3, 3);
    other.addTerm(1, 0);

    assertTrue(poly.isSame(other));
  }

  @Test
  public void evaluate() {
    poly.addTerm(5,2);
    poly.addTerm(4,1);
    poly.addTerm(-2,0);
    assertEquals("5x^2 +4x^1 -2", poly.toString());
    assertEquals(26, poly.evaluate(2), 0.01);
  }

  @Test
  public void getCoefficient() {
    poly.addTerm(10,3);
    poly.addTerm(-7,3);
    poly.addTerm(5,2);
    poly.addTerm(4,1);
    poly.addTerm(3,1);
    poly.addTerm(-2,0);
    assertEquals(3, poly.getCoefficient(3));
    assertEquals(5, poly.getCoefficient(2));
    assertEquals(7, poly.getCoefficient(1));
    assertEquals(-2, poly.getCoefficient(0));
  }

  @Test
  public void getDegree() {
    this.poly.addTerm(2,3);
    this.poly.addTerm(4,1);
    this.poly.addTerm(5,4);
    this.poly.addTerm(-2,3);
    this.poly.addTerm(-20,4);
    this.poly.addTerm(13,11);
    this.poly.addTerm(48,28);
    assertEquals(28, this.poly.getDegree());
  }

  @Test
  public void testToString1() {
    poly.addTerm(5,2);
    poly.addTerm(4,1);
    poly.addTerm(-2,0);
    assertEquals("5x^2 +4x^1 -2", poly.toString());
  }

  @Test
  public void testToString2() {
    poly.addTerm(4,1);
    poly.addTerm(2,5);
    poly.addTerm(-3,2);
    poly.addTerm(-10,0);
    assertEquals("2x^5 -3x^2 +4x^1 -10", poly.toString());
  }

  @Test
  public void testToString3() {
    poly.addTerm(-50,3);
    poly.addTerm(1,2);
    poly.addTerm(3,0);
    assertEquals("-50x^3 +1x^2 +3", poly.toString());
  }

  @Test
  public void parseTest(){
    Polynomial pObj = new PolynomialImpl("4x^3 +3x^1 -5");
    assertEquals("4x^3 +3x^1 -5", pObj.toString());
  }

  @Test
  public void parseTest2(){
    Polynomial pObj = new PolynomialImpl("-3x^4 -2x^5 -5 +11x^1");
    assertEquals("-2x^5 -3x^4 +11x^1 -5", pObj.toString());
  }

  @Test
  public void parseTest3(){
    Polynomial pObj = new PolynomialImpl("102");
    assertEquals("102", pObj.toString());
  }

  @Test
  public void parseTest4(){
    Polynomial pObj = new PolynomialImpl("+3x^4 -2x^5 -5 -2x^4 +11x^1");
    assertEquals("-2x^5 +1x^4 +11x^1 -5", pObj.toString());
  }

  @Test
  public void testEmptyPolynomial(){
    assertEquals("0", poly.toString());
  }
}