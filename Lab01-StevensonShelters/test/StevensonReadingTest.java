import org.junit.Before;
import org.junit.Test;

import weather.StevensonReading;

import static org.junit.Assert.assertEquals;

/**
 * StevensonReadingTest defines several methods to test the methods defined in the StevensonReading
 * class.
 */
public class StevensonReadingTest {

  private StevensonReading singleReading;

  /**
   * Setup new instance of the Stevenson Reading.
   */
  @Before
  public void setup() {
    singleReading = new StevensonReading(23, 12, 3, 12);
  }

  /**
   * Test for toString method.
   */
  @Test
  public void setupToString() {
    singleReading = new StevensonReading(65, 40, 6, 11);
    assertEquals("Reading: T = 65, D = 40, v = 6, rain = 11", singleReading.toString());
  }

  /**
   * Test to check temperature value less than dew point value.
   */
  @Test(expected = IllegalArgumentException.class)
  public void getTemperatureDewPoint() {
    singleReading = new StevensonReading(20, 23, 23, 23);
  }

  /**
   * Test to check negative Wind Speed value.
   */
  @Test(expected = IllegalArgumentException.class)
  public void getWindSpeedNegative() {
    singleReading = new StevensonReading(24, 23, -9, 23);
  }

  /**
   * Test to check negative Total Rain value.
   */
  @Test(expected = IllegalArgumentException.class)
  public void getTotalRainNegative() {
    singleReading = new StevensonReading(20, 23, 23, -50);
  }

  /**
   * Test for temperature value.
   */
  @Test
  public void getTemperature() {
    assertEquals(23, singleReading.getTemperature());
  }

  /**
   * Test for Dew Point value.
   */
  @Test
  public void getDewPoint() {
    assertEquals(12, singleReading.getDewPoint());
  }

  /**
   * Test for Wind Speed value.
   */
  @Test
  public void getWindSpeed() {
    assertEquals(3, singleReading.getWindSpeed());
  }

  /**
   * Test for Total Rain value.
   */
  @Test
  public void getTotalRain() {
    assertEquals(12, singleReading.getTotalRain());
  }

  /**
   * Test for Relative Humidity value.
   */
  @Test
  public void getRelativeHumidity() {
    assertEquals(45, singleReading.getRelativeHumidity(), 0);
  }

  /**
   * Test for Heat Index value.
   */
  @Test
  public void getHeatIndex() {
    assertEquals(25, singleReading.getHeatIndex(), 0);
  }

  /**
   * Test for Wind Chill value.
   */
  @Test
  public void getWindChill() {
    assertEquals(76, singleReading.getWindChill(), 0);
  }

  /**
   * Test for equals method overriding.
   */
  @Test
  public void testEqualsOverriding() {
    StevensonReading monday;
    StevensonReading tuesday;
    monday = new StevensonReading(63, 42, 6, 10);
    tuesday = new StevensonReading(63, 42, 6, 10);
    assertEquals(monday, tuesday);
  }

  /**
   * Test for hashcode method overriding.
   */
  @Test
  public void testHashcodeOverriding() {
    StevensonReading monday;
    StevensonReading tuesday;
    monday = new StevensonReading(63, 42, 6, 10);
    tuesday = new StevensonReading(63, 42, 6, 10);
    assertEquals(monday.hashCode(), tuesday.hashCode());
  }
}