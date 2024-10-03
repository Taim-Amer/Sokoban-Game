package Level;

import GUI.Levels;
import Structure.Board;
import Structure.Item;
import Structure.Position;

import java.util.ArrayList;

//package Level;
//
//import Structure.Item;
//
//import java.util.ArrayList;
//
//public class Level1 {
//    public char[][] initialBoard = {
//            {'#','#','#','#','#','#',' ',' ',' '},
//            {'#',' ',' ',' ','#','#','#','#','#'},
//            {'#',' ','E',' ','#',' ',' ',' ','#'},
//            {'#',' ',' ',' ','#','E','#',' ','#'},
//            {'#','#','#','E',' ',' ',' ',' ','#'},
//            {'#','#',' ',' ',' ','#','#','#','#'},
//            {' ','#',' ','B','G','G','G','#',' '},
//            {' ','#','#','#','#','#','#','#',' '}
//    };
//}
public class Level1 implements Levels {
    @Override
    public char[][] getInitialBoard() {
        return new char[][] {
                {'#', '#', '#', '#', '#', '#', ' ', ' ', ' '},
                {'#', ' ', ' ', ' ', '#', '#', '#', '#', '#'},
                {'#', ' ', 'E', ' ', '#', ' ', ' ', ' ', '#'},
                {'#', ' ', ' ', ' ', '#', 'E', '#', ' ', '#'},
                {'#', '#', '#', 'E', ' ', ' ', ' ', ' ', '#'},
                {'#', '#', ' ', ' ', ' ', '#', '#', '#', '#'},
                {' ', '#', ' ', 'B', 'G', 'G', 'G', '#', ' '},
                {' ', '#', '#', '#', '#', '#', '#', '#', ' '}
        };
    }
        public char[][] getFinalBoard(){
            return new char[][] {
                    {'#', '#', '#', '#', '#', '#', ' ', ' ', ' '},
                    {'#', ' ', ' ', ' ', '#', '#', '#', '#', '#'},
                    {'#', ' ', ' ', ' ', '#', ' ', ' ', ' ', '#'},
                    {'#', ' ', ' ', ' ', '#', ' ', '#', ' ', '#'},
                    {'#', '#', '#', ' ', ' ', ' ', ' ', ' ', '#'},
                    {'#', '#', ' ', ' ', ' ', '#', '#', '#', '#'},
                    {' ', '#', ' ', 'B', 'E', 'E', 'E', '#', ' '},
                    {' ', '#', '#', '#', '#', '#', '#', '#', ' '}
            };
        }

}