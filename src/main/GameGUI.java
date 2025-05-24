package main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameGUI extends JPanel {
    private final Maze maze;
    private final Player player;
    private final Player opponent;
    private final int tileSize = 30;
    private GameManager manager;

    public GameGUI(Maze maze, Player player, Player opponent) {
        this.maze = maze;
        this.player = player;
        this.opponent = opponent;

        setPreferredSize(new Dimension(maze.cols * tileSize, maze.rows * tileSize));
        setFocusable(true);
        requestFocusInWindow();

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int dx = 0, dy = 0;
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W, KeyEvent.VK_UP: dy = -1; break;
                    case KeyEvent.VK_S, KeyEvent.VK_DOWN: dy = 1; break;
                    case KeyEvent.VK_A, KeyEvent.VK_LEFT: dx = -1; break;
                    case KeyEvent.VK_D, KeyEvent.VK_RIGHT: dx = 1; break;
                }
                int nx = player.x + dx, ny = player.y + dy;
                if (maze.grid[ny][nx] == 0) {
                    player.x = nx;
                    player.y = ny;
                    if (manager != null) {
                        manager.sendPosition(nx, ny);
                        if (nx == maze.exitX && ny == maze.exitY) {
                            manager.sendWin();
                        }
                    }
                    repaint();
                }
            }
        });
    }

    public void setManager(GameManager manager) {
        this.manager = manager;
    }

    public void updateOpponentPosition(int x, int y) {
        opponent.x = x;
        opponent.y = y;
        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int y = 0; y < maze.rows; y++) {
            for (int x = 0; x < maze.cols; x++) {
                g.setColor(maze.grid[y][x] == 1 ? Color.BLACK : Color.WHITE);
                g.fillRect(x * tileSize, y * tileSize, tileSize, tileSize);
            }
        }
        g.setColor(Color.GREEN);
        g.fillRect(maze.exitX * tileSize, maze.exitY * tileSize, tileSize, tileSize);

        g.setColor(opponent.color);
        g.fillOval(opponent.x * tileSize, opponent.y * tileSize, tileSize, tileSize);

        g.setColor(player.color);
        g.fillOval(player.x * tileSize, player.y * tileSize, tileSize, tileSize);
    }
}