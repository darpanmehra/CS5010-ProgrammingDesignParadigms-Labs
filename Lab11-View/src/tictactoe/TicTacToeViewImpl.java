package tictactoe;

import javax.swing.*;

public class TicTacToeViewImpl extends JFrame implements TicTacToeView{

  private final TTTPanel panel;
  private final JLabel label;

  public TicTacToeViewImpl(ReadonlyTttModel model) {
    super("Tic Tac Toe Game");
    setSize(600, 600);
    setPreferredSize(new java.awt.Dimension(600, 600));
    setLocation(450, 450);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    panel = new TTTPanel(model);
    panel.setPreferredSize(new java.awt.Dimension(600, 600));
    label = new JLabel();
    panel.add(label);
    JScrollPane scroll = new JScrollPane(panel);
    this.add(scroll);
  }

  @Override
  public void addClickListener(TicTacToeController listener) {
    TTTMouseAdapter ml = new TTTMouseAdapter(listener);
    panel.addMouseListener(ml);
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }
}
