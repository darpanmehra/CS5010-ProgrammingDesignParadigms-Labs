package tictactoe;


import java.awt.*;

import javax.swing.*;

public class TTTPanel extends JPanel {

  private final ReadonlyTttModel model;

  public static final int CELL_SIZE = 120;
  public static final int OFFSET = 120;
  public static final int FONT_SIZE = 30;
  public static final String FONT = "Helvetica";


  TTTPanel(ReadonlyTttModel model) {
    this.model = model;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;

    for (int i = 1; i < 3; i++) {
      g2.drawLine(i * CELL_SIZE + OFFSET, OFFSET, i * CELL_SIZE + OFFSET, 3 * CELL_SIZE + OFFSET);
      g2.drawLine(OFFSET, i * CELL_SIZE + OFFSET, 3 * CELL_SIZE + OFFSET, i * CELL_SIZE + OFFSET);
    }
    g2.setFont(new Font(FONT, Font.PLAIN, FONT_SIZE));
    Player[][] board = model.getBoard();
    for (int r = 0; r < 3; r++) {
      for (int c = 0; c < 3; c++) {
        if (board[r][c] != null) {
          g2.drawString(board[r][c].toString(), Math.round((c + 0.29) * CELL_SIZE + OFFSET),
                  Math.round((r + 0.7) * CELL_SIZE + OFFSET));
        }
      }
    }
    if (model.isGameOver()) {
      g2.drawString(
              "Game over: "
                      + (model.getWinner() == null ? "tie game." : model.getWinner().toString() + " wins."),
              OFFSET, OFFSET - 4);
    } else {
      g2.drawString("Turn: " + model.getTurn().toString(), OFFSET, OFFSET - 4);
    }
  }
}
