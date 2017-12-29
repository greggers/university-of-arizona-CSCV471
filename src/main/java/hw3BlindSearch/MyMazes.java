package hw3BlindSearch;

/**
 * Title:        MyMazes<p>
 * Description:  A driver program drives blind search strategies<p>
 * @author Li Xu
 * @version 1.0
 */

public class MyMazes {
    static boolean DEBUG = false; 
    public static void main(String[] args) {
    	Maze maze = new Maze(10, 10); 
        DepthFirstSearchEngine searchE1 = new DepthFirstSearchEngine(new Maze(maze));
        MazeView view1 = new MazeView(searchE1); ;
        BreadthFirstSearchEngine searchE2 = new BreadthFirstSearchEngine(new Maze(maze));
        MazeView view2 = new MazeView(searchE2); 
        IterativeDeepeningSearchEngine searchE3 = new IterativeDeepeningSearchEngine(new Maze(maze));
        MazeView view3 = new MazeView(searchE3); 
    }
}
