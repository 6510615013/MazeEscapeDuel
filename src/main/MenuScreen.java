package main;
import javax.swing.*;
import java.awt.*;
//import java.awt.event.*;

public class MenuScreen extends JPanel {
    public MenuScreen(JFrame frame) {
        setLayout(new GridLayout(4, 1));

        JTextField nameField = new JTextField();
        JButton hostBtn = new JButton("Host Game");
        JButton joinBtn = new JButton("Join Game");

        add(new JLabel("Enter your name:"));
        add(nameField);
        add(hostBtn);
        add(joinBtn);

        hostBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (!name.isEmpty()) {
                Server server = new Server(5000);  // Fixed port
                new Thread(server).start();
                frame.setContentPane(new WaitingScreen(frame, true, name));
                frame.revalidate();
            }
        });

        joinBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String ip = JOptionPane.showInputDialog("Enter server IP:");
            if (!name.isEmpty() && ip != null) {
                Client client = new Client(ip, 5000, name);
                new Thread(client).start();
                frame.setContentPane(new WaitingScreen(frame, false, name));
                frame.revalidate();
            }
        });
    }
}