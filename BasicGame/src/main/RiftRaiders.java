package main;

import entity.Enemies;
import entity.Player;
import nl.saxion.app.SaxionApp;
import nl.saxion.app.interaction.GameLoop;
import nl.saxion.app.interaction.KeyboardEvent;
import nl.saxion.app.interaction.MouseEvent;
import tiles.TileManager;

import java.awt.*;


public class RiftRaiders implements GameLoop {
    // set parameters
    // Define tile size and screen dimensions
    final int tileWidth = 48;  // Width of each tile
    final int tileHeight = 48; // Height of each tile
    public final int screenWidth = 1000; // Screen width in pixels
    public final int screenHeight = 1000; // Screen height in pixels
    public int maxScreencol = 16;
    public int maxScreenrow = 12;

    //boolean
    boolean cavemanMoves = false;
    boolean knuppelOpgepakt = false;

    // tiles en level
    TileManager tileM;

    //gameloop aanroepen en starten via main
    public static void main(String[] args) {
        SaxionApp.startGameLoop(new RiftRaiders(), 1000, 1000, 40);
    }

    String currentScreen = "startscreen";

    Player shafir;
    Enemies caveman;
    Rectangle staticHitbox;
    int x_knuppel = 200;
    int y_knuppel = 300;

    @Override
    public void init() {
        // Initialize Player with position, speed, and animation delay
        shafir = new Player(0, 280, 8, 200, 50, 50);
        caveman = new Enemies(450, 200, 8, 200, 50, 50);
        tileM = new TileManager(this, tileWidth, tileHeight);

    }

    @Override
    public void loop() {
        if (currentScreen.equals("startscreen")) {
            startscreenLoop();
        } else {
            gamescreenLoop();
        }
    }

    //Start screen invoegen
    public void startscreenLoop() {
        SaxionApp.clear();
        SaxionApp.drawImage("Sprites/RRstartscreen.png", 0, 0, 1000, 700);
    }

    public void gamescreenLoop() {
        SaxionApp.clear();

        // Draw stage background
        // TODO: make someting to choose level
        // draw tiles before player
        // TODO: make function to load level
        // TODO: make file to create level
        // TODO: each level has a starting posistion, and end position/condition
        //draw_level();

        //Stage sprite tekenen
        SaxionApp.drawImage(Second.imageStage, 0, 0, 1000, 600);

        if (knuppelOpgepakt == false) {
            SaxionApp.drawImage(Second.imageKnuppel, 200, 300, 50, 50);
        }

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
        if (shafir.x > caveman.x) {
            caveman.move("Right");
        } else if (shafir.x < caveman.x) {
            caveman.move("Left");
        }

        //positie enemy verticaal
        if (shafir.y > caveman.y) {
            caveman.move("Down");
        } else if (shafir.y < caveman.y) {
            caveman.move("Up");
        }


        if (caveman.shouldUpdateAnimation()) {
            caveman.stapCounter++; // Advance animation frame
        }


        //enemy sprites aanpassen op de richting
        String spriteCaveman = switch (caveman.direction) {
            case "Up" -> (caveman.stapCounter % 2 == 0) ? Second.imageCavemanBoven1 : Second.imageCavemanBoven2;
            case "Down" -> (caveman.stapCounter % 2 == 0) ? Second.imageCavemanOnder1 : Second.imageCavemanOnder2;
            case "Left" -> (caveman.stapCounter % 2 == 0) ? Second.imageCavemanLinks1 : Second.imageCavemanLinks2;
            case "Right" -> (caveman.stapCounter % 2 == 0) ? Second.imageCavemanRechts1 : Second.imageCavemanRechts2;
            default -> Second.imageCavemanIdle;
        };

        //Draw enemy sprite
        SaxionApp.drawImage(spriteCaveman, caveman.x, caveman.y, 100, 100);


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
        if (currentScreen.equals("startscreen")) {
            startscreenKeyboardEvent(keyboardEvent);
        } else {
            gamescreenKeyboardEvent(keyboardEvent);
        }
    }

    public void startscreenKeyboardEvent(KeyboardEvent keyboardEvent) {
        if (keyboardEvent.isKeyPressed()) {
            if (keyboardEvent.getKeyCode() == KeyboardEvent.VK_SPACE) {
                currentScreen = "gamescreen";
            }
        }
    }

    public void gamescreenKeyboardEvent(KeyboardEvent keyboardEvent) {
        if (keyboardEvent.isKeyPressed()) {
            if (keyboardEvent.getKeyCode() == KeyboardEvent.VK_A) {
                shafir.move("Left");
            } else if (keyboardEvent.getKeyCode() == KeyboardEvent.VK_D) {
                shafir.move("Right");
            } else if (keyboardEvent.getKeyCode() == KeyboardEvent.VK_W) {
                shafir.move("Up");
            } else if (keyboardEvent.getKeyCode() == KeyboardEvent.VK_S) {
                shafir.move("Down");
            } else if (keyboardEvent.getKeyCode() == KeyboardEvent.VK_E) {

                //knuppel oppakken
                if ( Math.abs(shafir.x - 200) < 50 && Math.abs(shafir.y - 300) < 50) {
                    knuppelOpgepakt = true;
                }
            }

        }
    }

    @Override
    public void mouseEvent(MouseEvent mouseEvent) {
        // Handle mouse events if necessary
    }

    public void draw_level(){
        if (tileM != null) {
            tileM.drawTiles();
        }
    }

}

