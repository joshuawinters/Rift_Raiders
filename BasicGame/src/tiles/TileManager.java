package tiles;

import main.BaseGame;
import nl.saxion.app.SaxionApp;


public class TileManager {
    BaseGame game;
    Tiles[] tile;


    public TileManager(BaseGame game) {
        this.game = game;
        // number of tiles
        tile = new Tiles[10];
        GetTileImage();

    }
    public void GetTileImage() {
        try {

            tile[0] = new Tiles();
            tile[0].image = "Sprites/Tiles/New version (with numbers)/005.png";

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void drawTiles() {
        //d
        SaxionApp.drawImage(tile[0].image, 0, 0, 64, 64);
    }

}
