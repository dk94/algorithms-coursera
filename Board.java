import java.util.Iterator;

public class Board {
    
    private final int[][] board;
    private final int[][] finalBoard;
    private final int dimension;

    public Board(int[][] blocks) {
        board = blocks;
        dimension = blocks[0].length;
        finalBoard = new int[dimension][dimension];
        int counter = 1;
        for(int i=0; i < dimension; i++) {
            for(int j=0; j < dimension; j++) {
                if(j == dimension -1 & i == dimension -1) {
                    finalBoard[i][j] = 0;
                }
                finalBoard[i][j] = counter;
                counter++;
            }
        }
    }
    
    public int dimension() {
        return dimension;
    }
    
    public int hamming() {

        int currentHam = 0;
        for(int i = 0; i < dimension; i++) {
            for(int j=0; j < dimension; j++) {
                if(board[i][j] != 0 & board[i][j] != finalBoard[i][j]) {
                    currentHam++;
                }
            }
        }
        return currentHam;
    }
    
    public int manhattan() {
        return 0;
    }
    
    public boolean isGoal() {
        for(int i=0; i < dimension; i++) {
            for(int j=0; j < dimension; j++) {
                if(board[i][j] != finalBoard[i][j]) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    public Iterable<Board> neighbours() {
        return new neighboursCollection();
    }
    
    private class neighboursCollection implements Iterable<Board>{
        public neighboursCollection() {
            for()
        }
        
        private class neigboursIterator implements Iterator<Board> {
            public Board next() {
                return new Board(new int[0][0]);
            }
            
            public boolean hasNext() {
                return false;
            }
            
            
        }
 
        public Iterator<Board> iterator(){
            return new neigboursIterator();
        }
    }
    
    public static void main(String[] args) {

    }
}
