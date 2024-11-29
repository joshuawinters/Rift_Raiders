package main;

import entity.Enemies;
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

    String currentScreen = "startscreen";

    Player shafir;
    Enemies caveman;
    Rectangle staticHitbox;
    @Override
    public void init() {
        // Initialize Player with position, speed, and animation delay
        shafir = new Player(450, 250, 8, 200, 50, 50);
        caveman = new Enemies(450,250,8, 200, 50, 50);
        //enemy inspawnen
        SaxionApp.drawImage(Second.imageCavemanIdle, 0,280, 100, 100);

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

        //follow player (enemy)
        //positie enemy horizontaal
        if(shafir.x > caveman.x){
            caveman.x++;
        } else if(shafir.x < caveman.x) {
            caveman.x--;
        }

        //positie enemy verticaal
        if(shafir.y > caveman.y){
            caveman.y++;
        } else if(shafir.y < caveman.y) {
            caveman.y--;
        }

        //Draw enemy sprite
        SaxionApp.drawImage(Second.imageCavemanIdle, caveman.x, caveman.y,100, 100);

        // for debugging
        Rectangle hitbox = shafir.getHitbox();
        SaxionApp.drawRectangle(hitbox.x, hitbox.y, hitbox.width, hitbox.height);

        // Statische hitbox toevoegen in het midden van het scherm
        int hitboxWidth = 100;  // Breedte van de hitbox
        int hitboxHeight = 100; // Hoogte van de hitbox
        int centerX = 1000 / 2 - hitboxWidth / 2; // Midden van het scherm (x)
        int centerY = 1000 / 2 - hitboxHeight / 2; // Midden van het scherm (y)
        staticHitbox = new Rectangle(centerX, centerY, hitboxWidth, hitboxHeight);
        SaxionApp.drawRectangle(centerX, centerY, hitboxWidth, hitboxHeight);

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