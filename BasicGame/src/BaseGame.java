import entity.Player;
import nl.saxion.app.SaxionApp;
import nl.saxion.app.interaction.GameLoop;
import nl.saxion.app.interaction.KeyboardEvent;
import nl.saxion.app.interaction.MouseEvent;

import java.awt.*;


public class BaseGame implements GameLoop {
    //gameloop aanroepen en starten via main
    public static void main(String[] args) {
        SaxionApp.startGameLoop(new BaseGame(), 1000, 1000, 40);
    }

    Player shafir;
    Rectangle staticHitbox;
    @Override
    public void init() {
        // Initialize Player with position, speed, and animation delay
        shafir = new Player(450, 250, 8, 200, 50, 50);
        // Statische hitbox toevoegen in het midden van het scherm
        int hitboxWidth = 100;  // Breedte van de hitbox
        int hitboxHeight = 100; // Hoogte van de hitbox
        int centerX = 1000 / 2 - hitboxWidth / 2; // Midden van het scherm (x)
        int centerY = 1000 / 2 - hitboxHeight / 2; // Midden van het scherm (y)
        staticHitbox = new Rectangle(centerX, centerY, hitboxWidth, hitboxHeight);
    }

    @Override
    public void loop() {
        SaxionApp.clear();

        // Draw stage background
        SaxionApp.drawImage(Second.imageStage, 0, 0, 1000, 600);

        // Check and update animation
        if (shafir.shouldUpdateAnimation()) {
            shafir.stapCounter++; // Advance animation frame
        }

        // Determine the sprite based on direction and animation frame
        String sprite = switch (shafir.direction) {
            case "Up" -> (shafir.stapCounter % 2 == 0) ? Second.imageShafirAchterkant1 : Second.imageShafirAchterkant2;
            case "Down" -> (shafir.stapCounter % 2 == 0) ? Second.imageShafirVoorkant1 : Second.imageShafirVoorkant2;
            case "Left" -> (shafir.stapCounter % 2 == 0) ? Second.imageShafirLinks1 : Second.imageShafirLinks2;
            case "Right" -> (shafir.stapCounter % 2 == 0) ? Second.imageShafirRechts1 : Second.imageShafirRechts2;
            default -> Second.imageShafirIdle;
        };

        // Draw the player sprite
        SaxionApp.drawImage(sprite, shafir.x, shafir.y, 100, 100);

        // for debugging
        Rectangle hitbox = shafir.getHitbox();
        SaxionApp.drawRectangle(hitbox.x, hitbox.y, hitbox.width, hitbox.height);


    }

    @Override
    public void keyboardEvent(KeyboardEvent keyboardEvent) {
        if (keyboardEvent.isKeyPressed()) {
            if (keyboardEvent.getKeyCode() == KeyboardEvent.VK_A) {
                shafir.move("Left");
            } else if (keyboardEvent.getKeyCode() == KeyboardEvent.VK_D) {
                shafir.move("Right");
            } else if (keyboardEvent.getKeyCode() == KeyboardEvent.VK_W) {
                shafir.move("Up");
            } else if (keyboardEvent.getKeyCode() == KeyboardEvent.VK_S) {
                shafir.move("Down");
            }
        }
    }

    @Override
    public void mouseEvent(MouseEvent mouseEvent) {
        // Handle mouse events if necessary
    }

}