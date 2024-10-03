import GUI.GUI;
import Level.Level1;
import Level.Level2;
import Level.Level3;
import Level.Level4;
import Logic.*;
import Structure.Node;
import Structure.State;

import javax.swing.*;
import java.util.Objects;
import java.util.Scanner;

public class Main{

    public static void main(String[] args){
        Level1 level1 = new Level1();
        Level2 level2 = new Level2();
        Level3 level3 = new Level3();
        Level4 level4 = new Level4();

        UserPlayer userPlayer = new UserPlayer(level1.getInitialBoard(),level1.getFinalBoard());
        State state = new State(userPlayer);
        Scanner scanner = new Scanner(System.in);
        Node node = new Node(state,null,"");

        System.out.println("Hello and welcome to my Sokoban Game please select :");
        System.out.println("1_User Player \t 2-Dfs \t 3_Bfs \t 4_Ucs \t 5_A*");
        int userInput = scanner.nextInt();

        switch (userInput){
            case 1:
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        GUI gameBoard = new GUI(level2);
                        gameBoard.setVisible(true);
                    }
                });
                userPlayer.playGame();
                break;

            case 2:
                Node endDfs = DFS.solveDfs(node);
                Objects.requireNonNull(endDfs).getState().getUserPlayer().board.printBoard();
                break;

            case 3:
                Node endBfs = BFS.solveBfs(node);
                Objects.requireNonNull(endBfs).getState().getUserPlayer().board.printBoard();
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        GUI gameBoard = new GUI(level2);
                        gameBoard.setVisible(true);
                    }
                });
                break;

            case 4:
                Node endUcs = UCS.solveUCS(node);
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        GUI gameBoard = new GUI(level2);
                        gameBoard.setVisible(true);
                    }
                });
                Objects.requireNonNull(endUcs).getState().getUserPlayer().board.printBoard();
                break;

            case 5:
                Node endAStar = AStar.solveAStar(node);
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        GUI gameBoard = new GUI(level2);
                        gameBoard.setVisible(true);
                    }
                });
                Objects.requireNonNull(endAStar).getState().getUserPlayer().board.printBoard();
                break;
        }
    }
}