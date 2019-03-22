import java.util.Comparator;
import java.util.Iterator;

import edu.princeton.cs.algs4.MinPQ;

public class Solver {
    private final MinPQ minPQ;
    private int moves;
    private Board solution;
    public Solver(Board initial) {
        Board currentMin = initial;
        minPQ = new MinPQ(comparator());
        while(!currentMin.isGoal()) {
            moves++;

           for(Board neigbour : currentMin.neighbours()) {
              
               minPQ.insert(neigbour);
           }
           
           currentMin = (Board)minPQ.delMin();
           
           
        }
        
        
        solution = currentMin;
        
    }
    public boolean isSolvable() {return false;}            // is the initial board solvable?
    public int moves() {return moves;}                    // min number of moves to solve initial board; -1 if unsolvable
    public Board solution() {
        return solution;
    }
    
    private Comparator<Board> comparator() {
        return new ManhattanComparator();
    }
    
    private class HammingComparator implements Comparator<Board> {
        public int compare(Board o1, Board o2) {
            if(o1.hamming() > o2.hamming()) return 1;
            else if(o1.hamming() == o2.hamming()) {
                return 0;
            }
            else return -1;
        }
        
    }
    
    private class ManhattanComparator implements Comparator<Board> {
        public int compare(Board o1, Board o2) {
            if(o1.manhattan() > o2.manhattan()) return 1;
            else if(o1.manhattan() == o2.manhattan()) {
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
