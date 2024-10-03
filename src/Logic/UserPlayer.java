package Logic;

import Structure.Board;
import Structure.Item;
import Structure.Position;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
public class UserPlayer {
    public ArrayList<Position> getTargets() {
        return targets;
    }
    private ArrayList<Position> targets ;
    public ArrayList<Position> getEggs() {
        return Eggs;
    }
    private ArrayList<Position> Eggs ;
    private int bunnyRow ;
    private int bunnyColumn ;
    public Board board;
    public int getBunnyRow(){
        return bunnyRow;
    }
    public int getBunnyColumn() {
        return bunnyColumn;
    }
    public UserPlayer(char[][]initialBoard,char[][] finalBoard){
        targets = new ArrayList<>();
        Eggs = new ArrayList<>();
        board = new Board(initialBoard,finalBoard);

        for (int row=0 ; row< board.getBoardHeight() ; row++){
            for (int col=0 ; col<board.getBoardWidth() ; col++){
                if (board.getBoard()[row][col] == Item.TARGET){
                    targets.add(new Position(row,col));
                }
                if (board.getBoard()[row][col] == Item.EGG){
                    Eggs.add(new Position(row,col));
                }
            }
        }
        findBunnyPosition();
    }
    public UserPlayer(char[][]initialBoard,char[][] finalBoard , ArrayList<Position> targets,ArrayList<Position> Eggs){
        this.targets = new ArrayList<>();
        this.Eggs = new ArrayList<>();
        for(Position item: targets){
            this.targets.add(item);
        }
        for(Position item: Eggs){
            this.Eggs.add(item);
        }
        board = new Board(initialBoard,finalBoard);

        findBunnyPosition();
    }
    private void findBunnyPosition(){
        for (int row=0 ; row< board.getBoardHeight() ; row++){
            for (int col=0 ; col<board.getBoardWidth() ; col++){
                if (board.getBoard()[row][col] == Item.BUNNY){
                    bunnyRow = row;
                    bunnyColumn = col;
                }
            }
        }
    }
    public boolean isValidMove(int newRow,int newColumn){
        if (board.getBoard()[newRow][newColumn] == Item.STONE){
            return false;
        } else if (board.getBoard()[newRow][newColumn] == Item.EGG) {
            int newEggRow = newRow + (newRow - bunnyRow);
            int newEggColumn = newColumn + (newColumn - bunnyColumn);
            return board.getBoard()[newEggRow][newEggColumn] != Item.STONE && board.getBoard()[newEggRow][newEggColumn] != Item.EGG;
        }
        return true;
    }
    public void moveBunny(int newRow , int newColumn){
        if (isValidMove(newRow,newColumn)){
            if (board.getBoard()[newRow][newColumn] == Item.EGG){
                int newEggRow = newRow + (newRow - bunnyRow);
                int newEggColumn = newColumn + (newColumn - bunnyColumn);
                Eggs.remove(new Position(newRow,newColumn));
                Eggs.add(new Position(newEggRow,newEggColumn));
                this.board.getBoard()[newEggRow][newEggColumn] = Item.EGG;
            }
            this.board.getBoard()[bunnyRow][bunnyColumn] = (targets.contains(new Position(bunnyRow,bunnyColumn))) ? Item.TARGET : Item.EMPTY;
            this.board.getBoard()[newRow][newColumn] = Item.BUNNY;
            this.bunnyRow = newRow;
            this.bunnyColumn = newColumn;
        }
    }
    public UserPlayer copy(){
        UserPlayer userPlayer = new UserPlayer(this.board.copy().getBoard(),this.board.getFinalBoard(),this.getTargets(),this.getEggs());
        return userPlayer ;
    }
    public boolean isWin() {
        for (int i=0;i<this.board.getBoardHeight();i++)
            for(int j=0;j<this.board.getBoardWidth();j++){
                if((this.board.getBoard()[i][j] == 'B' && this.board.getFinalBoard()[i][j] != 'E') || this.board.getFinalBoard()[i][j] == 'B')
                    continue;
                if(this.board.getBoard()[i][j] != this.board.getFinalBoard()[i][j])
                    return false;
        }

        return true;
    }
    public void playGame(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Your Move : ");
        while (true){
            board.printBoard();
            if (isWin()){
                System.out.println("You won the game !");
                break;
            }
            System.out.print("Enter Your Move : ");
            String input = scanner.nextLine().toUpperCase();

            int newRow = bunnyRow;
            int newCol = bunnyColumn;

            switch (input) {
                case "W":
                case "UP":
                    newRow--;
                    break;
                case "S":
                case "DOWN":
                    newRow++;
                    break;
                case "A":
                case "LEFT":
                    newCol--;
                    break;
                case "D":
                case "RIGHT":
                    newCol++;
                    break;
            }

            moveBunny(newRow,newCol);
        }
        scanner.close();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPlayer that = (UserPlayer) o;
        return bunnyRow == that.bunnyRow && bunnyColumn == that.bunnyColumn && this.board.equals(that.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bunnyRow, bunnyColumn, board);
    }
}
