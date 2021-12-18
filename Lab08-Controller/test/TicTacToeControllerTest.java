import org.junit.Test;

import java.io.StringReader;

import tictactoe.TicTacToe;
import tictactoe.TicTacToeConsoleController;
import tictactoe.TicTacToeController;
import tictactoe.TicTacToeModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test cases for the tic tac toe controller, using mocks for readable and appendable.
 */
public class TicTacToeControllerTest {

  // Providing this shell for you to write your
  // own test cases for the TicTacToe controller
  // You DO NOT NEED to implement tests for the provided model

  // TODO: Implement your own tests cases for the controller

  @Test
  public void playGameQuit() {
    TicTacToe m = new TicTacToeModel();
    StringReader in = new StringReader("1 0 q");
    Appendable out = new StringBuffer();
    TicTacToeController controller = new TicTacToeConsoleController(in, out);
    controller.playGame(m);
    assertEquals("   |   |  \n" +
            "-----------\n" +
            "   |   |  \n" +
            "-----------\n" +
            "   |   |  \n" +
            "Enter a move for X:\n" +
            "Not a valid move: 1, 0\n" +
            "Game quit! Ending game state:\n" +
            "   |   |  \n" +
            "-----------\n" +
            "   |   |  \n" +
            "-----------\n" +
            "   |   |  \n", out.toString());
  }


  @Test(expected = IllegalStateException.class)
  public void testFailingAppendable() {
    // Testing when something goes wrong with the Appendable
    // Here we are passing it a mock of an Appendable that always fails
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 2 1 1 3 3 1 2 1 3 2 3 2 1 3 1 3 2");
    Appendable gameLog = new FailingAppendable();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidModel() {

    StringReader input = new StringReader("2 2 1 1 3 3 1 2 1 3 2 3 2 1 3 1 3 2");
    Appendable gameLog = new StringBuffer();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(null);
  }

  @Test
  public void invalidRowInput() {
    TicTacToe m = new TicTacToeModel();
    StringReader in = new StringReader("one 0 q");
    Appendable out = new StringBuffer();
    TicTacToeController controller = new TicTacToeConsoleController(in, out);
    controller.playGame(m);
    assertEquals("   |   |  \n" +
            "-----------\n" +
            "   |   |  \n" +
            "-----------\n" +
            "   |   |  \n" +
            "Enter a move for X:\n" +
            "Not a valid number: one\n" +
            "Game quit! Ending game state:\n" +
            "   |   |  \n" +
            "-----------\n" +
            "   |   |  \n" +
            "-----------\n" +
            "   |   |  \n", out.toString());
  }

  @Test
  public void testInvalidColumn() {
    TicTacToe m = new TicTacToeModel();
    StringReader in = new StringReader("1 three q");
    Appendable out = new StringBuffer();
    TicTacToeController controller = new TicTacToeConsoleController(in, out);
    controller.playGame(m);
    assertEquals("   |   |  \n" +
            "-----------\n" +
            "   |   |  \n" +
            "-----------\n" +
            "   |   |  \n" +
            "Enter a move for X:\n" +
            "Not a valid number: three\n" +
            "Game quit! Ending game state:\n" +
            "   |   |  \n" +
            "-----------\n" +
            "   |   |  \n" +
            "-----------\n" +
            "   |   |  \n", out.toString());
  }

  @Test
  public void testOutOfBoundsInput() {
    TicTacToe m = new TicTacToeModel();
    StringReader in = new StringReader("10 1 q");
    Appendable out = new StringBuffer();
    TicTacToeController controller = new TicTacToeConsoleController(in, out);
    controller.playGame(m);
    assertEquals("   |   |  \n" +
            "-----------\n" +
            "   |   |  \n" +
            "-----------\n" +
            "   |   |  \n" +
            "Enter a move for X:\n" +
            "Not a valid move: 10, 1\n" +
            "Game quit! Ending game state:\n" +
            "   |   |  \n" +
            "-----------\n" +
            "   |   |  \n" +
            "-----------\n" +
            "   |   |  \n", out.toString());
  }

  @Test
  public void testOutOfBoundsColInput() {
    TicTacToe m = new TicTacToeModel();
    StringReader in = new StringReader("1 10 q");
    Appendable out = new StringBuffer();
    TicTacToeController controller = new TicTacToeConsoleController(in, out);
    controller.playGame(m);
    assertEquals("   |   |  \n" +
            "-----------\n" +
            "   |   |  \n" +
            "-----------\n" +
            "   |   |  \n" +
            "Enter a move for X:\n" +
            "Not a valid move: 1, 10\n" +
            "Game quit! Ending game state:\n" +
            "   |   |  \n" +
            "-----------\n" +
            "   |   |  \n" +
            "-----------\n" +
            "   |   |  \n", out.toString());
  }

  @Test
  public void testQuitOnRow() {
    TicTacToe m = new TicTacToeModel();
    StringReader in = new StringReader("1 2 q");
    Appendable out = new StringBuffer();
    TicTacToeController controller = new TicTacToeConsoleController(in, out);
    controller.playGame(m);
    assertTrue(out.toString().contains("Game quit!"));
  }

  @Test
  public void testQuitOnColumn() {
    TicTacToe m = new TicTacToeModel();
    StringReader in = new StringReader("1 2 1 q");
    Appendable out = new StringBuffer();
    TicTacToeController controller = new TicTacToeConsoleController(in, out);
    controller.playGame(m);
    assertTrue(out.toString().contains("Game quit!"));
  }

  @Test
  public void testOverlappingMove() {
    TicTacToe m = new TicTacToeModel();
    StringReader in = new StringReader("1 2 1 3 1 2 q");
    Appendable out = new StringBuffer();
    TicTacToeController controller = new TicTacToeConsoleController(in, out);
    controller.playGame(m);
    assertTrue(out.toString().contains("Not a valid move: 1, 2\n"));
  }

  @Test
  public void testContinuousInput() {
    TicTacToe m = new TicTacToeModel();
    StringReader in = new StringReader("1 5 1 2 2 1 1 1 2 2 1 3");
    Appendable out = new StringBuffer();
    TicTacToeController controller = new TicTacToeConsoleController(in, out);
    controller.playGame(m);
    //Output has a invalid move
    assertTrue(out.toString().contains("Not a valid move: 1, 5\n"));
    //Game continues and X wins
    assertTrue(out.toString().contains("Game is over! X wins."));
  }

  @Test
  public void testXWinsGame() {
    TicTacToe m = new TicTacToeModel();
    StringReader in = new StringReader("1 1 2 1 1 2 2 2 1 3");
    Appendable out = new StringBuffer();
    TicTacToeController controller = new TicTacToeConsoleController(in, out);
    controller.playGame(m);
    assertTrue(out.toString().contains("Game is over! X wins."));
  }

  @Test
  public void testOWinsGame() {
    TicTacToe m = new TicTacToeModel();
    StringReader in = new StringReader("1 1 2 1 1 2 2 2 3 1 2 3");
    Appendable out = new StringBuffer();
    TicTacToeController controller = new TicTacToeConsoleController(in, out);
    controller.playGame(m);
    assertTrue(out.toString().contains("Game is over! O wins."));
  }

  @Test
  public void testTieGame() {
    TicTacToe m = new TicTacToeModel();
    StringReader in = new StringReader("1 1 2 2 3 1 2 1 2 3 1 3 3 3 3 2 1 2");
    Appendable out = new StringBuffer();
    TicTacToeController controller = new TicTacToeConsoleController(in, out);
    controller.playGame(m);
    //System.out.println(out.toString());
    assertTrue(out.toString().contains("Game is over! Tie game."));
  }
}
