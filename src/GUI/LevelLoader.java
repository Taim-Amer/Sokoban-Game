package GUI;

public class LevelLoader {
    public static char[][] loadLevel(Levels level) {
        return level.getInitialBoard();
    }
    public static char[][] loadLevelFinal(Levels level) {
        return level.getFinalBoard();
    }

}
