import org.junit.Before;
import org.junit.Test;

import polynomial.Polynomial;
import polynomial.PolynomialImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This is a Junit test to test the various methods performed on the polynomial.
 */
public class PolynomialImplTest {

  private Polynomial poly;

  @Before
  public void setUp() throws Exception {
    this.poly = new PolynomialImpl();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInput() {
    Polynomial pObj = new PolynomialImpl(null);
  }

  @Test
  public void add() {
    this.poly.addTerm(5, 2);
    this.poly.addTerm(5, 3);

    Polynomial another = new PolynomialImpl();
    another.addTerm(3, 3);
    another.addTerm(3, 1);

    Polynomial result = this.poly.add(another);
    assertEquals("8x^3 +5x^2 +3x^1", result.toString());
  }

  @Test
  public void testAddNegativePolynomial() {
    poly.addTerm(5, 2);
    poly.addTerm(4, 1);
    poly.addTerm(-2, 0);

    Polynomial another = new PolynomialImpl();
    another.addTerm(-3, 2);
    another.addTerm(3, 1);
    another.addTerm(-5, 0);

    Polynomial result = this.poly.add(another);
    assertEquals("2x^2 +7x^1 -7", result.toString());
  }

  @Test
  public void addTerm() {
    poly.addTerm(5, 2);
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
    poly.addTerm(5, 2);
    poly.addTerm(4, 1);
    poly.addTerm(-2, 0);
    assertEquals("5x^2 +4x^1 -2", poly.toString());
    assertEquals(26, poly.evaluate(2), 0.01);
  }

  @Test
  public void getCoefficient() {
    poly.addTerm(10, 3);
    poly.addTerm(-7, 3);
    poly.addTerm(5, 2);
    poly.addTerm(4, 1);
    poly.addTerm(3, 1);
    poly.addTerm(-2, 0);
    assertEquals(3, poly.getCoefficient(3));
    assertEquals(5, poly.getCoefficient(2));
    assertEquals(7, poly.getCoefficient(1));
    assertEquals(-2, poly.getCoefficient(0));
  }

  @Test
  public void getDegree() {
    this.poly.addTerm(2, 3);
    this.poly.addTerm(4, 1);
    this.poly.addTerm(5, 4);
    this.poly.addTerm(-2, 3);
    this.poly.addTerm(-20, 4);
    this.poly.addTerm(13, 11);
    this.poly.addTerm(48, 28);
    assertEquals(28, this.poly.getDegree());
  }

  @Test
  public void testToString1() {
    poly.addTerm(5, 2);
    poly.addTerm(4, 1);
    poly.addTerm(-2, 0);
    assertEquals("5x^2 +4x^1 -2", poly.toString());
  }

  @Test
  public void testToString2() {
    poly.addTerm(4, 1);
    poly.addTerm(2, 5);
    poly.addTerm(-3, 2);
    poly.addTerm(-10, 0);
    assertEquals("2x^5 -3x^2 +4x^1 -10", poly.toString());
  }

  @Test
  public void testToString3() {
    poly.addTerm(-50, 3);
    poly.addTerm(1, 2);
    poly.addTerm(3, 0);
    assertEquals("-50x^3 +1x^2 +3", poly.toString());
  }

  @Test
  public void parseValidPolynomial() {
    Polynomial pObj = new PolynomialImpl("4x^3 +3x^1 -5");
    assertEquals("4x^3 +3x^1 -5", pObj.toString());
  }

  @Test
  public void parseJumbledPolynomial() {
    Polynomial pObj = new PolynomialImpl("-3x^4 -2x^5 -5 +11x^1");
    assertEquals("-2x^5 -3x^4 +11x^1 -5", pObj.toString());
  }

  @Test
  public void parseConstantOnly() {
    Polynomial pObj = new PolynomialImpl("102");
    assertEquals("102", pObj.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void parseNegativePowerPolynomial() {
    Polynomial pObj = new PolynomialImpl("-5x^-4 -3x^2 +2x^1 +12");
  }

  @Test(expected = IllegalArgumentException.class)
  public void parseInvalidFormat() {
    Polynomial pObj = new PolynomialImpl("-3x^3 +-2x^5 -3 +12x^1");
  }

  @Test
  public void testEmptyPolynomial() {
    assertEquals("0", poly.toString());
  }

  @Test
  public void testAddTermToExistingPolynomial() {
    Polynomial pObj = new PolynomialImpl("-3x^4 -2x^5 -5 +11x^1");
    pObj.addTerm(5, 5);
    assertEquals("3x^5 -3x^4 +11x^1 -5", pObj.toString());
  }

  @Test
  public void testToAddTermSumZero() {
    Polynomial pObj = new PolynomialImpl("-3x^4 -2x^5 -5 +11x^1");
    pObj.addTerm(5, 5);
    pObj.addTerm(3, 4);
    assertEquals("3x^5 +11x^1 -5", pObj.toString());
  }

  @Test
  public void testIsSameWithUniquePolynomials() {
    Polynomial pObj1 = new PolynomialImpl("-3x^4 -2x^5 -5 +11x^1");
    Polynomial pObj2 = new PolynomialImpl("102");
    assertFalse(pObj1.isSame(pObj2));
  }

  @Test
  public void testAddTermsToPolynomialMakeZero() {
    Polynomial pObj1 = new PolynomialImpl("-3x^4 -2x^5 -5 +11x^1");
    pObj1.addTerm(2, 5);
    pObj1.addTerm(3, 4);
    pObj1.addTerm(5, 0);
    pObj1.addTerm(-11, 1);

    assertEquals(0, pObj1.evaluate(5), 0.001);
  }

  @Test
  public void isSameAfterEvaluate() {
    Polynomial pObj1 = new PolynomialImpl("-3x^4 -2x^5 -5 +11x^1");
    double respObj1 = pObj1.evaluate(1);

    Polynomial pObj2 = new PolynomialImpl();
    pObj2.addTerm(1, 0);
    double respObj2 = pObj2.evaluate(1);
    assertTrue(respObj1 == respObj2);
  }

  @Test
  public void testAddDifferentPowers() {
    poly.addTerm(5, 4);
    poly.addTerm(52, 313);
    poly.addTerm(31, 4141);
    poly.addTerm(-5, 4211);
    poly.addTerm(1, 11);
    assertEquals("-5x^4211 +31x^4141 +52x^313 +1x^11 +5x^4", poly.toString());
  }
}