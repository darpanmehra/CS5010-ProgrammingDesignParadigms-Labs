package tictactoe;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TTTMouseAdapter extends MouseAdapter {

  private TicTacToeController listener;

  public TTTMouseAdapter(TicTacToeController listener) {
    this.listener = listener;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    int row = (e.getY() - TTTPanel.OFFSET) / TTTPanel.CELL_SIZE;
    int col = (e.getX() - TTTPanel.OFFSET) / TTTPanel.CELL_SIZE;
    listener.handleCellClick(row, col);
  }

}
