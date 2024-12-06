package tiles;

import main.BaseGame;
import nl.saxion.app.SaxionApp;

import java.io.*;


public class TileManager {
    BaseGame game;
    Tiles[] tile;
    int tileWidth;  // Width of a single tile
    int tileHeight; // Height of a single tile
    final int num_tiles = 37;
    int mapTileNumber[][];

    public TileManager(BaseGame game, int tileWidth, int tileHeight) {
        this.game = game;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        // number of tiles
        tile = new Tiles[num_tiles];
        mapTileNumber = new int[game.maxScreencol][game.maxScreenrow];

        GetTileImage();
        loadmap();
    }
    public void GetTileImage() {
        try {

            // Loop through the tile numbers (001 to @variable num_tiles)
            for (int i = 1; i <= num_tiles; i++) {
                tile[i - 1] = new Tiles(); // Initialize each tile object

                // Format the number to 3 digits with leading zeros (e.g., "001", "002", ..., "010")
                String formattedNumber = String.format("%03d", i);

                // Set the image path dynamically
                tile[i - 1].image = "Sprites/Tiles/New version (with numbers)/" + formattedNumber + ".png";
            }
            // this code for special tiles
            /*
            tile[0] = new Tiles();
            tile[0].image = "Sprites/Tiles/New version (with numbers)/017.png";

            tile[1] = new Tiles();
            tile[1].image = "Sprites/Tiles/New version (with numbers)/016.png";
            */
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void loadmap(){
        try{

            InputStream is = new FileInputStream("Sprites/maps/map_doubletest");
            if (is == null) {
                throw new RuntimeException("Map file not found. Ensure the file is in the correct path!");
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            // read file
            while(col < game.maxScreencol && row < game.maxScreenrow){
                String line = br.readLine();
                String numbers[] = line.split(" ");
                // split numbers into array
                while(col < game.maxScreencol){
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNumber[col][row] = num;
                    col++;
                }
                if(col== game.maxScreencol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void drawTiles() {
        //d
        //SaxionApp.drawImage(tile[0].image, 0, 0, tileWidth, tileHeight);
        int col =0;
        int row = 0;
        int x = 0;
        int y = 0;
        while (col < game.maxScreencol && row < game.maxScreenrow) {
            int tileNum = mapTileNumber[col][row];

            SaxionApp.drawImage(tile[tileNum].image, x, y, tileWidth, tileHeight);
            col++;
            x += tileWidth;

            if(col== game.maxScreencol){
                col = 0;
                row++;
                x=0;
                y+=tileHeight;
            }

        }
    }

}
