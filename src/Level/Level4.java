package Level;

import GUI.Levels;

public class Level4 implements Levels {
    @Override
    public char[][] getInitialBoard() {
        return new char[][] {
                {'#','#','#','#','#','#','#'},
                {'#',' ',' ',' ','B','#','#'},
                {'#',' ','E',' ',' ',' ','#'},
                {'#','#',' ','#','#','#','#'},
                {'#',' ',' ','#',' ',' ','#'},
                {'#','#','G',' ',' ',' ','#'},
                {'#','#','#','#','#','#','#'},
        };
    }
    public char[][] getFinalBoard(){
        return new char[][] {
                {'#','#','#','#','#','#','#'},
                {'#',' ',' ',' ',' ','#','#'},
                {'#',' ',' ',' ',' ',' ','#'},
                {'#','#',' ','#','#','#','#'},
                {'#',' ',' ','#',' ',' ','#'},
                {'#','#','E',' ',' ',' ','#'},
                {'#','#','#','#','#','#','#'},
        };
    }
}