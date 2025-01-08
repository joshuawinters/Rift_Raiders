package entity;

import java.awt.*;

public class Enemies extends Entity {
    public Enemies (int x, int y, int speed, long animationDelay, int width, int height) {
        super(x, y, speed, animationDelay, width, height); // Call the Entity constructor
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;
    }

    // Get hitbox based on enemy position

    }
