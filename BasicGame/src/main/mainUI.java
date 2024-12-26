package main;
import entity.Player;
import nl.saxion.app.SaxionApp;

import static main.RiftRaiders.screenHeight;
import static main.RiftRaiders.screenWidth;

public class mainUI {

    // Camera position variables
    int worldx = 0;
    int worldy = 270;

    // Method to draw the start screen
    public void startscreenLoop() {
        SaxionApp.clear();
        SaxionApp.drawImage("Sprites/RRstartscreen.png", 0, 0, 1000, 700);
    }

    // Update the camera position to center around the player
    public void updateCamera(Player shafir) {
        // Center the camera based on the player's position
        worldx = shafir.x - screenWidth / 2;
        worldy = shafir.y - screenHeight / 2;

        // Ensure the camera stays within bounds of the world (assuming worldWidth and worldHeight are defined)
        int worldWidth = 5000;  // Example world width
        int worldHeight = 5000; // Example world height

        worldx = Math.max(0, Math.min(worldx, worldWidth - screenWidth));
        worldy = Math.max(0, Math.min(worldy, worldHeight - screenHeight));
    }
}

