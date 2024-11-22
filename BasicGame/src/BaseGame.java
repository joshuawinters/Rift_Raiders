import nl.saxion.app.SaxionApp;
import nl.saxion.app.interaction.GameLoop;
import nl.saxion.app.interaction.KeyboardEvent;
import nl.saxion.app.interaction.MouseEvent;


public class BaseGame implements GameLoop {

    //gameloop aanroepen en starten via main
    public static void main(String[] args) {
        SaxionApp.startGameLoop(new BaseGame(), 1000, 1000, 40);
    }

    Shafir shafir;

    @Override
    //initialing commands voor game
    //shafir start positie
    public void init() {
        shafir = new Shafir();
        shafir.x = 450;
        shafir.y = 250;
        SaxionApp.playSound("C:\\Users\\juana\\OneDrive\\Bureaublad\\IntelliJ opdrachten\\Sandbox\\BasicGame\\src\\OST\\OSTStage1.wav");
    }

    @Override
    //loop zodat de commands blijven runnen in de game
    public void loop() {
        SaxionApp.clear();

        //stage sprite inladen
        SaxionApp.drawImage(Second.imageStage, 0, 0, 1000, 600);

        //frames bijhouden voor animatiesnelheid
        shafir.frameCounter++;
        if (shafir.frameCounter >= 9) { // Wissel sprite elke 10 frames (1 stap)
            shafir.frameCounter = 0; // Reset frameCounter
            shafir.stapCounter++;
        }

        //sprites veranderen op basis van directie
        String sprite = null;
        switch (shafir.direction) {
            case "Up":
                sprite = (shafir.stapCounter % 2 == 0) ? Second.imageShafirAchterkant1 : Second.imageShafirAchterkant2;
                break;
            case "Down":
                sprite = (shafir.stapCounter % 2 == 0) ? Second.imageShafirVoorkant1 : Second.imageShafirVoorkant2;
                break;
            case "Left":
                sprite = (shafir.stapCounter % 2 == 0) ? Second.imageShafirLinks1 : Second.imageShafirLinks2;
                break;
            case "Right":
                sprite = (shafir.stapCounter % 2 == 0) ? Second.imageShafirRechts1 : Second.imageShafirRechts2;
                break;
        }
        if (sprite != null) {
            SaxionApp.drawImage(sprite, shafir.x, shafir.y, 100, 100);
        } else {
            SaxionApp.drawImage(Second.imageShafirIdle, shafir.x, shafir.y, 100, 100);

        }
    }

    @Override
    //keyboard inputs laten werken
    public void keyboardEvent(KeyboardEvent keyboardEvent) {
        if (keyboardEvent.isKeyPressed()) {
            if (keyboardEvent.getKeyCode() == KeyboardEvent.VK_A) {
                shafir.x -= 8;
                shafir.direction = "Left";
                shafir.stapCounter++;
            } else if (keyboardEvent.getKeyCode() == KeyboardEvent.VK_D) {
                shafir.x += 8;
                shafir.direction = "Right";
                shafir.stapCounter++;
            } else if (keyboardEvent.getKeyCode() == KeyboardEvent.VK_W) {
                shafir.y -= 8;
                shafir.direction = "Up";
                shafir.stapCounter++;
            } else if (keyboardEvent.getKeyCode() == KeyboardEvent.VK_S) {
                shafir.y += 8;
                shafir.direction = "Down";
                shafir.stapCounter++;
            }
        }
    }

    @Override
    public void mouseEvent(MouseEvent mouseEvent) {
    }

}

// Klasse Shafir in dezelfde file
class Shafir {
    int x;
    int y;
    String direction;
    //houdt stappen bij voor verschillende sprites
    int stapCounter;
    //houdt frames bij voor een beter stap effect
    int frameCounter;

    public Shafir() {
        this.x = 0;
        this.y = 0;
        this.direction = "Down";
        this.stapCounter = 0;
        this.frameCounter = 0;
    }
}