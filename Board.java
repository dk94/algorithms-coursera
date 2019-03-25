import java.util.Iterator;

import edu.princeton.cs.algs4.LinkedStack;

public class Board {
    
    private final int[][] board;
    private final int dimension;
    private int moves;

    public Board(int[][] blocks) {
        board = blocks;
        dimension = blocks[0].length;
    }
    
    public int dimension() {
        return dimension;
    }
    
    public int hamming() {

        int currentHam = 0;
        for(int i = 0; i < dimension; i++) {
            for(int j=0; j < dimension; j++) {
                if(board[i][j] != 0 & board[i][j] != i*dimension+j+1) {
                    currentHam++;
                }
            }
        }
        return currentHam;
    }
    
    private void setMovesForBoard(int moves) {
    	moves = moves;
    }
    
    public int manhattan() {
        int manhattan = 0;
        for(int i = 0; i < dimension; i++) {
            for(int j=0; j < dimension; j++) {
                if(board[i][j] != 0 ) {
                   int originRow = (board[i][j] - 1) / dimension;
                   int originColumn = (board[i][j] -1) % dimension;
                   
                   manhattan += Math.abs(originRow -i) + Math.abs(originColumn - j);
                }
            }
        }
        
        return manhattan;
    }
    
    public boolean isGoal() {
        for(int i=0; i < dimension; i++) {
            for(int j=0; j < dimension; j++) {
                if(board[i][j] != i*dimension+j+1 & board[i][j] != 0) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    public Iterable<Board> neighbours() {
        return new neighboursCollection();
    }
    
    public boolean equals(Object y) {
    	Board compBoard = (Board) y;
    	return this.toString().equals(compBoard.toString());
    }
    
    private int[][] copyArray(){
        int [][] copy = new int[dimension][dimension];
        for(int i=0; i<dimension; i++) {
            for(int j=0; j<dimension; j++) {
              copy[i][j] = board[i][j];
            }
        }
        
        return copy;
    }
    
    private void exchange(int [][] arr, int i, int j, int k, int m) {
        int temp = arr[i][j];
        arr[i][j] = arr[k][m];
        arr[k][m] = temp;
    }
    
    private class neighboursCollection implements Iterable<Board>{
        
        private LinkedStack stack = new LinkedStack();
        public neighboursCollection() {
            int zeroRowPosition=0;
            int zeroColumnPosition=0;
            

            for(int i=0; i<dimension; i++) {
                for(int j=0; j<dimension; j++) {
                    if(board[i][j] == 0) {
                        zeroRowPosition = i;
                        zeroColumnPosition = j;
                        break;
                    }
                }
            }
            
            if(zeroRowPosition + 1 < dimension) {
                int[][] arrayC = copyArray();
                exchange(arrayC,
                        zeroRowPosition, 
                        zeroColumnPosition, 
                        zeroRowPosition+1, 
                        zeroColumnPosition);

                Board neigbour = new Board(arrayC);
                
                stack.push(neigbour);
            }
            
            if(zeroRowPosition - 1 >= 0) {
                int[][] arrayC = copyArray();
                exchange(arrayC,
                        zeroRowPosition, 
                        zeroColumnPosition, 
                        zeroRowPosition-1, 
                        zeroColumnPosition);

                stack.push(new Board(arrayC));
            }
            
            if(zeroColumnPosition - 1 >= 0) {
                int[][] arrayC = copyArray();
                exchange(arrayC,
                        zeroRowPosition, 
                        zeroColumnPosition, 
                        zeroRowPosition, 
                        zeroColumnPosition-1);

                stack.push(new Board(arrayC));
            }
            
            if(zeroColumnPosition + 1 < dimension) {
                int[][] arrayC = copyArray();
                exchange(arrayC,
                        zeroRowPosition, 
                        zeroColumnPosition, 
                        zeroRowPosition, 
                        zeroColumnPosition+1);

                stack.push(new Board(arrayC));
            }
        }
        
        private class neigboursIterator implements Iterator<Board> {
        	Iterator<Board> iterator = stack.iterator();
            public Board next() {
                return (Board)iterator.next();
            }
            
            public boolean hasNext() {
                return iterator.hasNext();
            }
            
            
        }
 
        public Iterator<Board> iterator(){
            return new neigboursIterator();
        }
    }
    
    public String toString()  {
        StringBuilder str = new StringBuilder();
        str.append(dimension);
        str.append("\n");
        
        for(int i=0; i<dimension; i++) {
            for(int j=0; j<dimension; j++) {
                str.append(board[i][j]);
                str.append(" ");
            }
            
            str.append("\n");
        }
        
        return str.toString();
        
        
    }
    
    public static void main(String[] args) {

    }
}
