package hw5Game;

public class HumanPlayer extends Player {

  Move move; 

  HumanPlayer(Game game, int id) {
    super(game, id); 
    move = new Move(); 
  }

  synchronized public Move makeMove() {
    //System.out.println("HumanPlayer " + id + " makeMove");

    try {
      wait(); 
    } 
    catch (InterruptedException e) {
    }         
    return move; 
  }

  synchronized public void selectCell(int x, int y) {
    move.row = x;
    move.col = y; 
    notifyAll(); 
  }

}
