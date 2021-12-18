package tictactoe;

public class TicTacToeControllerImpl implements TicTacToeController {

  private TicTacToeModel model;
  private TicTacToeView view;

  public TicTacToeControllerImpl(TicTacToeModel model, TicTacToeView view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void playGame(TicTacToe m) {
    view.addClickListener(this);
    view.makeVisible();
  }

  @Override
  public void handleCellClick(int row, int col) {
    try {
      model.move(row, col);
      view.refresh();
    } catch (IllegalArgumentException e) {
      // do nothing
    } catch (IllegalStateException e) {
      // do nothing
    }
  }
}
