package hw5Game;

public class MachinePlayer extends Player {

  protected int ncells;

  MachinePlayer(Game game, int id) {
    super(game, id); 
    Board board = game.getBoard(); 
    ncells = board.getRow() * board.getCol(); 
  }

  public Move makeMove() {
    try {
      Thread.currentThread().sleep(1000); 
    } catch (InterruptedException e) {} 

    Move move = new Move();   
    Board board = game.getBoard(); 
    int row = game.getBoard().getRow();
    int i = (int)(Math.random() * ncells); 
    move.row = i / row; 
    move.col = i % row; 
    while (!board.isLegalMove(move)) {
      i++; 
      if (i >= ncells) 
	    i = i % ncells;
      move.row = i / row; 
      move.col = i % row; 
    }
    return move; 
  }

	

}
