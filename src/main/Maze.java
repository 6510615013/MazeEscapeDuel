package main;
import java.util.Random;

public class Maze {
    public int[][] grid;
    private final int rows, cols;
    private final Random rand = new Random();

    public Maze(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        grid = new int[rows][cols];
        generateMaze();
    }

    private void generateMaze() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = 1; // wall
            }
        }

        dfs(1,1);
    }

    private void dfs(int x, int y) {
        grid[x][y] = 0; // path
        int[][] dirs = {{0,2},{0,-2},{2,0},{-2,0}};
        shuffle(dirs);

        for (int[] d : dirs) {
            int nx = x + d[0], ny = y + d[1];
            if (inBounds(nx, ny) && grid[nx][ny] == 1) {
                grid[x + d[0]/2][y + d[1]/2] = 0;
                dfs(nx, ny);
            }
        }
    }

    private boolean inBounds(int x, int y) {
        return x > 0 && y > 0 && x < rows-1 && y < cols-1;
    }

    private void shuffle(int[][] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int[] temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}