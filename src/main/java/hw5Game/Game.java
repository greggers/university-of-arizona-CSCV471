package hw5Game;

import javax.swing.*; 
import java.awt.Container;
import java.awt.BorderLayout;

public class Game extends JFrame {

  protected Player players[];
  protected Player turn; 

  protected Board  board;
  protected JLabel  messageBar; 

  public Game(String gametype) {
    setSize(700, 700);
    setTitle("CSCV471 Tictactoe Game");
    setDefaultCloseOperation(EXIT_ON_CLOSE);   
    players = new Player[2];
    board = new Board(this, 3, 3);
    messageBar = new JLabel("Game begin."); 
    Container container = getContentPane();
    container.setLayout(new BorderLayout()); 
    container.add("Center", board);  
    container.add("South", messageBar);
    //pack();
    setVisible(true);
    buildGame(gametype);
    //buildGame("man-human");
  }


  public void buildGame(String gametype) {
    if (gametype.equals("man-man")) {
      players[0] = new HumanPlayer(this, Player.MAX); 
      players[1] = new HumanPlayer(this, Player.MIN);
    } else if (gametype.equals("mac-mac")) {
      players[0] = new MachinePlayer(this, Player.MAX); 
      players[1] = new MachinePlayer(this, Player.MIN);
    } else {
      players[0] = new HumanPlayer(this, Player.MAX);
      players[1] = new MachinePlayer(this, Player.MIN);
      //players[1] = new MiniMaxPlayer(this, Player.MIN);
    }
    players[0].start();
    players[1].start();
    players[0].setNext(players[1]);
    players[1].setNext(players[0]);
    players[0].turn(); 
  }

  /** 
    return false if it's an illegal move. 
    */
  public boolean makeMove(Move move) {
    if (board.makeMove(move, turn.getID())) {
      board.checkGame(turn.getID());
      if (isOver()) {
	    int winner = board.getWinner(); 
	    if (winner >= 0) {
	      displayMessage("Player " + winner + " won."); 
	    } 
	    else {
	      displayMessage("It's a draw");
	    }
	    for (int n = 0; n < players.length; n++)
	      players[n].interrupt(); 
      }
      return true; 
    } 
    else {
      return false; 
    }
  }

  public Player getPlayer() {
    return turn; 
  }

  public Board getBoard() {
    return board; 
  }

  public boolean isOver() {
    return board.isOver(); 
  }

  public void displayMessage(String msg) {
    messageBar.setText(msg); 
    repaint();
  } 
  public static void main(String[] args){
      Game instance = new Game("man-mac");
  }
}

