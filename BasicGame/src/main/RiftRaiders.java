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
    static int screenWidth = 1500; // Screen width in pixels
    static int screenHeight = 1500; // Screen height in pixels
    public int maxScreencol = 25;
    public int maxScreenrow = 25;

    // collisions
    public CollisionChecker cChecker = new CollisionChecker(this);
    //public setGame cChecker = new setGame(this);



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
    boolean cavemanLeeft = true;
    boolean gameOver = false;
    boolean gameOverscreen = false;
    boolean shafirInRange = false;
    boolean cavemanResets = false; //caveman deaths bijhouden voor death counter
    boolean deathIsCounted = false;
    boolean bossSpawned = false;
    boolean dialoge = false;
    boolean mainBossLeeft = false;
    boolean mainBossInRange = false;
    boolean bossAttack = false;
    boolean hold = false;
    boolean doodDoorCaveman = false;
    boolean doodDoorBoss = false;
    boolean shafirDuim = false;

    // tiles en level
    TileManager tileM;
    mainUI ui = new mainUI();
    //gameloop aanroepen en starten via main
    public static void main(String[] args) {
        SaxionApp.startGameLoop(new RiftRaiders(), screenWidth, 600, 40);
    }

    String currentScreen = "startscreen";

    Enemies mainBoss;
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
    int cavemanDeathdelay = 20;
    int cavemanDeathCounter = 0;
    int dialogeCounter = 25;
    int holdCounter = 25;
    int attackCounter = 25;


    @Override
    public void init() {
        // Initialize Player with position, speed, and animation delay
        shafir = new Player(0, 280, 8, 200, 50, 50);
        caveman = new Enemies(280, 0, 8, 200, 50, 50);
        mainBoss = new Enemies(280, 0, 8, 200, 50, 50);
        tileM = new TileManager(this, tileWidth, tileHeight);
    }

    //game resetten bij death
    public void resetGame() {
        //posities resetten
        shafir = new Player(0, 280, 8, 200, 50, 50);
        caveman = new Enemies(450, 200, 8, 200, 50, 50);
        mainBoss = new Enemies(280, 0, 8, 200, 50, 50);
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
        shafirInRange = false;
        cavemanLeeft = true;
        deathIsCounted = false;

        //counters resetten
        x_knuppel = 200;
        y_knuppel = 300;
        slaanRefresh = 0;
        slaanRefreshCaveman = 15;
        damageRefreshChar = 5;
        heartsFrameCounter = 6;
        shafirStijgSpeed = 7;
        gameOverDelay = 60;
        cavemanDeathdelay = 20;
        cavemanDeathCounter = 0;
    }

    //reset aanmaken voor caveman death
    public void cavemanReset() {
        //sprite reset
        caveman = new Enemies(280, 0, 8, 200, 50, 50);

        //booleans reset
        cavemanLeeft = true;
        cavemanInRange = false;
        knuppelOpgepakt = true;
        ShafirHeeftKnuppel = true;
        cavemanSlaat = false;
        deathIsCounted = false;

        //counters
        slaanRefreshCaveman = 15;
    }

    @Override
    public void loop() {
        if (currentScreen.equals("startscreen")) {
            ui.startscreenLoop();
        } else if (gameOverDelay == 0) {
            ui.gameOverscreenloop();
            gameOverscreen = true;
        } else {
            gamescreenLoop();
        }
    }

    //collisions checken
    public boolean checkCollision(Rectangle rect1, Rectangle rect2) {
        return rect1.intersects(rect2);
    }


    public void gamescreenLoop() {
        SaxionApp.clear();
        // Draw stage background
        // TODO: make someting to choose level
        // draw tiles before player
        // TODO: make file to create level
        // TODO: each level has a starting posistion, and end position/condition
        // TODO: clear main file and put everything in classes

        //Stage sprite tekenen
        draw_level();
        //SaxionApp.drawImage(Second.imageStage, 0, 0, 1000, 600);
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
        if (heartsFrameCounter <= 0 && cavemanLeeft) {
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
        if (!ShafirHeeftKnuppel && !shafirDuim) {
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
        if (cavemanLeeft) {
            if (shafirLeeft && knuppelOpgepakt) {
                boolean kd = moving();
                if (!kd) {
                    //follow player (enemy)
                    //positie enemy horizontaal
                    if (shafir.x > caveman.x) {
                        caveman.move("Right", this);
                    } else if (shafir.x < caveman.x) {
                        caveman.move("Left",this);
                    }
                    //positie enemy verticaal
                    if (shafir.y > caveman.y) {
                        caveman.move("Down",this);
                    } else if (shafir.y < caveman.y) {
                        caveman.move("Up",this);
                    }
                    if (caveman.shouldUpdateAnimation()) {
                        caveman.stapCounter++; // Advance animation frame
                    }
                }
            }
        }


        //caveman laten slaan in range
        if (cavemanDeathCounter <= 20)
        if (cavemanLeeft) {
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
                        case "Up" ->
                                (caveman.stapCounter % 2 == 0) ? Second.imageCavemanBoven1 : Second.imageCavemanBoven2;
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

        //caveman death mogelijk maken
        if (knuppelOpgepakt) {
            if (Math.abs(shafir.x - caveman.x) < 80 && Math.abs(shafir.y - caveman.y) < 80) {
                shafirInRange = true;
                if (ShafirSlaat) {
                    cavemanLeeft = false;
                }
            }
        }
        //respawn delay aanmaken
        if (!bossSpawned && cavemanDeathdelay > 0 && !cavemanLeeft) {
            cavemanDeathdelay--;
        }
        //respawn delay resetten
        if (!bossSpawned && cavemanDeathdelay == 0) {
            cavemanReset();
            cavemanResets = true;
            cavemanDeathdelay = 20;
        }

        //shaifr emote tekenen (werkt niet goed)
        if (shafirDuim) {
            SaxionApp.drawImage(Second.imageShafirEmote, shafir.x, shafir.y, 100, 100);
        }

        //caveman death sprite tekenen
        if (!cavemanLeeft) {
            if(cavemanDeathCounter<5) {
                if (caveman.direction.equals("Left")) {
                    SaxionApp.drawImage(Second.imageCavemanDeathLinks, caveman.x, caveman.y, 100, 100);
                } else if (caveman.direction.equals("Right")) {
                    SaxionApp.drawImage(Second.imageCavemanDeathRechts, caveman.x, caveman.y, 100, 100);
                } else if (caveman.direction.equals("Up")) {
                    SaxionApp.drawImage(Second.imageCavemanDeathAchter, caveman.x, caveman.y, 100, 100);
                } else if (caveman.direction.equals("Down")) {
                    SaxionApp.drawImage(Second.imageCavemanDeathVoor, caveman.x, caveman.y, 100, 100);
                }
            }
        }

        //caveman deaths bijhouden voor boss dmv een counter
        if (!cavemanLeeft && !deathIsCounted) {
                cavemanDeathCounter++;
                System.out.println("caveman deaths: " + cavemanDeathCounter);
                deathIsCounted = true;
        }
        if (cavemanResets && cavemanDeathdelay == 0) {
            cavemanLeeft = true;
        }

        //boss laten inspawnen bij 5 kills
        if (cavemanDeathCounter >= 1 && !bossSpawned) { // Controleer of boss nog niet gespawned is
            SaxionApp.drawImage(Second.imageBossIdle, mainBoss.x, mainBoss.y, 100, 100);
            bossSpawned = true;
            mainBossLeeft = true;
            cavemanLeeft = false; // Stop caveman acties
            System.out.println("boss spawned");
        }
        //caveman spawn laten stoppen
        if (bossSpawned) {
            cavemanLeeft = false; // Zet caveman acties uit
            cavemanDeathCounter = 5; // Zorg dat de counter niet meer verandert
        }

        //blokje dialoge inspawnen
        if (bossSpawned) {
            cavemanLeeft = false;
            dialoge = true;
            dialogeCounter--;
            if (dialoge && dialogeCounter > 0) {
                SaxionApp.setFill(Color.DARK_GRAY);
                SaxionApp.setBorderColor(Color.WHITE);
                SaxionApp.drawRectangle(280, 470, 430, 75);
                SaxionApp.drawImage(Second.imageMainBHoofd, 280, 470, 130, 130);
                SaxionApp.setTextDrawingColor(Color.WHITE);
                SaxionApp.drawText("Nu ben ik klaar met jou", 420, 490, 15);
            }
        }


        if (bossSpawned && dialogeCounter <= 0 && !mainBossInRange&& !hold) {
            String sprite2 = switch (mainBoss.direction) {
                case "Up" -> (mainBoss.stapCounter % 2 == 0) ? Second.imageBStapWapenAchter1 : Second.imageBStapWapenAchter2;
                case "Down" -> (mainBoss.stapCounter % 2 == 0) ? Second.imageBStapWapenVoor1 : Second.imageBStapWapenVoor2;
                case "Left" -> (mainBoss.stapCounter % 2 == 0) ? Second.imageBStapWapenLinks1 : Second.imageBStapWapenLinks2;
                case "Right" -> (mainBoss.stapCounter % 2 == 0) ? Second.imageBStapWapenRechts1 : Second.imageBStapWapenRechts2;
                default -> Second.imageBStapOnder1;
            };
            // Draw the player sprite
            SaxionApp.drawImage(sprite2, mainBoss.x, mainBoss.y, 100, 100);
        }

        //mainBoss laten volgen
        if (bossSpawned && dialogeCounter <= 0 && !mainBossInRange && !hold) { // Check dat de dialoog is afgelopen
            // Laat de boss de speler volgen
            boolean kb = moving_boss(); // Gebruik dit voor caveman, maar we moeten hier boss movement implementeren
            if (!kb) {
                // Volg de speler (shafir)

                if (shafir.x > mainBoss.x) {
                    mainBoss.move("Right", this);
                } else if (shafir.x < mainBoss.x) {
                    mainBoss.move("Left", this);
                }

                if (shafir.y > mainBoss.y) {
                    mainBoss.move("Down", this);
                } else if (shafir.y < mainBoss.y) {
                    mainBoss.move("Up", this);
                }

                // Update de animatie van de boss
                if (mainBoss.shouldUpdateAnimation()) {
                    mainBoss.stapCounter++;
                }
            }
        }

        //mainBoss attack sprites
        if (bossSpawned && shafirLeeft) {
           if (Math.abs(mainBoss.x - shafir.x) < 80 && Math.abs(mainBoss.y - shafir.y) < 80) {
               mainBossInRange = true;
           } else{
               mainBossInRange = false;
           }
           if (mainBossInRange){
               hold = true;
           }
           if (hold && !bossAttack) {
               holdCounter--;
               if (mainBoss.direction.equals("Up")) {
                   SaxionApp.drawImage(Second.imageMainBHoldAchter, mainBoss.x, mainBoss.y, 100, 100);
               } else if (mainBoss.direction.equals("Down")) {
                   SaxionApp.drawImage(Second.imageMainBHoldVoor, mainBoss.x, mainBoss.y, 100, 100);
               } else if (mainBoss.direction.equals("Left")) {
                   SaxionApp.drawImage(Second.imageMainBHoldLinks, mainBoss.x, mainBoss.y, 100, 100);
               } else if (mainBoss.direction.equals("Right")) {
                   SaxionApp.drawImage(Second.imageMainBHoldRechts, mainBoss.x, mainBoss.y, 100, 100);
               }
           }


        }

        //mainBoss laten slaan
        if (hold && holdCounter <= 0 && attackCounter > 0) {
            bossAttack = true;
            heartsFrameCounter--;
            doodDoorBoss = true;
            attackCounter--;
            if(attackCounter >= 0) {
                if (mainBoss.direction.equals("Up")) {
                    SaxionApp.drawImage(Second.imageBossAttackAchter, mainBoss.x, mainBoss.y, 100, 100);
                } else if (mainBoss.direction.equals("Down")) {
                    SaxionApp.drawImage(Second.imageBossAttackVoor, mainBoss.x, mainBoss.y, 100, 100);
                } else if (mainBoss.direction.equals("Left")) {
                    SaxionApp.drawImage(Second.imageBossAttackLinks, mainBoss.x, mainBoss.y, 100, 100);
                } else if (mainBoss.direction.equals("Right")) {
                    SaxionApp.drawImage(Second.imageBossAttackRechts, mainBoss.x, mainBoss.y, 100, 100);
                }
            } else {
                holdCounter = 25;
                bossAttack = false;
                attackCounter = 25;
                hold = false;
            }
        }

        //death animation toevoegen
        if (heartsFrameCounter <= 0 && doodDoorBoss) {
            gameOver = true;
            deathAnimation = true;
            if (attackCounter <= 0) {
                SaxionApp.drawImage(Second.imageBossIdle, mainBoss.x, mainBoss.y, 100, 100);
            }
            shafirLeeft = false;
            if (!shafirLeeft) {
                shafir.y -= shafirStijgSpeed;
            }
            // Begin aftellen van de delay
            if (gameOverDelay > 0) {
                gameOverDelay--; // Verlaag de delay-timer
            }
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



        //shafir hitbox
        Rectangle staticHitbox = shafir.getHitbox();

        //HITBOX tekenen
        //SaxionApp.drawRectangle(staticHitbox.x, staticHitbox.y, staticHitbox.width,staticHitbox.height);

    }


    //caveman laten stoppen als hij in range is
    public boolean moving(){
        if(Math.abs(caveman.x - shafir.x) < 80 && Math.abs(caveman.y - shafir.y) < 80){
            return true;
        }
        return false;
    }
    //caveman laten stoppen als hij in range is
    public boolean moving_boss(){
        if(Math.abs(mainBoss.x - shafir.x) < 80 && Math.abs(mainBoss.y - shafir.y) < 80){
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
                shafir.move("Left",this);
            } else if (keyboardEvent.getKeyCode() == KeyboardEvent.VK_D) {
                shafir.move("Right",this);
            } else if (keyboardEvent.getKeyCode() == KeyboardEvent.VK_W) {
                shafir.move("Up",this);
            } else if (keyboardEvent.getKeyCode() == KeyboardEvent.VK_S) {
                shafir.move("Down",this);
            } else if (keyboardEvent.getKeyCode() == KeyboardEvent.VK_F) {
                //knuppel oppakken
                if ( Math.abs(shafir.x - 200) < 70 && Math.abs(shafir.y - 300) < 70) {
                    knuppelOpgepakt = true;
                    ShafirHeeftKnuppel = true;
                    cavemanMoves = true;

                    caveman.x = 380;
                    caveman.y = 0;
                }
            } else if (keyboardEvent.getKeyCode() == KeyboardEvent.VK_E) {
                ShafirSlaat = true;
                // int shafirSlaatAantalFrames = 25
                slaanRefresh = 8;
            } else if (keyboardEvent.getKeyCode() == KeyboardEvent.VK_V) {
                shafirDuim = true;
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