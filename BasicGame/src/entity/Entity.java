package entity;
import main.RiftRaiders;
import nl.saxion.app.SaxionApp;
import main.Second;

import java.awt.*;

public class Entity{

    RiftRaiders game;

    // collisions
    public boolean collisionOn = false;
    // place and speed
    public int x; // Position X
    public int y; // Position Y
    public Rectangle solidArea;

    public String direction; // Current direction (Up, Down, Left, Right)
    public int stapCounter; // Counter for animation steps

    protected long animationTime; // Last animation update time
    protected long animationDelay; // Delay between animation frames

    //hitbox
    public int width; // Width of the entity
    public int height; // Height of the entity

    //player atributes
    public int health = 3; // Aantal hartjes
    public boolean hartVol = true;
    public int speed=8; // Movement speed
    public int Maxhealth;
    public int strenght;
    public int attack;
    public int dexterity;
    public int defense;
    public int xp;
    public int level;
    public int nexlevelxp;
    public int coin;
    public int attackValue;
    public int defenseValue;




    public Entity(int x, int y, int speed, long animationDelay, int width, int height) {
        this.x = x;
        this.y = y;
        this.direction = "IDLE";
        this.stapCounter = 0;
        this.speed = speed;
        this.animationTime = System.currentTimeMillis(); // Starttijd
        // this sets fps i think
        this.animationDelay = animationDelay;
        // hit box
        this.width = width;
        this.height = height;

    }
    // hit box
    public Rectangle getHitbox() {
        int hitboxX = x + solidArea.x*4;
        int hitboxY = y + solidArea.y*4;
        int hitboxWidth = solidArea.width;
        int hitboxHeight = solidArea.height;
        return new Rectangle(hitboxX, hitboxY, hitboxWidth, hitboxHeight);
    }

    /*
    possible extra hitbox
    currently not used
    public Rectangle getReach() {
        //putting hitbox in the middle of character
        // dit werkt kut!!
        int hitboxX = x ; // Offset to center the hitbox horizontally
        int hitboxY = y ; // Offset to center the hitbox vertically
        int hitboxWidth = width * 2;   // Hitbox is half the sprite's width
        int hitboxHeight = height * 2; // Hitbox is half the sprite's height
        return new Rectangle(hitboxX, hitboxY, hitboxWidth, hitboxHeight);
    }
    */


    public void move(String direction, RiftRaiders game) {
        this.game = game;
        this.direction = direction;
            //check colision
            collisionOn = false;
            game.cChecker.CheckTile(this);

            // if collision false move
            if(collisionOn == false) {
                switch (direction) {
                    case "Up":
                        y -= speed;
                        break;
                    case "Down":
                        y += speed;
                        break;
                    case "Left":
                        x -= speed;
                        break;
                    case "Right":
                        x += speed;
                        break;
                }
            }
    }

    public boolean shouldUpdateAnimation() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - animationTime >= animationDelay) {
            animationTime = currentTime;
            return true;
        }
        return false;
    }
}
