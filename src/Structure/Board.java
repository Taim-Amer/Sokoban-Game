package Structure;

import java.util.Arrays;
public class Board {
    private char[][] board;
    private char[][] finalBoard;

    public Board(char[][] initialBoard,char[][] finalBoard){
        this.board = initialBoard;
        this.finalBoard = finalBoard;
    }
    public int getBoardHeight(){
        return board.length;
    }
    public int getBoardWidth(){
        return board[0].length;
    }
    public char[][] getBoard() {
        return board;
    }
    public char[][] getFinalBoard() {
        return finalBoard;
    }
    public void printBoard() {
        for (int row=0 ; row<getBoardHeight() ; row++){
            for (int col=0 ; col<getBoardWidth() ; col++){
                System.out.print(board[row][col]);
            }
            System.out.println();
        }
        System.out.println();
    }
    public Board copy() {
        char[][] newBoard = new char[this.getBoardHeight()][this.getBoardWidth()];
        for (int row=0 ; row<this.getBoardHeight() ; row++){
            for(int col=0 ; col<this.getBoardWidth() ; col++){
                newBoard[row][col] = this.board[row][col];
            }
        }
        return new Board(newBoard,this.getFinalBoard());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board1 = (Board) o;
        for (int row=0 ; row<this.getBoardHeight() ; row++){
            for (int col=0 ; col<this.getBoardWidth() ; col++){
                if (this.board[row][col] != board1.getBoard()[row][col]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(board);
    }

}
