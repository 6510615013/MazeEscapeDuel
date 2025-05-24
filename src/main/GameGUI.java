package main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameGUI extends JPanel {
    private final Maze maze;
    private final Player player;

    private final int tileSize = 30;

    public GameGUI() {
        this.maze = new Maze(21, 21);
        this.player = new Player("Player", 1, 1, Color.BLUE);

        setPreferredSize(new Dimension(maze.grid[0].length * tileSize, maze.grid.length * tileSize));
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int dx = 0, dy = 0;
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W: dy = -1; break;
                    case KeyEvent.VK_S: dy = 1; break;
                    case KeyEvent.VK_A: dx = -1; break;
                    case KeyEvent.VK_D: dx = 1; break;
                }
                int nx = player.x + dx, ny = player.y + dy;
                if (maze.grid[ny][nx] == 0) {
                    player.x = nx;
                    player.y = ny;
                    repaint();
                }
            }
        });
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int y = 0; y < maze.grid.length; y++) {
            for (int x = 0; x < maze.grid[0].length; x++) {
                g.setColor(maze.grid[y][x] == 1 ? Color.BLACK : Color.WHITE);
                g.fillRect(x * tileSize, y * tileSize, tileSize, tileSize);
            }
        }
        g.setColor(player.color);
        g.fillOval(player.x * tileSize, player.y * tileSize, tileSize, tileSize);
    }
}