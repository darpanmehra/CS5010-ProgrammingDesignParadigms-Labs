package assignments;

import java.util.Comparator;
import java.util.List;

/**
 * This class implements the DeadlineSchedulingStrategy interface. It schedules as per the deadline
 * of the assignment.
 */
public class DeadlineSchedulingStrategy implements SchedulingStrategy {
  @Override
  public String schedule(List<Assignment> assignments) {
    if (assignments == null) {
      throw new IllegalArgumentException("Assignments cannot be null");
    }
    if (assignments.isEmpty()) {
      throw new IllegalArgumentException("Assignments cannot be empty");
    }

    assignments.sort(Comparator.comparing(Assignment::getEndDate)
            .thenComparing(Assignment::getDescription));

    return "deadline";
  }
}
