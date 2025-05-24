package main;
import javax.swing.*;

public class GameManager {
    private final JFrame frame;
    private final Player player;
    private final Maze maze;
    private final GameGUI gui;

    public GameManager(JFrame frame, Player player, Maze maze, GameGUI gui) {
        this.frame = frame;
        this.player = player;
        this.maze = maze;
        this.gui = gui;
    }

    public void sendPosition(int x, int y) {
        NetworkHandler.send("POS:" + x + "," + y);
    }

    public void updateOpponent(int x, int y) {
        gui.updateOpponentPosition(x, y);
    }

    // public void handleWin(String winnerName) {
    //     NetworkHandler.send("WIN:" + winnerName);
    //     int opt = JOptionPane.showConfirmDialog(null,
    //             winnerName + " won! Try again?", "Victory", JOptionPane.YES_NO_OPTION);
    //     if (opt == JOptionPane.YES_OPTION) {
    //         NetworkHandler.send("RESTART_REQUEST");
    //     } else {
    //         NetworkHandler.send("EXIT");
    //         System.exit(0);
    //     }
    // }

    public void sendWin() {
        NetworkHandler.send("Win:" + player.name);
    }

    public void handleWin(String winnerName) {
        boolean isWinner = winnerName.equals(player.name);
        String message = isWinner ? "You win! Play again?" : "You lose! Play again?";
        int opt = JOptionPane.showConfirmDialog(null, message, "Game Over", JOptionPane.YES_NO_OPTION);
        if (opt == JOptionPane.YES_OPTION) {
            NetworkHandler.send("RESTART_REQUEST");
        } else {
            NetworkHandler.send("EXIT");
            System.exit(0);
        }
    }
}