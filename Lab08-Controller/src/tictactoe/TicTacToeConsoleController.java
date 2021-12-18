package tictactoe;

import java.io.IOException;
import java.util.Scanner;

/**
 * This starter files is for students to implement a console controller for the TicTacToe MVC
 * assignment.
 */
public class TicTacToeConsoleController implements TicTacToeController {

  private final Appendable out;
  private final Scanner scan;

  /**
   * Constructor for the controller.
   *
   * @param in  the source to read from
   * @param out the target to print to
   */
  public TicTacToeConsoleController(Readable in, Appendable out) {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    this.out = out;
    scan = new Scanner(in);
  }

  @Override
  public void playGame(TicTacToe m) {
    if (m == null) {
      throw new IllegalArgumentException("TicTacToe can't be null");
    }
    // int row = 0, col = 0;
    try {
      boolean isPlayerQuit = false;
      while (!m.isGameOver()) {
        out.append(m + "\n");
        out.append("Enter a move for " + m.getTurn() + ":\n");
        isPlayerQuit = playGameHelper(m);
        if (isPlayerQuit) {
          out.append("Game quit! Ending game state:\n");
          out.append(m + "\n");
          break;
        }
      }
      // Final Game state
      if (!isPlayerQuit) {
        out.append(m + "\n");
        out.append((m.getWinner() != null) ? "Game is over! " + m.getWinner()
                + " wins.\n" : "Game is over! Tie game.\n");
      }
    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed", ioe);
    }
  }

  private boolean playGameHelper(TicTacToe m) throws IOException {
    Integer row = getInputHelper();
    if (row == null) {
      return true;
    }
    Integer col = getInputHelper();
    if (col == null) {
      return true;
    }
    try {
      row = row - 1;
      col = col - 1;
      m.move(row, col);
    } catch (IllegalArgumentException iae) {
      out.append("Not a valid move: " + (row + 1) + ", " + (col + 1) + "\n");
      return playGameHelper(m);
    } catch (IllegalStateException ise) {
      throw new IllegalStateException("Game is over");
    }
    return false;
  }

  private Integer getInputHelper() throws IOException {
    String input;
    boolean moreInput = true;
    while (moreInput) {
      input = scan.next();
      if (input.equalsIgnoreCase("q")) {
        return null;
      }
      if (isNumeric(input)) {
        return Integer.parseInt(input);
      } else {
        out.append("Not a valid number: " + input + "\n");
        return getInputHelper();
      }
    }
    return null;
  }

  private boolean isNumeric(String input) {
    return input.matches("^(0|[1-9][0-9]*)$");
  }

}
