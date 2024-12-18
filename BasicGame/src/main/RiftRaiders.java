package main;

import GameData.SaveLoad;
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

    //eeh
    SaveLoad saveLoad = new SaveLoad(this);
    //boolean
    boolean cavemanMoves = false;
    boolean knuppelOpgepakt = false;
    boolean ShafirHeeftKnuppel = false;
    boolean hartVol = true;
    boolean ShafirSlaat = false;
    boolean cavemanSlaat = false;

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
    int slaanRefresh = 0;

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
    public boolean checkCollision(Rectangle rect1, Rectangle rect2) {
        return rect1.intersects(rect2);
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
        //Health boarder tekenen
        SaxionApp.drawImage(Second.imageHealthBoarder, 0, 0, 160, 200);


        //hart sprites toevoegen
        if (hartVol == true) {
            SaxionApp.drawImage(Second.imageHartVol1, 25, 55, 30, 30);
            SaxionApp.drawImage(Second.imageHartVol2, 65, 55, 30, 30);
            SaxionApp.drawImage(Second.imageHartVol3, 105, 55, 30, 30);
        } else if (hartVol == false) {
            SaxionApp.drawImage(Second.imageHartLeeg1, 25, 55, 30, 30);
            SaxionApp.drawImage(Second.imageHartLeeg2, 25, 55, 30, 30);
            SaxionApp.drawImage(Second.imageHartLeeg3, 25, 55, 30, 30);
        }

        //sprite inspawnen voor knuppel en boolean koppelen
        if (knuppelOpgepakt == false) {
            SaxionApp.drawImage(Second.imageKnuppel, 200, 300, 50, 50);
        } else if (slaanRefresh > 0) {
            ShafirSlaat = true;
            if (shafir.direction.equals("Left")) {
                SaxionApp.drawImage(Second.imageShafirSlagLinks, shafir.x, shafir.y, 100, 100);
            } else if (shafir.direction.equals("Right")) {
                SaxionApp.drawImage(Second.imageShafirSlagRechts, shafir.x, shafir.y, 100, 100);
            } else if (shafir.direction.equals("Up")) {
                SaxionApp.drawImage(Second.imageShafirSlagBoven, shafir.x, shafir.y, 100, 100);
            } else if (shafir.direction.equals("Down")) {
                SaxionApp.drawImage(Second.imageShafirSlagOnder, shafir.x, shafir.y, 100, 100);
            }
            slaanRefresh--;
        } else {
            ShafirSlaat = false;
            String sprite2 = switch (shafir.direction) {
                case "Up" -> (shafir.stapCounter % 2 == 0) ? Second.shafirKnuppelBoven1 : Second.shafirKnuppelBoven2;
                case "Down" -> (shafir.stapCounter % 2 == 0) ? Second.shafirKnuppelOnder1 : Second.shafirKnuppelOnder2;
                case "Left" -> (shafir.stapCounter % 2 == 0) ? Second.shafirKnuppelLinks1 : Second.shafirKnuppelLinks2;
                case "Right" ->
                        (shafir.stapCounter % 2 == 0) ? Second.shafirKnuppelRechts1 : Second.shafirKnuppelRechts2;
                default -> Second.imageShafirIdle;
            };
            SaxionApp.drawImage(sprite2, shafir.x, shafir.y, 100, 100);
        }

        if (Math.abs(caveman.x - shafir.x) > 200 && Math.abs(caveman.y - shafir.y) > 200) {
            System.out.println("dichtbij genoeg");
            if (slaanRefresh > 0) {
                cavemanSlaat = true;
                if (caveman.direction.equals("Left")) {
                    SaxionApp.drawImage(Second.imageCavemanSlagLinks, caveman.x, caveman.y, 100, 100);
                } else if (caveman.direction.equals("Right")) {
                    SaxionApp.drawImage(Second.imageCavemanSlagRechts, caveman.x, caveman.y, 100, 100);
                } else if (caveman.direction.equals("Up")) {
                    SaxionApp.drawImage(Second.imageCavemanSlagAchter, caveman.x, caveman.y, 100, 100);
                } else if (caveman.direction.equals("Down")) {
                    SaxionApp.drawImage(Second.imageCavemanSlagVoor, caveman.x, caveman.y, 100, 100);
                }
            }
            slaanRefresh--;
        }

        // Check and update animation
        if (shafir.shouldUpdateAnimation()) {
            shafir.stapCounter++; // Advance animation frame
        }

        // Determine the sprite based on direction and animation frame
        if (!ShafirHeeftKnuppel) {
            String sprite = switch (shafir.direction) {
                case "Up" ->
                        (shafir.stapCounter % 2 == 0) ? Second.imageShafirAchterkant1 : Second.imageShafirAchterkant2;
                case "Down" ->
                        (shafir.stapCounter % 2 == 0) ? Second.imageShafirVoorkant1 : Second.imageShafirVoorkant2;
                case "Left" -> (shafir.stapCounter % 2 == 0) ? Second.imageShafirLinks1 : Second.imageShafirLinks2;
                case "Right" -> (shafir.stapCounter % 2 == 0) ? Second.imageShafirRechts1 : Second.imageShafirRechts2;
                default -> Second.imageShafirIdle;
            };
            // Draw the player sprite
            SaxionApp.drawImage(sprite, shafir.x, shafir.y, 100, 100);
        }

        //caveman laten stoppen
        boolean kd = moving();
        if(!kd) {
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
        }


        //enemy sprites aanpassen op de richting
        if (cavemanMoves == true) {
            String spriteCaveman = switch (caveman.direction) {
                case "Up" -> (caveman.stapCounter % 2 == 0) ? Second.imageCavemanBoven1 : Second.imageCavemanBoven2;
                case "Down" -> (caveman.stapCounter % 2 == 0) ? Second.imageCavemanOnder1 : Second.imageCavemanOnder2;
                case "Left" -> (caveman.stapCounter % 2 == 0) ? Second.imageCavemanLinks1 : Second.imageCavemanLinks2;
                case "Right" ->
                        (caveman.stapCounter % 2 == 0) ? Second.imageCavemanRechts1 : Second.imageCavemanRechts2;
                default -> Second.imageCavemanIdle;
            };
            //Draw enemy sprite
            SaxionApp.drawImage(spriteCaveman, caveman.x, caveman.y, 100, 100);
        }


        // Detect collision
        if (checkCollision(shafir.getHitbox(), caveman.getHitbox())) {
            System.out.println("Collision detected between player and enemy!");
            // Handle collision (e.g., reduce health, game over, etc.)
        }

        // Debugging: Draw hitboxes
//        SaxionApp.drawRectangle(shafir.getHitbox().x, shafir.getHitbox().y, shafir.getHitbox().width, shafir.getHitbox().height);
//        SaxionApp.drawRectangle(caveman.getHitbox().x, caveman.getHitbox().y, caveman.getHitbox().width, caveman.getHitbox().height);

        // Draw static hitbox

        int hitboxWidth = 100;
        int hitboxHeight = 100;
        int centerX = 1000 / 2 - hitboxWidth / 2;
        int centerY = 1000 / 2 - hitboxHeight / 2;
        staticHitbox = new Rectangle(centerX, centerY, hitboxWidth, hitboxHeight);
        //SaxionApp.drawRectangle(centerX, centerY, hitboxWidth, hitboxHeight);

        // Collision with static hitbox
        if (checkCollision(shafir.getHitbox(), staticHitbox)) {
            System.out.println("Collision detected with static object!");
            // Handle collision
        }


    }


    //caveman laten stoppen als hij in range is
    public boolean moving(){
        if(Math.abs(caveman.x - shafir.x) < 80 && Math.abs(caveman.y - shafir.y) < 80){
            return true;
        }
        return false;
    }

    @Override
    public void keyboardEvent(KeyboardEvent keyboardEvent) {
        if (currentScreen.equals("startscreen")) {
            startscreenKeyboardEvent(keyboardEvent);
        } else {
            gamescreenKeyboardEvent(keyboardEvent);
        }
    }

    //keyboard interaction voor startscreen
    public void startscreenKeyboardEvent(KeyboardEvent keyboardEvent) {
        if (keyboardEvent.isKeyPressed()) {
            if (keyboardEvent.getKeyCode() == KeyboardEvent.VK_SPACE) {
                currentScreen = "gamescreen";
            }
        }
    }

    //keyboard interactions aanmaken
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
            } else if (keyboardEvent.getKeyCode() == KeyboardEvent.VK_F) {
                //knuppel oppakken
                if ( Math.abs(shafir.x - 200) < 70 && Math.abs(shafir.y - 300) < 70) {
                    knuppelOpgepakt = true;
                    ShafirHeeftKnuppel = true;
                    cavemanMoves = true;

                    caveman.x = 450;
                    caveman.y = 200;
                }
            } else if (keyboardEvent.getKeyCode() == KeyboardEvent.VK_E) {
                ShafirSlaat = true;
                // int shafirSlaatAantalFrames = 25
                slaanRefresh = 8;
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

