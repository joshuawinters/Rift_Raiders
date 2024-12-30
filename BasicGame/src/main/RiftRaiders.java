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
    static int screenWidth = 1000; // Screen width in pixels
    static int screenHeight = 1000; // Screen height in pixels
    public int maxScreencol = 16;
    public int maxScreenrow = 12;


    //boolean
    boolean cavemanMoves = false;
    boolean knuppelOpgepakt = false;
    boolean ShafirHeeftKnuppel = false;
    boolean hartVol = true;
    boolean ShafirSlaat = false;
    boolean cavemanSlaat = false;
    boolean cavemanHit = false;
    boolean cavemanInRange = false;
    boolean charHIt = false;
    boolean deathAnimation = false;
    boolean shafirLeeft = true;
    boolean gameOver = false;
    boolean gameOverscreen = false;

    // tiles en level
    TileManager tileM;
    mainUI ui = new mainUI();
    //gameloop aanroepen en starten via main
    public static void main(String[] args) {
        SaxionApp.startGameLoop(new RiftRaiders(), screenWidth, screenHeight, 40);
    }

    String currentScreen = "startscreen";

    Player shafir;
    Enemies caveman;
    Rectangle staticHitbox;
    int x_knuppel = 200;
    int y_knuppel = 300;
    int slaanRefresh = 0;
    int slaanRefreshCaveman = 15;
    int damageRefreshChar = 5;
    int heartsFrameCounter = 6;
    int shafirStijgSpeed = 7;
    int gameOverDelay = 60; //voor de gameover screen

    @Override
    public void init() {
        // Initialize Player with position, speed, and animation delay
        shafir = new Player(0, 280, 8, 200, 50, 50);
        caveman = new Enemies(450, 200, 8, 200, 50, 50);
        tileM = new TileManager(this, tileWidth, tileHeight);
    }

    //game resetten bij death
    public void resetGame() {
        //posities resetten
        shafir = new Player(0, 280, 8, 200, 50, 50);
        caveman = new Enemies(450, 200, 8, 200, 50, 50);
        tileM = new TileManager(this, tileWidth, tileHeight);

        //booleans resetten
        cavemanMoves = false;
        knuppelOpgepakt = false;
        ShafirHeeftKnuppel = false;
        hartVol = true;
        ShafirSlaat = false;
        cavemanSlaat = false;
        cavemanHit = false;
        cavemanInRange = false;
        charHIt = false;
        deathAnimation = false;
        shafirLeeft = true;
        gameOver = false;
        gameOverscreen = false;

        //counters resetten
        x_knuppel = 200;
        y_knuppel = 300;
        slaanRefresh = 0;
        slaanRefreshCaveman = 15;
        damageRefreshChar = 5;
        heartsFrameCounter = 6;
        shafirStijgSpeed = 7;
        gameOverDelay = 60;
    }

    @Override
    public void loop() {
        if (currentScreen.equals("startscreen")) {
            ui.startscreenLoop();
        } else if (gameOverDelay == 0) {
            gameOverscreenloop();
            gameOverscreen = true;
        } else {
            gamescreenLoop();
        }
    }


    public boolean checkCollision(Rectangle rect1, Rectangle rect2) {
        return rect1.intersects(rect2);
    }

    //game over screen
    public void gameOverscreenloop() {
        SaxionApp.clear();
        SaxionApp.drawImage(Second.imageGameoverScreen, 0, 0, 1000, 700);
    }


    public void gamescreenLoop() {
        SaxionApp.clear();
        // Draw stage background
        // TODO: make someting to choose level
        // draw tiles before player
        // TODO: make function to load level
        // TODO: make file to create level
        // TODO: each level has a starting posistion, and end position/condition
        // TODO: clear main file and put everything in classes
        // draw_level();

        //Stage sprite tekenen
        SaxionApp.drawImage(Second.imageStage, 0, 0, 1000, 600);
        //Health boarder tekenen
        SaxionApp.drawImage(Second.imageHealthBoarder, 0, 0, 160, 200);


        //hart sprites toevoegen
        if (heartsFrameCounter > 0) {
            SaxionApp.drawImage(Second.imageHartVol1, 25, 55, 30, 30);
        }else {
            SaxionApp.drawImage(Second.imageHartLeeg1, 25, 55, 30, 30);
        }
        if (heartsFrameCounter > 2) {
            SaxionApp.drawImage(Second.imageHartVol2, 65, 55, 30, 30);
        }else {
            SaxionApp.drawImage(Second.imageHartLeeg2, 65, 55, 30, 30);
        }
        if (heartsFrameCounter > 4) {
            SaxionApp.drawImage(Second.imageHartVol3, 105, 55, 30, 30);
        }else {
            SaxionApp.drawImage(Second.imageHartLeeg3, 105, 55, 30, 30);
        }

        //game over animations
        if (heartsFrameCounter == 0) {
            gameOver = true;
            deathAnimation = true;
            SaxionApp.drawImage(Second.imageCavemanIdle, caveman.x, caveman.y, 100, 100);
            shafirLeeft = false;
            if (!shafirLeeft) {
                shafir.y -= shafirStijgSpeed;
            }
            // Begin aftellen van de delay
            if (gameOverDelay > 0) {
                gameOverDelay--; // Verlaag de delay-timer
            }
        }



        //sprite inspawnen voor knuppel en boolean koppelen
        if (!knuppelOpgepakt) {
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
            cavemanHit = true;
            slaanRefresh--;
        } else if (heartsFrameCounter > 0) {
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
        if (shafirLeeft) {
            boolean kd = moving();
            if (!kd) {
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
        }


        //caveman laten slaan in range
        if (knuppelOpgepakt && heartsFrameCounter > 0) {
            if (Math.abs(caveman.x - shafir.x) < 80 && Math.abs(caveman.y - shafir.y) < 80) {
                cavemanInRange = true;
                if (cavemanSlaat) {
                    if (caveman.direction.equals("Left")) {
                        SaxionApp.drawImage(Second.imageCavemanSlagLinks, caveman.x, caveman.y, 100, 100);
                    } else if (caveman.direction.equals("Right")) {
                        SaxionApp.drawImage(Second.imageCavemanSlagRechts, caveman.x, caveman.y, 100, 100);
                    } else if (caveman.direction.equals("Up")) {
                        SaxionApp.drawImage(Second.imageCavemanSlagAchter, caveman.x, caveman.y, 100, 100);
                    } else if (caveman.direction.equals("Down")) {
                        SaxionApp.drawImage(Second.imageCavemanSlagVoor, caveman.x, caveman.y, 100, 100);
                    }
                    charHIt = true;
                    hartVol = false;
                } else {
                    if (caveman.direction.equals("Left")) {
                        SaxionApp.drawImage(Second.imageCavemanLinks1, caveman.x, caveman.y, 100, 100);
                    } else if (caveman.direction.equals("Right")) {
                        SaxionApp.drawImage(Second.imageCavemanRechts1, caveman.x, caveman.y, 100, 100);
                    } else if (caveman.direction.equals("Up")) {
                        SaxionApp.drawImage(Second.imageCavemanBoven1, caveman.x, caveman.y, 100, 100);
                    } else if (caveman.direction.equals("Down")) {
                        SaxionApp.drawImage(Second.imageCavemanOnder1, caveman.x, caveman.y, 100, 100);
                    }
                }

                // bijhouden slaan niet slaan van enemy
                if (slaanRefreshCaveman <= 0) {
                    slaanRefreshCaveman = 15;
                    heartsFrameCounter--;
                    if (cavemanSlaat) {
                        cavemanSlaat = false;
                    } else {
                        cavemanSlaat = true;
                    }
                }
                slaanRefreshCaveman--;
            } else {
                cavemanSlaat = false;
                cavemanMoves = true;
                String spriteCaveman = switch (caveman.direction) {
                    case "Up" -> (caveman.stapCounter % 2 == 0) ? Second.imageCavemanBoven1 : Second.imageCavemanBoven2;
                    case "Down" ->
                            (caveman.stapCounter % 2 == 0) ? Second.imageCavemanOnder1 : Second.imageCavemanOnder2;
                    case "Left" ->
                            (caveman.stapCounter % 2 == 0) ? Second.imageCavemanLinks1 : Second.imageCavemanLinks2;
                    case "Right" ->
                            (caveman.stapCounter % 2 == 0) ? Second.imageCavemanRechts1 : Second.imageCavemanRechts2;
                    default -> Second.imageCavemanIdle;
                };
                //Draw enemy sprite
                SaxionApp.drawImage(spriteCaveman, caveman.x, caveman.y, 100, 100);
            }
        }


        //hit indicator aanmaken
        if (knuppelOpgepakt && heartsFrameCounter > 0) {
            if (charHIt && damageRefreshChar > 0) {
                if (shafir.direction.equals("Left")) {
                    SaxionApp.drawImage(Second.imageShafirDamageLinks, shafir.x, shafir.y, 100, 100);
                } else if (shafir.direction.equals("Right")) {
                    SaxionApp.drawImage(Second.imageShafirDamageRechts, shafir.x, shafir.y, 100, 100);
                } else if (shafir.direction.equals("Up")) {
                    SaxionApp.drawImage(Second.imageShafirDamageAchter, shafir.x, shafir.y, 100, 100);
                } else if (shafir.direction.equals("Down")) {
                    SaxionApp.drawImage(Second.imageShafirDamageVoor, shafir.x, shafir.y, 100, 100);
                }

                if (charHIt) {
                    charHIt = false;
                    damageRefreshChar = 5;
                }

                damageRefreshChar--;
            } else if (damageRefreshChar < 0) {
                String sprite2 = switch (shafir.direction) {
                    case "Up" ->
                            (shafir.stapCounter % 2 == 0) ? Second.shafirKnuppelBoven1 : Second.shafirKnuppelBoven2;
                    case "Down" ->
                            (shafir.stapCounter % 2 == 0) ? Second.shafirKnuppelOnder1 : Second.shafirKnuppelOnder2;
                    case "Left" ->
                            (shafir.stapCounter % 2 == 0) ? Second.shafirKnuppelLinks1 : Second.shafirKnuppelLinks2;
                    case "Right" ->
                            (shafir.stapCounter % 2 == 0) ? Second.shafirKnuppelRechts1 : Second.shafirKnuppelRechts2;
                    default -> Second.imageShafirIdle;
                };
                SaxionApp.drawImage(sprite2, shafir.x, shafir.y, 100, 100);
            }
        }


        // Detect collision
        if (checkCollision(shafir.getHitbox(), caveman.getHitbox())) {
            System.out.println("Collision detected between player and enemy!");
            // Handle collision (e.g., reduce health, game over, etc.)
        }

        //shafir death sprites printen bij death
        if (deathAnimation) {
            if (shafir.direction.equals("Left")) {
                SaxionApp.drawImage(Second.imageShafirDeathLinks, shafir.x, shafir.y, 100, 100);
            } else if (shafir.direction.equals("Right")) {
                SaxionApp.drawImage(Second.imageShafirDeathRechts, shafir.x, shafir.y, 100, 100);
            } else if (shafir.direction.equals("Up")) {
                SaxionApp.drawImage(Second.imageShafirDeathAchter, shafir.x, shafir.y, 100, 100);
            } else if (shafir.direction.equals("Down")) {
                SaxionApp.drawImage(Second.imageShafirDeathVoor, shafir.x, shafir.y, 100, 100);
            }
        }

        // Debugging: Draw hitboxesd
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
        } else if (gameOverscreen) {
            gameOverscreenKeyboardEvent(keyboardEvent);
        } else {
            gamescreenKeyboardEvent(keyboardEvent);
        }
    }

    //keyboard interaction voor gameover screen
    public void gameOverscreenKeyboardEvent(KeyboardEvent keyboardEvent) {
        if (keyboardEvent.isKeyPressed()) {
            if (keyboardEvent.getKeyCode() == KeyboardEvent.VK_ENTER) {
                currentScreen = "startscreen";
            }
        }
    }

    //keyboard interaction voor startscreen
    public void startscreenKeyboardEvent(KeyboardEvent keyboardEvent) {
        if (keyboardEvent.isKeyPressed()) {
            if (keyboardEvent.getKeyCode() == KeyboardEvent.VK_SPACE) {
                resetGame();
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

