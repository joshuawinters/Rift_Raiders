package main;
import nl.saxion.app.SaxionApp;

public class mainUI {
    public void startscreenLoop() {
        // Schoon het scherm en teken de startscreen-afbeelding
        SaxionApp.clear();
        SaxionApp.drawImage("Sprites/RRstartscreen.png", 0, 0, 1000, 700);
    }

//    //gameover screen aanmaken
//    public void gameOverscreenLoop() {
//        SaxionApp.clear();
//        SaxionApp.drawImage("Sprites/gameOverScreen.png", 0, 0, 1000, 700);
//    }
  }
