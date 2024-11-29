package entity;
import entity.Player;
import entity.Entity;
import main.BaseGame;

public class Enemies extends Entity {
    public Enemies (int x, int y, int speed, long animationDelay, int width, int height) {
        super(x, y, speed, animationDelay, width, height); // Call the Entity constructor

    }

}
