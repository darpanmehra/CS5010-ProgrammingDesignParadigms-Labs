package assignments;

import java.util.List;

/**
 * This interface defines the methods that a scheduling strategy must implement.
 */
public interface SchedulingStrategy {

  /**
   * Interface for scheduling strategies.
   * @param assignments assignments to be scheduled.
   * @return the name of the strategy that was used.
   */
  String schedule(List<Assignment> assignments);
}
