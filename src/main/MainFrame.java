package main;
import javax.swing.JFrame;

public class MainFrame extends JFrame{
    private static MainFrame instance = null;

    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
        }
        return instance;
    }

    private MainFrame() {
        super("Maze Escape Duel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

