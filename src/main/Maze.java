package main;
import java.util.Random;

public class Maze {
    public int[][] grid;
    public final int rows, cols;
    public int exitX, exitY;

    public Maze() {
        this(21, 21, System.currentTimeMillis());
    }

    public Maze(int rows, int cols, long seed) {
        this.rows = rows;
        this.cols = cols;
        grid = new int[rows][cols];
        generateMaze(seed);
        placeExit();
    }

    private void generateMaze(long seed) {
        Random rand = new Random(seed);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                grid[i][j] = 1;
        dfs(1, 1, rand);
    }

    private void dfs(int x, int y, Random rand) {
        grid[x][y] = 0;
        int[][] dirs = {{0, 2}, {0, -2}, {2, 0}, {-2, 0}};
        shuffle(dirs, rand);
        for (int[] d : dirs) {
            int nx = x + d[0], ny = y + d[1];
            if (inBounds(nx, ny) && grid[nx][ny] == 1) {
                grid[x + d[0] / 2][y + d[1] / 2] = 0;
                dfs(nx, ny, rand);
            }
        }
    }

    private void shuffle(int[][] arr, Random rand) {
        for (int i = arr.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int[] temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    private boolean inBounds(int x, int y) {
        return x > 0 && y > 0 && x < rows - 1 && y < cols - 1;
    }

    private void placeExit() {
        exitX = cols / 2;
        exitY = rows - 2;
        grid[exitY][exitX] = 0;
    }
}