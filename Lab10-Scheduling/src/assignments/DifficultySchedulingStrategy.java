package assignments;

import java.util.List;

/**
 * DifficultySchedulingStrategy is a class for scheduling strategies that schedules the assignments
 * based on their difficulty.
 */
public class DifficultySchedulingStrategy implements SchedulingStrategy {
  @Override
  public String schedule(List<Assignment> assignments) {
    if (assignments == null) {
      throw new IllegalArgumentException("Assignments cannot be null");
    }
    if (assignments.isEmpty()) {
      throw new IllegalArgumentException("Assignments cannot be empty");
    }

    assignments.sort((o1, o2) -> o2.getDifficulty() - o1.getDifficulty());

    return "difficulty";
  }
}
