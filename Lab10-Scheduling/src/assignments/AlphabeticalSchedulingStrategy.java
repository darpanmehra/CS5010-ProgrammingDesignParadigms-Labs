package assignments;

import java.util.Comparator;
import java.util.List;

/**
 * This class implements the AlphabeticalSchedulingStrategy. It is used to schedule the assignments
 * in the alphabetical order.
 */
public class AlphabeticalSchedulingStrategy implements SchedulingStrategy {

  @Override
  public String schedule(List<Assignment> assignments) {
    if (assignments == null) {
      throw new IllegalArgumentException("Assignments cannot be null");
    }
    if (assignments.isEmpty()) {
      throw new IllegalArgumentException("Assignments cannot be empty");
    }
    assignments.sort(Comparator.comparing(Assignment::getDescription));
    return "alphabetical";
  }

}
