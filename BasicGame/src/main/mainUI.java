package main;

import nl.saxion.app.SaxionApp;

public class mainUI {
    public void startscreenLoop() {
        // Schoon het scherm en teken de startscreen-afbeelding
        SaxionApp.clear();
        SaxionApp.drawImage("Sprites/RRstartscreen.png", 0, 0, 1200, 576);
    }

    public void gameOverscreenloop() {
        SaxionApp.clear();
        SaxionApp.drawImage(Second.imageGameoverScreen, 0, 0, 1200, 576);
    }
    public void GameCompleted(){
        SaxionApp.clear();

        SaxionApp.drawImage(Second.imageCompletedScreen, 0, 0, 1200, 576);
    }
}

