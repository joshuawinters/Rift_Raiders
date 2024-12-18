package entity;

import main.RiftRaiders;

import java.awt.*;

public class Player extends Entity {
    public Player(int x, int y, int speed, long animationDelay, int width, int height) {
        super(x, y, speed, animationDelay, width, height);// Call the Entity constructor

    }
    public void SetDefaultStats() {
        level = 1;
        Maxhealth = 3;
        strenght = 1;
        dexterity = 1;
        xp = 0;
        nexlevelxp = 5;
        coin = 0;

    }
}

