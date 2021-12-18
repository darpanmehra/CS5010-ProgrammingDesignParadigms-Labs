package weather;

/**
 * Stevenson Reading class has several methods to get and calculate different readings such as Dew
 * Point, Temperature, Heat Index, etc.
 */
public final class StevensonReading implements WeatherReading {


  private final double temperature;
  private final double dewPoint;
  private final double windSpeed;
  private final double totalRain;
  private final double relativeHumidity;

  /**
   * Set parameters and check the constraints.
   *
   * @param temperature for air temperature (T) in Celsius.
   * @param dewPoint    for Dew Point (D).
   * @param windSpeed   for wind speed (v) in miles per hour.
   * @param totalRain   for total rain (rain) in mm.
   * @throws IllegalArgumentException when temperature is less than Dew Point.
   * @throws IllegalArgumentException when wind speed is negative.
   * @throws IllegalArgumentException when total rain negative.
   */

  public StevensonReading(double temperature, double dewPoint, double windSpeed, double totalRain) {
    // Temperature - Dew Point check for calculating the Relative Humidity
    if (temperature < dewPoint) {
      throw new IllegalArgumentException("Temperature should always be greater than the Dew Point");
    }
    //Wind speed should be non-negative
    if (windSpeed < 0) {
      throw new IllegalArgumentException("Wind speed value should be non-negative");
    }
    //Total rain should be non-negative
    if (totalRain < 0) {
      throw new IllegalArgumentException("Total rain value should be non-negative");
    }

    this.temperature = temperature;
    this.dewPoint = dewPoint;
    this.windSpeed = windSpeed;
    this.totalRain = totalRain;
    this.relativeHumidity = calcRelativeHumidity();
  }

  /**
   * Get the temperature of this reading rounded to the nearest integer.
   *
   * @return the temperature
   */
  @Override
  public int getTemperature() {
    return (int) Math.round(temperature);
  }

  /**
   * Get the dew point for this reading rounded to the nearest integer.
   *
   * @return the dew point
   */
  @Override
  public int getDewPoint() {
    return (int) Math.round(dewPoint);
  }

  /**
   * Get the wind speed for this reading rounded to the nearest integer.
   *
   * @return the wind speed
   */
  @Override
  public int getWindSpeed() {
    return (int) Math.round(windSpeed);
  }

  /**
   * Get the total rain of this reading (in mm).
   *
   * @return the total rain.
   */
  @Override
  public int getTotalRain() {
    return (int) Math.round(totalRain);
  }

  private double calcRelativeHumidity() {
    final double relativeHumidity = (5 * (dewPoint - temperature) + 100);
    return relativeHumidity;
  }

  /**
   * Get the relative humidity (in %) of this weather reading rounded to the nearest integer.
   *
   * @return the relative humidity in %.
   */
  @Override
  public int getRelativeHumidity() {
    return (int) Math.round(relativeHumidity);
  }

  /**
   * Helper method for the calculating the heat index.
   *
   * @return the heat index.
   */
  private double calcHeatIndex() {
    // Initializing all co-efficients
    final double c1 = -8.78469475556;
    final double c2 = 1.61139411;
    final double c3 = 2.33854883889;
    final double c4 = -0.14611605;
    final double c5 = -0.012308094;
    final double c6 = -0.0164248277778;
    final double c7 = 0.002211732;
    final double c8 = 0.00072546;
    final double c9 = -0.000003582;

    final double heatIndex = (c1
            + c2 * temperature + c3 * relativeHumidity
            + c4 * temperature * relativeHumidity
            + c5 * Math.pow(temperature, 2) + c6 * Math.pow(relativeHumidity, 2)
            + c7 * relativeHumidity * Math.pow(temperature, 2)
            + c8 * temperature * Math.pow(relativeHumidity, 2)
            + c9 * Math.pow(temperature, 2) * Math.pow(relativeHumidity, 2)
    );

    return heatIndex;
  }

  /**
   * Get the heat index for this weather reading rounded to the nearest integer.
   *
   * @return get the heat index.
   */
  @Override
  public int getHeatIndex() {
    int heatIndex = (int) Math.round(calcHeatIndex());
    return heatIndex;
  }

  /**
   * Convert Temperature in degree Celsius to Fahrenheit.
   *
   * @return temperature in Fahrenheit.
   */
  private double convertCelsiusToFahrenheit() {
    double temperatureF = (temperature * (9.0 / 5.0)) + 32;
    return temperatureF;
  }

  /**
   * Helper function to calculate the Wind Chill.
   *
   * @param temperatureFahrenheit from the get WindChill method.
   * @return the wind chill value.
   */
  private double calcWindChill(double temperatureFahrenheit) {
    final double windChill = (35.74
            + (0.6215 * temperatureFahrenheit)
            - (35.75 * Math.pow(windSpeed, 0.16))
            + (0.4275 * temperatureFahrenheit * Math.pow(windSpeed, 0.16))
    );
    return windChill;
  }

  /**
   * Get the wind chill rounded to the nearest integer.
   *
   * @return the wind chill
   */
  @Override
  public int getWindChill() {
    double temperatureFahrenheit = convertCelsiusToFahrenheit();
    double windChill = calcWindChill(temperatureFahrenheit);
    return (int) Math.round(windChill);
  }

  /**
   * toString method to represent the data passed.
   *
   * @return String value of the data representation.
   */

  public String toString() {
    return String.format("Reading: T = %d, D = %d, v = %d, rain = %d",
            (int) Math.round(temperature),
            (int) Math.round(dewPoint),
            (int) Math.round(windSpeed),
            (int) Math.round(totalRain)
    );

  }

  /**
   * Overriding the equals to check for the value of the properties with the identity. Reference:
   * https://www.baeldung.com/java-equals-hashcode-contract.
   *
   * @param o when equals method is overridden
   * @return Boolean
   */
  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof StevensonReading)) {
      return false;
    }
    StevensonReading other = (StevensonReading) o;
    boolean temperatureEquals = (this.temperature == other.temperature);
    boolean dewPointEquals = (this.dewPoint == other.dewPoint);
    boolean windSpeedEquals = (this.windSpeed == other.windSpeed);
    boolean totalRainEquals = (this.totalRain == other.totalRain);
    return temperatureEquals && dewPointEquals && windSpeedEquals && totalRainEquals;
  }

  /**
   * Overriding Hashcode for the objects.
   *
   * @return Hash
   */
  @Override
  public int hashCode() {
    int hash = 7;
    hash = 31 * hash + (int) temperature;
    hash = 31 * hash + (int) dewPoint;
    hash = 31 * hash + (int) windSpeed;
    hash = 31 * hash + (int) totalRain;
    return hash;
  }
}