package entity;

import main.RiftRaiders;

import java.awt.*;

public class Player extends Entity {


    public Player(int x,int y,int speed,int animationDelay,int width,int height) {
        super(x, y, speed, animationDelay, width, height);// Call the Entity constructor
        //super(0, 0, 10, 100, 50, 50);
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;
    }
    public void SetDefaultStats() {
        level = 1;
        Maxhealth = 3;
        strenght = 1;
        dexterity = 1;
        xp = 0;
        nexlevelxp = 5;
        coin = 0;
        // functions die attack vermenigvuldigen met de attack waarde van wapen
        // attack = getattackvalue();
        // defense = getdefensevalue();


    }

}

