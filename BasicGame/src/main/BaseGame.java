package main;

import entity.Player;
import nl.saxion.app.SaxionApp;
import nl.saxion.app.interaction.GameLoop;
import nl.saxion.app.interaction.KeyboardEvent;
import nl.saxion.app.interaction.MouseEvent;
import tiles.TileManager;
import tiles.Level;

import java.awt.*;
import java.io.IOException;


public class BaseGame implements GameLoop {
    //set size
    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    final int maxScreencol = 16;
    final int maxScreenrow = 16;
    final int screenwidth = maxScreencol * tileSize;
    final int screenheight = maxScreenrow * tileSize;

    // tiles en levels
    TileManager tileM = new TileManager(this);
    Level currentLevel; // Add the current level


    //gameloop aanroepen en starten via main
    public static void main(String[] args) {
        SaxionApp.startGameLoop(new BaseGame(), 1000, 1000, 40);
    }

    Player shafir;
    Rectangle staticHitbox;
    @Override
    public void init() {
        //load level
        try {
            // Load a level from a file (example level1.txt)
            currentLevel = Level.load("levels/level1.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Initialize Player with position, speed, and animation delay
        shafir = new Player(450, 250, 8, 200, 50, 50);
    }

    @Override
    public void loop() {
        SaxionApp.clear();

        // Draw stage background
        SaxionApp.drawImage(Second.imageStage, 0, 0, 1000, 600);
        //paintScreen();
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
        //System.out.println("Tile image path: " + tile[0].image);
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


    public void paintScreen() {

        if (tileM != null) {
            tileM.drawTiles();
        }

    }

}