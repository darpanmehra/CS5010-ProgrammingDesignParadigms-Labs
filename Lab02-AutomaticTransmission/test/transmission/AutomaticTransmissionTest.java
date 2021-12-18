package transmission;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests for testing the AutomaticTransmission class.
 */
public class AutomaticTransmissionTest {

  private AutomaticTransmission currentState;

  /**
   * Creating a new instance for testing.
   */
  @Before
  public void setUp() {
    currentState = new AutomaticTransmission(10, 25, 35, 60, 85);
  }

  /**
   * Test incorrect order of the threshold speeds for each gear (in ascending order).
   *
   * @throws IllegalArgumentException for incorrect order of the threshold speed.
   */
  @Test(expected = IllegalArgumentException.class)
  public void incorrectThresholdSpeeds() {
    currentState = new AutomaticTransmission(10, 9, 35, 60, 85);
  }

  /**
   * Test for negative Threshold value.
   *
   * @throws IllegalArgumentException for negative threshold values.
   */
  @Test(expected = IllegalArgumentException.class)
  public void negativeThresholdSpeeds() {
    currentState = new AutomaticTransmission(-10, 0, 35, 60, 85);
  }

  /**
   * Test for Speed Increase.
   */
  @Test
  public void increaseSpeed() {
    currentState.increaseSpeed();
    currentState.increaseSpeed();
    currentState.increaseSpeed();
    assertEquals("Transmission (speed = 3, gear = 1)", currentState.toString());
  }

  /**
   * Test for Speed decrease.
   */
  @Test
  public void decreaseSpeed() {
    //Increase Speeds
    currentState.increaseSpeed();
    currentState.increaseSpeed();
    currentState.increaseSpeed();
    currentState.increaseSpeed();
    currentState.increaseSpeed();
    currentState.increaseSpeed();
    currentState.increaseSpeed();
    currentState.increaseSpeed();
    //Decrease Speeds
    currentState.decreaseSpeed();
    currentState.decreaseSpeed();
    assertEquals("Transmission (speed = 6, gear = 1)", currentState.toString());
  }

  /**
   * Test for Gear Change.
   */
  @Test
  public void testChangeGear() {
    currentState = new AutomaticTransmission(3, 10, 20, 50, 80);
    currentState.increaseSpeed();
    currentState.increaseSpeed();
    currentState.increaseSpeed();
    currentState.increaseSpeed();
    currentState.increaseSpeed();
    currentState.increaseSpeed();
    assertEquals(2, currentState.getGear());
  }

  /**
   * Test for Speed decrease to a negative value.
   *
   * @throws IllegalStateException when speed less than 0
   */
  @Test(expected = IllegalStateException.class)
  public void testNegativeSpeed() {
    currentState.increaseSpeed();
    currentState.increaseSpeed();
    currentState.decreaseSpeed();
    currentState.decreaseSpeed();
    currentState.decreaseSpeed();
    assertEquals(-1, currentState.getSpeed());
    fail("This test should fail if speed is less than 0");
  }

  /**
   * Get speed of the car in MPH.
   */
  @Test
  public void getSpeed() {
    currentState.increaseSpeed();
    currentState.increaseSpeed();
    currentState.increaseSpeed();
    currentState.increaseSpeed();
    currentState.increaseSpeed();
    currentState.increaseSpeed();
    assertEquals(6, currentState.getSpeed());
  }

  /**
   * Get current gear status of the car.
   */
  @Test
  public void getGear() {
    currentState = new AutomaticTransmission(3, 15, 40, 60, 85);
    currentState.increaseSpeed();
    currentState.increaseSpeed();
    currentState.increaseSpeed();
    currentState.increaseSpeed();
    currentState.increaseSpeed();
    currentState.increaseSpeed();
    assertEquals(2, currentState.getGear());
  }

  /**
   * Test the data representation for the car speed and the current gear.
   */
  @Test
  public void testToString() {
    currentState.increaseSpeed();
    currentState.increaseSpeed();
    currentState.increaseSpeed();
    assertEquals("Transmission (speed = 3, gear = 1)", currentState.toString());
  }
}