package main;
import java.awt.*;

public class Player {
    public int x, y;
    public String name;
    public Color color;

    public Player(String name, int x, int y, Color color) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.color = color;
    }
}