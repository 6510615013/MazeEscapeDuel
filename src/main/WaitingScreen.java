package main;
import javax.swing.*;
import java.awt.*;

public class WaitingScreen extends JPanel {
    public WaitingScreen(JFrame frame, boolean isHost, String playerName) {
        setLayout(new BorderLayout());

        JLabel statusLabel = new JLabel("Waiting for opponent to join...", SwingConstants.CENTER);
        add(statusLabel, BorderLayout.CENTER);

        if (isHost) {
            JLabel inviteLabel = new JLabel("Invite link: localhost:5000", SwingConstants.CENTER);
            add(inviteLabel, BorderLayout.NORTH);
        }

        new Thread(() -> {
            try {
                while (NetworkHandler.in == null) Thread.sleep(100);

                while (true) {
                    String msg = NetworkHandler.receive();
                    if (msg.startsWith("SEED:")) {
                        long seed = Long.parseLong(msg.substring(5));
                        Maze maze = new Maze(21, 21, seed);

                        Player player = new Player(playerName, 19, 1, Color.RED);
                        Player opponent = new Player("Opponent", 1, 1, Color.BLUE);
                        GameGUI gui = new GameGUI(maze, player, opponent);
                        GameManager manager = new GameManager(frame, player, maze, gui);
                        gui.setManager(manager); // ⭐️ fix NPE

                        SwingUtilities.invokeLater(() -> {
                            frame.setContentPane(gui);
                            frame.pack();
                            frame.revalidate();
                            gui.requestFocusInWindow();
                        });

                        // ฟังตำแหน่งจาก server
                        new Thread(() -> {
                            try {
                                while (true) {
                                    String income = NetworkHandler.receive();
                                    if (income.startsWith("POS:")) {
                                        String[] parts = income.substring(4).split(",");
                                        int x = Integer.parseInt(parts[0]);
                                        int y = Integer.parseInt(parts[1]);
                                        manager.updateOpponent(x, y);
                                    } else if (income.startsWith("Win:")) {
                                        String winner = income.substring(4);
                                        manager.handleWin(winner);
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }).start();

                        break;
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}