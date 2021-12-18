package transmission;

/**
 * Automatic Transmission class defines methods used by the automatic car and the gear the car is
 * currently on.
 */
public final class AutomaticTransmission implements Transmission {

  private double speed;
  private int gear;
  private final double thresholdGear1;
  private final double thresholdGear2;
  private final double thresholdGear3;
  private final double thresholdGear4;
  private final double thresholdGear5;

  /**
   * Constructor for AutomaticTransmission class.
   *
   * @param thresholdGear1 for threshold speed for gear 1
   * @param thresholdGear2 for threshold speed for gear 2
   * @param thresholdGear3 for threshold speed for gear 3
   * @param thresholdGear4 for threshold speed for gear 4
   * @param thresholdGear5 for threshold speed for gear 5
   */
  public AutomaticTransmission(double thresholdGear1, double thresholdGear2,
                               double thresholdGear3, double thresholdGear4,
                               double thresholdGear5) {
    if (thresholdGear1 >= thresholdGear2 || thresholdGear2 >= thresholdGear3
            || thresholdGear3 >= thresholdGear4 || thresholdGear4 >= thresholdGear5) {
      throw new IllegalArgumentException("Threshold speed for the gears are incorrect.");
    }
    if (thresholdGear1 <= 0 || thresholdGear2 <= 0
            || thresholdGear3 <= 0 || thresholdGear4 <= 0 || thresholdGear5 <= 0) {
      throw new IllegalArgumentException("Threshold speed for the gears should be greater than 0");
    }

    if (this.speed < 0) {
      throw new IllegalStateException("Speed cannot be a negative value.");
    }

    this.speed = 0;
    this.gear = 0;
    this.thresholdGear1 = thresholdGear1;
    this.thresholdGear2 = thresholdGear2;
    this.thresholdGear3 = thresholdGear3;
    this.thresholdGear4 = thresholdGear4;
    this.thresholdGear5 = thresholdGear5;
  }

  /**
   * Manage Speeds and update the gears accordingly.
   */
  private void manageTransmission(double speed) {

    this.speed = speed;

    if (this.speed < 0.0) {
      throw new IllegalStateException("Invalid Speed Value");
    } else if (this.speed == 0.0) {
      this.gear = 0;
    } else if (this.speed < thresholdGear1) {
      this.gear = 1;
    } else if (this.speed < thresholdGear2) {
      this.gear = 2;
    } else if (this.speed < thresholdGear3) {
      this.gear = 3;
    } else if (this.speed < thresholdGear4) {
      this.gear = 4;
    } else if (this.speed < thresholdGear5) {
      this.gear = 5;
    } else {
      this.gear = 6;
    }
  }

  /**
   * Increases the speed by 1 MPH updating the gear appropriately.
   */
  @Override
  public void increaseSpeed() {
    manageTransmission(speed + 1);
  }

  /**
   * Decreases the speed by 1 MPH updating the gear appropriately.
   *
   * @throws IllegalStateException if called would cause the speed to go below 0
   */
  @Override
  public void decreaseSpeed() throws IllegalStateException {
    manageTransmission(speed - 1);
  }

  /**
   * Gets the speed of this Transmission.
   *
   * @return the speed
   */
  @Override
  public int getSpeed() {
    return (int) Math.round(this.speed);
  }

  /**
   * Gets the gear of this Transmission.
   *
   * @return the gear
   */
  @Override
  public int getGear() {
    return this.gear;
  }

  /**
   * Displays the current state of the transmission.
   *
   * @return String eg: Transmission (speed = 45, gear = 3)
   */
  @Override
  public String toString() {
    final int speed = (int) Math.round(this.speed);
    final int gear = this.gear;
    return String.format("Transmission (speed = %s, gear = %s)",
            (int) Math.round(speed), gear);
  }

}
