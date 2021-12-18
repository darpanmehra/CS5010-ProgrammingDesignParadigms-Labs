package assignments;

import java.util.Comparator;
import java.util.List;

/**
 * This class represents a scheduling strategy that schedules the assignments in the order of their
 * number.
 */
public class AssignedSchedulingStrategy implements SchedulingStrategy {

  @Override
  public String schedule(List<Assignment> assignments) {
    if (assignments == null) {
      throw new IllegalArgumentException("Assignments cannot be null");
    }
    if (assignments.isEmpty()) {
      throw new IllegalArgumentException("Assignments cannot be empty");
    }

    //assignments.sort((o1, o2) -> o2.getNumber() - o1.getNumber());

    assignments.sort(Comparator.comparingInt(Assignment::getNumber));

    return "assigned";
  }

}
