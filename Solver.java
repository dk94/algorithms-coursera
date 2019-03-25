import java.util.Comparator;
import java.util.Iterator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
    private final MinPQ minPQ;
    private int moves;
    private Board solution;
    private class Node {
    	public Node(Board b, int m) {
    		item = b;
    		currentMoves = m;
    	}
    	private Board item;
    	private int currentMoves;
    }
    public Solver(Board initial) {
        Board currentMin = initial;
        minPQ = new MinPQ(comparator());
        Board previous = null;
        while(!currentMin.isGoal()) {
            moves++;
            
           for(Board neigbour : currentMin.neighbours()) {
              StdOut.println(neigbour + " " + neigbour.manhattan() + "move:" + moves);
               if(previous == null || !neigbour.equals(previous)) {
            		   minPQ.insert(new Node(neigbour, moves));   
               }
           }
           
           Node minNode = (Node)minPQ.delMin();
           previous = currentMin;
           currentMin = minNode.item;
        }
        
        
        solution = currentMin;
        
    }
    public boolean isSolvable() {return false;}            // is the initial board solvable?
    public int moves() {return moves;}                    // min number of moves to solve initial board; -1 if unsolvable
    public Board solution() {
        return solution;
    }
    
    private Comparator<Node> comparator() {
        return new ManhattanComparator();
    }
    
    private class HammingComparator implements Comparator<Board> {
        public int compare(Board o1, Board o2) {
          return 0;
        }
        
    }
    
    private class ManhattanComparator implements Comparator<Node> {
        public int compare(Node o1, Node o2) {
            if((o1.item.manhattan() + o1.currentMoves) > (o2.item.manhattan() + o2.currentMoves) ) return 1;
            else if((o1.item.manhattan() + o1.currentMoves)  == (o2.item.manhattan() + o2.currentMoves) ) {
            	 if((o1.item.manhattan())  > (o2.item.manhattan())) return 1;
                 else if((o1.item.manhattan()) < (o2.item.manhattan())) {
                     return -1;
                 }
                 else return 0;
            }
            else return -1;
        }
        
    }

 
    // sequence of boards in a shortest solution; null if unsolvable
    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
        
        StdOut.println(initial.manhattan());
        
        Solver solver = new Solver(initial);
        StdOut.println("Minimum number of moves = " + solver.moves());
        StdOut.println(solver.solution());
        
    } // solve a slider puzzle (given below)
}
