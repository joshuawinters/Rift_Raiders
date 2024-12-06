package entity;
import java.awt.Rectangle;

public class Entity {

    public int x; // Position X
    public int y; // Position Y
    public int speed = 8; // Movement speed
    public String direction; // Current direction (Up, Down, Left, Right)
    public int stapCounter; // Counter for animation steps
    public Rectangle solidArea;
    public boolean collisionOn = false;

    protected long animationTime; // Last animation update time
    protected long animationDelay; // Delay between animation frames

    //hitbox
    public int width; // Width of the entity
    public int height; // Height of the entity
    Rectangle staticHitbox;

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
        int hitboxX = this.x + this.width * 4 / 5;
        int hitboxY = this.y + this.height;
        int hitboxWidth = this.width / 2;
        int hitboxHeight = this.height / 2;
        return new Rectangle(hitboxX, hitboxY, hitboxWidth, hitboxHeight);
    }

    public void move(String direction) {
        this.direction = direction;
        int xRock = this.staticHitbox.x;
        int yRock = this.staticHitbox.y;
        int widthRock = this.staticHitbox.width;
        int heightRock = this.staticHitbox.height;
        int margin = 10;
        switch (direction) {
            case "Up":
                if (this.y - this.speed >= yRock + heightRock / 2 + margin || this.y <= yRock || this.x + widthRock / 2 <= xRock - margin || this.x >= xRock + widthRock / 2 + margin) {
                    this.y -= this.speed;
                }
                break;
            case "Down":
                if (this.y + this.speed <= yRock - heightRock / 2 - margin || this.y + this.speed >= yRock + heightRock / 2 + margin || this.x <= xRock - widthRock / 2 - margin || this.x >= xRock + widthRock / 2 + margin) {
                    this.y += this.speed;
                }
                break;
            case "Left":
                if (this.x - this.speed >= xRock + widthRock / 2 + margin || this.x <= xRock || this.y + heightRock / 2 <= yRock - margin || this.y >= yRock + heightRock / 2 + margin) {
                    this.x -= this.speed;
                }
                break;
            case "Right":
                if (this.x + this.speed <= xRock - widthRock / 2 - margin || this.x + this.speed >= xRock + widthRock / 2 + margin || this.y <= yRock - heightRock / 2 - margin || this.y >= yRock + heightRock / 2 + margin) {
                    this.x += this.speed;
                }
        }

        public boolean shouldUpdateAnimation() {
            long currentTime = System.currentTimeMillis();
            if (currentTime - this.animationTime >= this.animationDelay) {
                this.animationTime = currentTime;
                return true;
            } else {
                return false;
            }
        }

        public void setStaticHitBox(Rectangle staticHitbox) { this.staticHitbox = staticHitbox; }
    }
}
