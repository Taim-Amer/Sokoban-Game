package GUI;
import Logic.UserPlayer;
import Structure.Item;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

public class GUI extends JFrame {
    private char[][] currentLevel;
    private char[][] endstate;
    private int squareSize = 100;
    private UserPlayer userPlayer;
    public GUI(Levels level) {
        currentLevel = LevelLoader.loadLevel(level);
        endstate=LevelLoader.loadLevelFinal(level);
        userPlayer = new UserPlayer(currentLevel,endstate);
        setTitle("Sokoban Board");
        setSize(currentLevel[0].length * squareSize, currentLevel.length * squareSize);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                int newRow = userPlayer.getBunnyRow();
                int newCol = userPlayer.getBunnyColumn();

                switch (keyCode) {
                    case KeyEvent.VK_UP:
                        newRow--;
                        if (userPlayer.isValidMove(newRow, newCol)) {
                            userPlayer.moveBunny(newRow, newCol);
                            repaint();
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        newRow++;
                        if (userPlayer.isValidMove(newRow, newCol)) {
                            userPlayer.moveBunny(newRow, newCol);
                            repaint();
                        }
                        break;
                    case KeyEvent.VK_LEFT:
                        newCol--;
                        if (userPlayer.isValidMove(newRow, newCol)) {
                            userPlayer.moveBunny(newRow, newCol);
                            repaint();
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        newCol++;
                        if (userPlayer.isValidMove(newRow, newCol)) {
                            userPlayer.moveBunny(newRow, newCol);
                            repaint();
                        }
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        setFocusable(true);
        requestFocus();
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        ClassLoader classLoader = getClass().getClassLoader();
        URL wallImageURL = classLoader.getResource("GUI/Assets/lavaWall.jpg");
        assert wallImageURL != null;
        ImageIcon wallImageIcon = new ImageIcon(wallImageURL);
        Image wallImage = wallImageIcon.getImage();

        URL gressImageURL = classLoader.getResource("GUI/Assets/lavaGround.jpg");
        assert gressImageURL != null;
        ImageIcon gressImageIcon = new ImageIcon(gressImageURL);
        Image gressImage = gressImageIcon.getImage();

        URL eggImageURL = classLoader.getResource("GUI/Assets/egg3.jpg");
        assert eggImageURL != null;
        ImageIcon eggImageIcon = new ImageIcon(eggImageURL);
        Image eggImage = eggImageIcon.getImage();

        URL bunnyImageURL = classLoader.getResource("GUI/Assets/evelBunny.jpg");
        assert bunnyImageURL != null;
        ImageIcon bunnyImageIcon = new ImageIcon(bunnyImageURL);
        Image bunnyImage = bunnyImageIcon.getImage();

        URL targetImageURL = classLoader.getResource("GUI/Assets/ttar.png");
        assert targetImageURL != null;
        ImageIcon targetImageIcon = new ImageIcon(targetImageURL);
        Image targetImage = targetImageIcon.getImage();


        for (int row = 0; row < userPlayer.board.getBoardHeight(); row++) {
            for (int col = 0; col < userPlayer.board.getBoardWidth(); col++) {
                int x = col * squareSize;
                int y = row * squareSize;

                switch (userPlayer.board.getBoard()[row][col]) {
                    case Item.STONE:
                        g.drawImage(wallImage, x, y, squareSize, squareSize, this);
                        break;
                    case Item.EMPTY:
                        g.drawImage(gressImage, x, y, squareSize, squareSize, this);
                        break;
                    case Item.EGG:
                        g.drawImage(gressImage, x, y, squareSize, squareSize, this);
                        g.drawImage(eggImage, x, y, squareSize, squareSize, this);
                        break;
                    case Item.BUNNY:
                        g.drawImage(gressImage, x, y, squareSize, squareSize, this);
                        g.drawImage(bunnyImage, x, y, squareSize, squareSize, this);
                        break;
                    case Item.TARGET:
                        g.drawImage(gressImage, x, y, squareSize, squareSize, this);
                        g.drawImage(targetImage, x, y, squareSize, squareSize, this);
                        break;
                }
            }
        }
    }
}




