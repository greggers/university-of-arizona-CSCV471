package hw5Game;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.*;

public class Board extends JPanel implements Cloneable{

  protected Game game; 
  protected int row, col;
  protected int rowWidth, colHeight; 
  protected int board[][];
  protected int maxMoves; 
  protected int moves; 
  protected int utility;
  

  protected boolean isover = false; 
  protected int winner = -1; 

  public Board(Game game, int row, int col) {
    this.game = game; 
    this.row = row; 
    this.col = col; 
    maxMoves = row * col; 
    moves = 0; 
    board = new int[row][col]; 
    utility = Integer.MIN_VALUE;
    addMouseListener(new MouseHandler());
  }
  
  
  protected Object clone(){
	 Board cloned = new Board(this.game, row, col);
	 cloned.moves = moves; 
	 for (int i=0; i<row; i++)
		 for (int j=0; j<col; j++)
			 cloned.board[i][j] = board[i][j];
	 return cloned;
  }

  public void paintComponent(Graphics g) {
    Dimension d = getSize(); 
    rowWidth = d.width / row; 
    colHeight = d.height / col; 
    int i, j; 
    for (i = 1; i < row; i++) 
      g.drawLine(i * rowWidth, 0, i * rowWidth, d.height); 
    for (j = 1; j < col; j++) 
      g.drawLine(0, j * colHeight, d.width, j * colHeight); 
    
    Font font = new Font("Helvetica", Font.BOLD, 48);
    g.setFont(font); 
    for (i = 0; i < row; i++) 
      for (j = 0; j < col; j++) 
	if (board[i][j] != 0) {
	  int x = i * rowWidth + 12; 
	  int y = j * colHeight + 60; 
	  switch (board[i][j]) {
	  case 1:
	    g.setColor(Color.red); 
	    g.drawString("X", x, y); 
	    break; 
	  case 2:
	    g.setColor(Color.blue); 
	    g.drawString("O", x, y); 
	    break; 
	  }
	}

  }

  public int getRow() {
    return row; 
  }

  public int getCol() {
    return col; 
  }

  public boolean isOver() {
    return isover; 
  }

  public int getWinner() {
    return winner; 
  }

  public boolean makeMove(Move move, int playerId) {
    if (isLegalMove(move)) {
      moves++; 
      board[move.row][move.col] = playerId; 
      repaint(); 
      return true; 
    } 
    else {
      return false; 
    }
  }

  public boolean isLegalMove(Move move) {
    return (board[move.row][move.col] == 0); 
  }

  protected void checkGame(int playerId) {
    if (moves > maxMoves) {
      isover = true;
      return; 
    }

    boolean win = false; 
    for (int i = 0; i < row; i++)
      if (checkRow(playerId, i)) {
	    win = true; 
	    break; 
      }
      if (!win) 
        for (int i = 0; i < col; i++)
	      if (checkCol(playerId, i)) {
	        win = true; 
	        break; 
	      }
      if (!win) 
        win = checkDiagnal(playerId); 
    
    if (win) {
      winner = playerId; 
      isover = true; 
    }

    if (moves >= maxMoves) {
      isover = true; 
    }
  }

  protected boolean checkRow(int playerId, int row) {
    for (int i = 0; i < col; i++) 
      if (board[row][i] != playerId) 
	    return false; 
    return true; 
  }

  protected boolean checkCol(int playerId, int col) {
    for (int i = 0; i < row; i++) 
      if (board[i][col] != playerId) 
	    return false; 
    return true; 
  }

  protected boolean checkDiagnal(int playerId) {
    boolean result = true; 
    for (int i = 0; i < row; i++) 
      if (board[i][i] != playerId) {
	    result = false; 
	    break; 
      }
    if (result)
      return true;
    result = true; 
    for (int i = 0; i < row; i++) 
      if (board[i][col - i - 1] != playerId) {
	    result = false; 
	    break; 
      }
    return result; 
  }
  
  void setUtility(int score){
	  utility = score;
  }
  
   int computeUtility(){
	  int score = 0; 
	  
	  if (winner == 1){ 
		  score = 1;
	  }
	  if (winner == 2){
		  score = -1;
	  }
	  return score;
  }
  int getPlayId(){
	  if (moves % 2 == 0) return 1;
	  return 2;
  }
  
  public String toString(){
      StringBuffer buf = new StringBuffer();
      buf.append("Board:\n");
      for (int i=0; i<row; i++){
    	  for (int j=0; j<col; j++)
    		  buf.append("\t"+board[i][j]);
    	  buf.append("\n");
      }
      buf.append("\n\nmoves:" + moves);
      return buf.toString();
  }

  class MouseHandler extends MouseAdapter {
    public void mouseClicked(MouseEvent event) {
      Point p = event.getPoint();  
      game.getPlayer().selectCell(p.x / rowWidth, p.y / colHeight); 
    }
  }

}
