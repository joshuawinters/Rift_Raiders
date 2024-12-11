package entity;
import java.awt.Rectangle;

public class Entity {

    public int x; // Position X
    public int y; // Position Y
    public int speed = 8; // Movement speed
    public String direction; // Current direction (Up, Down, Left, Right)
    public int stapCounter; // Counter for animation steps


    protected long animationTime; // Last animation update time
    protected long animationDelay; // Delay between animation frames

    //hitbox
    public int width; // Width of the entity
    public int height; // Height of the entity

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
    public Rectangle getHitbox() {}
        //begin hierrrrrrr/

        // public boolean shouldUpdateAnimation(); {
        // long currentTime = System.currentTimeMillis();
        //if (currentTime - this.animationTime >= this.animationDelay) {
        //this.animationTime = currentTime;
        //return true;
        //} else {
        //      return false;
        // }
        //}

        // public void setStaticHitBox(Rectangle staticHitbox) { this.staticHitbox = staticHitbox; }
       // public boolean shouldUpdateAnimation () {
          //  long currentTime = System.currentTimeMillis();
           // if (currentTime - this.animationTime >= this.animationDelay) {
              //  this.animationTime = currentTime;
              //  return true;
          //  } else {
             //   return false;



      //  public void setStaticHitBox (Rectangle staticHitbox){
          //  this.staticHitbox = staticHitbox;


