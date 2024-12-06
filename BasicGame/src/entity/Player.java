package entity;

import main.BaseGame;

import java.awt.*;

public class Player extends Entity {
    public Player(int x, int y, int speed, long animationDelay, int width, int height) {
        super(x, y, speed, animationDelay, width, height); // Call the Entity constructor
    }

    public Rectangle getHitbox() {
        return new Rectangle(x, y, width, height);
    }
}

