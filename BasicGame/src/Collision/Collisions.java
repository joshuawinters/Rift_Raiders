package Collision;
import java.awt.Rectangle;
public class Collisions {
    /**
     * Checks if a moving object collides with any obstacles.
     *
     * @param movingObject The hitbox of the moving object.
     * @param obstacles    Array of obstacles to check against.
     * @return True if a collision is detected, false otherwise.
     */
    public boolean checkCollision(Rectangle movingObject, Rectangle[] obstacles) {
        for (Rectangle obstacle : obstacles) {
            if (movingObject.intersects(obstacle)) {
                return true;
            }
        }
        return false;
    }

}
