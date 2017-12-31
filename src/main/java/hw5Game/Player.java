package hw5Game;

abstract public class Player extends Thread {
  final static int MAX = 1;
  final static int MIN = 2; 

  protected Player next; // the other player 
  protected Player turn; // go if turn==this

  protected Game game; 
  protected int id; 

  public Player(Game game, int id) {
    this.game = game; 
    this.id = id; 
    turn = null; 
    next = null; 
  }

  public int getID() {
    return id; 
  }

  public synchronized void setNext(Player p){
    next = p;
  }

  public synchronized void turn() { 
    turn = this;
    game.turn = this; 
    notifyAll(); 
  }

  abstract public Move makeMove(); 

  public void selectCell(int x, int y) {} 

  public synchronized void run() {
    while (!game.isOver()) {
      while (turn != this) {   
        try { 
	      // wait until it is your turn
	      wait(); 
	      // wake up from turn method
	    }         
        catch (InterruptedException ex) { return; }
      }
      if (game.isOver()) 
	    break; 
      game.displayMessage("Player " + id + "'s turn"); 
      while (true) {
	    Move move = makeMove(); 
	    if (game.makeMove(move)) {
	      break;
	    } 
	    else { 
	      game.displayMessage("Illegal move!"); 
	    }
      }
      turn = null;
      next.turn(); //let the next player run
    }
  }
}
