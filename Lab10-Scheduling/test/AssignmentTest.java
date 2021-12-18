import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import assignments.AlphabeticalSchedulingStrategy;
import assignments.AssignedSchedulingStrategy;
import assignments.Assignment;
import assignments.AssignmentList;
import assignments.DeadlineSchedulingStrategy;
import assignments.DifficultySchedulingStrategy;
import assignments.SchedulingStrategy;

import static org.junit.Assert.assertEquals;

/**
 * Class that tests the tasks.
 */
public class AssignmentTest {

  private AssignmentList list;
  private Assignment t1;
  private Assignment t2;
  private Assignment t3;
  private Assignment t4;

  @Before
  public void setUp() {
    t1 = new Assignment("Go to Paris");
    t1.setDeadline(3, 4, 2025);
    t2 = new Assignment("Attend graduation");
    t2.setDeadline(5, 4, 2023);
    t3 = new Assignment("Fifa world cup at Qatar");
    t3.setDeadline(6, 4, 2022);
    t4 = new Assignment("A random task to do when free");
    t4.setDeadline(1, 1, 2026);
  }

  /**
   * Testing constructor and toString().
   */
  @Test
  public void testConstructor() {
    LocalDate now = LocalDate.now();
    System.out.println(now);
    Assignment t1 = new Assignment("task 1");
    assertEquals("task 1, starting " + now + ", ending " + now, t1.toString());
    Assignment t2 = new Assignment("task 2");
    t2.setDeadline(3, 4, 2025);
    assertEquals("task 2, starting " + now + ", ending 2025-03-04", t2.toString());
  }

  @Test
  public void testSetDeadline() {
    Assignment t1 = new Assignment("task 1");
    t1.setDeadline(3, 4, 2025);
    assertEquals("task 1, starting " + LocalDate.now() + ", ending 2025-03-04", t1.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIAEonEmptyList() {
    list = new AssignmentList();
    SchedulingStrategy strategy = new DeadlineSchedulingStrategy();
    list.scheduleAssignments(strategy);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidAssignmentListAlphaSchedulingStrategy() {
    SchedulingStrategy strategy = new AlphabeticalSchedulingStrategy();
    strategy.schedule(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidAssignmentListAssignedSchedulingStrategy() {
    SchedulingStrategy strategy = new AssignedSchedulingStrategy();
    strategy.schedule(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidAssignmentListDeadlineSchedulingStrategy() {
    SchedulingStrategy strategy = new DeadlineSchedulingStrategy();
    strategy.schedule(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidAssignmentListDifficultySchedulingStrategy() {
    SchedulingStrategy strategy = new DifficultySchedulingStrategy();
    strategy.schedule(null);
  }

  // AssignedSchedulingStrategy Test
  @Test
  public void testAssignedSchedulingStrategy() {
    list = new AssignmentList();
    list.add(t1);
    list.add(t3);
    list.add(t2);
    SchedulingStrategy strategy = new AssignedSchedulingStrategy();
    list.scheduleAssignments(strategy);
    assertEquals("Ordered by assigned\n" +
                    "1 -- Go to Paris, starting 2021-11-19, ending 2025-03-04\n" +
                    "2 -- Attend graduation, starting 2021-11-19, ending 2023-05-04\n" +
                    "3 -- Fifa world cup at Qatar, starting 2021-11-19, ending 2022-06-04\n",
            list.toString());
  }

  @Test
  public void testAssignedAfterAlphabeticalSchedulingStrategy() {
    list = new AssignmentList();
    list.add(t1);
    list.add(t3);
    list.add(t2);
    SchedulingStrategy strategy = new AlphabeticalSchedulingStrategy();
    list.scheduleAssignments(strategy);
    assertEquals("Ordered by alphabetical\n" +
            "1 -- Attend graduation, starting 2021-11-19, ending 2023-05-04\n" +
            "2 -- Fifa world cup at Qatar, starting 2021-11-19, ending 2022-06-04\n" +
            "3 -- Go to Paris, starting 2021-11-19, ending 2025-03-04\n", list.toString());

    SchedulingStrategy strategy2 = new AssignedSchedulingStrategy();
    list.scheduleAssignments(strategy2);
    assertEquals("Ordered by assigned\n" +
                    "1 -- Go to Paris, starting 2021-11-19, ending 2025-03-04\n" +
                    "2 -- Attend graduation, starting 2021-11-19, ending 2023-05-04\n" +
                    "3 -- Fifa world cup at Qatar, starting 2021-11-19, ending 2022-06-04\n",
            list.toString());
  }

  // AlphabeticalSchedulingStrategy Test
  @Test
  public void testAlphabeticalSchedulingStrategy() {
    list = new AssignmentList();
    list.add(t1);
    list.add(t3);
    list.add(t2);
    SchedulingStrategy strategy = new AlphabeticalSchedulingStrategy();
    list.scheduleAssignments(strategy);
    assertEquals("Ordered by alphabetical\n" +
            "1 -- Attend graduation, starting 2021-11-19, ending 2023-05-04\n" +
            "2 -- Fifa world cup at Qatar, starting 2021-11-19, ending 2022-06-04\n" +
            "3 -- Go to Paris, starting 2021-11-19, ending 2025-03-04\n", list.toString());
  }

  @Test
  public void testAlphabeticalSchedulingStrategyAfterDeadline() {
    list = new AssignmentList();
    list.add(t1);
    list.add(t3);
    list.add(t2);
    SchedulingStrategy strategy = new DeadlineSchedulingStrategy();
    list.scheduleAssignments(strategy);
    assertEquals("Ordered by deadline\n" +
            "1 -- Fifa world cup at Qatar, starting 2021-11-19, ending 2022-06-04\n" +
            "2 -- Attend graduation, starting 2021-11-19, ending 2023-05-04\n" +
            "3 -- Go to Paris, starting 2021-11-19, ending 2025-03-04\n", list.toString());

    SchedulingStrategy strategy2 = new AlphabeticalSchedulingStrategy();
    list.scheduleAssignments(strategy2);
    assertEquals("Ordered by alphabetical\n" +
            "1 -- Attend graduation, starting 2021-11-19, ending 2023-05-04\n" +
            "2 -- Fifa world cup at Qatar, starting 2021-11-19, ending 2022-06-04\n" +
            "3 -- Go to Paris, starting 2021-11-19, ending 2025-03-04\n", list.toString());
  }

  // DeadlineSchedulingStrategy Test
  @Test
  public void testDeadlineSchedulingStrategy() {
    list = new AssignmentList();
    list.add(t1);
    list.add(t3);
    list.add(t2);
    SchedulingStrategy strategy = new DeadlineSchedulingStrategy();
    list.scheduleAssignments(strategy);
    assertEquals("Ordered by deadline\n" +
            "1 -- Fifa world cup at Qatar, starting 2021-11-19, ending 2022-06-04\n" +
            "2 -- Attend graduation, starting 2021-11-19, ending 2023-05-04\n" +
            "3 -- Go to Paris, starting 2021-11-19, ending 2025-03-04\n", list.toString());
  }

  @Test
  public void testDeadlineSchedulingStrategyAfterAlphabeticalScheduling() {
    list = new AssignmentList();
    list.add(t1);
    list.add(t3);
    list.add(t2);
    SchedulingStrategy strategy = new AlphabeticalSchedulingStrategy();
    list.scheduleAssignments(strategy);
    assertEquals("Ordered by alphabetical\n" +
            "1 -- Attend graduation, starting 2021-11-19, ending 2023-05-04\n" +
            "2 -- Fifa world cup at Qatar, starting 2021-11-19, ending 2022-06-04\n" +
            "3 -- Go to Paris, starting 2021-11-19, ending 2025-03-04\n", list.toString());

    // DeadlineSchedulingStrategy
    SchedulingStrategy strategy2 = new DeadlineSchedulingStrategy();
    list.scheduleAssignments(strategy2);
    assertEquals("Ordered by deadline\n" +
            "1 -- Fifa world cup at Qatar, starting 2021-11-19, ending 2022-06-04\n" +
            "2 -- Attend graduation, starting 2021-11-19, ending 2023-05-04\n" +
            "3 -- Go to Paris, starting 2021-11-19, ending 2025-03-04\n", list.toString());
  }

  // DifficultySchedulingStrategy Test
  @Test
  public void testDifficultySchedulingStrategy() {
    list = new AssignmentList();
    list.add(t1);
    list.add(t3);
    list.add(t2);
    SchedulingStrategy strategy = new DifficultySchedulingStrategy();
    list.scheduleAssignments(strategy);
    assertEquals("Ordered by difficulty\n" +
                    "1 -- Fifa world cup at Qatar, starting 2021-11-19, ending 2022-06-04\n" +
                    "2 -- Attend graduation, starting 2021-11-19, ending 2023-05-04\n" +
                    "3 -- Go to Paris, starting 2021-11-19, ending 2025-03-04\n",
            list.toString());
  }

  @Test
  public void testDifficultySchedulingStrategyAfterDeadlineStrategy() {
    list = new AssignmentList();
    list.add(t1);
    list.add(t3);
    list.add(t2);
    SchedulingStrategy strategy = new DeadlineSchedulingStrategy();
    list.scheduleAssignments(strategy);
    assertEquals("Ordered by deadline\n" +
            "1 -- Fifa world cup at Qatar, starting 2021-11-19, ending 2022-06-04\n" +
            "2 -- Attend graduation, starting 2021-11-19, ending 2023-05-04\n" +
            "3 -- Go to Paris, starting 2021-11-19, ending 2025-03-04\n", list.toString());

    // DifficultySchedulingStrategy
    SchedulingStrategy strategy2 = new DifficultySchedulingStrategy();
    list.scheduleAssignments(strategy2);
    assertEquals("Ordered by difficulty\n" +
                    "1 -- Fifa world cup at Qatar, starting 2021-11-19, ending 2022-06-04\n" +
                    "2 -- Attend graduation, starting 2021-11-19, ending 2023-05-04\n" +
                    "3 -- Go to Paris, starting 2021-11-19, ending 2025-03-04\n",
            list.toString());
  }

  @Test
  public void testAddTaskAfterScheduling() {
    list = new AssignmentList();
    list.add(t1);
    list.add(t3);
    list.add(t2);
    SchedulingStrategy strategy = new DeadlineSchedulingStrategy();
    list.scheduleAssignments(strategy);
    assertEquals("Ordered by deadline\n" +
            "1 -- Fifa world cup at Qatar, starting 2021-11-19, ending 2022-06-04\n" +
            "2 -- Attend graduation, starting 2021-11-19, ending 2023-05-04\n" +
            "3 -- Go to Paris, starting 2021-11-19, ending 2025-03-04\n", list.toString());

    list.add(t4);
    assertEquals("Ordered by deadline\n" +
                    "1 -- Fifa world cup at Qatar, starting 2021-11-19, ending 2022-06-04\n" +
                    "2 -- Attend graduation, starting 2021-11-19, ending 2023-05-04\n" +
                    "3 -- Go to Paris, starting 2021-11-19, ending 2025-03-04\n" +
                    "4 -- A random task to do when free, starting 2021-11-19, ending 2026-01-01\n",
            list.toString());
  }

}
