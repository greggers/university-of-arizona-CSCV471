package hw4HeuristicSearch;

import java.awt.Dimension;

import java.util.ArrayList;

import java.util.Collections;

import java.util.Iterator;

import hw3BlindSearch.AbstractSearchEngine;
import hw3BlindSearch.Maze;

public class AStarSearchEngine extends AbstractSearchEngine {

    public AStarSearchEngine(Maze maze) {

        super(maze);
//
//        search();
//
    }
//
//  
//
//    private void search(){
//
//        Node node = new Node(startLoc, 1, null); 
//
//        //more code for the start location node
//
//         fringe.add(node);
//
//    
//
//        while (isSearching == true){
//
//             if (fringe.isEmpty()){ //failure
//
//                 System.out.println("AStar fails to find a solution!");
//
//                break; 
//
//              }
//
//             node = fringe.remove(0);
//
//             //more code to search
//
// 
//
//             Dimension [] moves = getPossibleMoves(node);
//
//             for (int i=0; i<4; i++) {
//
//                //check the possible move 
//
//               //if we need to create a node for this move
//
//                Node child = new Node(moves[i], depth +1, node );
//
//                //more code to estimate the cost of the new node to the goal
//
//                //if we want to add the node to the fringe
//
//                fringe.add(child);    
//
//                //whatever code we want to add here
//
//            }
//
//            //sort the nodes on the fringe based on their f values
//
//            Collections.sort(fringe);
//
//      }
//
//      //more code for search
//
//     }
//
//    
//
//    static private class Node implements Comparable<Node>{
//
//         Node(Dimension loc,  int depth, Node parent){
//
//             this.loc = loc;
//
//             this.f = -1;
//
//            this.depth = depth;
//
//            this.parent = parent;
//
//         }
//
//         //more methods could be defined
//
//         //....e.g. getF()   
//
//          public boolean equals(Object one){
//
//             boolean result = false;
//
//             if (!(one instanceof Node)) return result;
//
//             Node onenode = (Node)one;
//
//             if (onenode.getLoc().equals(loc)) return true;
//
//             return false; 
//
//         }
//
//         public int compareTo(Node one){
//
//              return (new Integer(f)).compareTo(new Integer(one.getF()));
//
//         }
//
//        private Dimension loc;
//
//        private int f; 
//
//        private int depth = -1; 
//
//        private Node parent = null;    
//
//      
//
//    }
//
//    
//
//    private int h1(Node n){
//
//     //more code here to define heuristic functions
//    	return 0;
//    }
//
//    private ArrayList<Node> fringe = new ArrayList<Node>();

}
