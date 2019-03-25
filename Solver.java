import java.util.Comparator;
import java.util.Iterator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
    private final MinPQ minPQ;
    private int moves = 0;
    private Board solution;
    private class Node {
    	public Node(Board b, int m, Board p) {
    		item = b;
    		currentMoves = m;
    		previous = p;
    	}
    	private Board item;
    	private int currentMoves;
    	private Board previous;
    }
    public Solver(Board initial) {
        Board currentMin = initial;
        minPQ = new MinPQ(comparator());
        Board previous = null;
        while(!currentMin.isGoal()) {
            
           for(Board neigbour : currentMin.neighbours()) {
               if(previous == null || !neigbour.equals(previous)) {
            		   minPQ.insert(new Node(neigbour, moves + 1, currentMin));   
               }
           }
           
           Node minNode = (Node)minPQ.delMin();
           moves = minNode.currentMoves;
           previous = minNode.previous;
           currentMin = minNode.item;
           
           StdOut.println(currentMin + "  " + currentMin.manhattan()+ "moves" + minNode.currentMoves);
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
            	return 0;
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
