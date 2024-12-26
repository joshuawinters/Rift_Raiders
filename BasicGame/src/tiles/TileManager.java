package tiles;

import main.RiftRaiders;
import main.RiftRaiders;
import nl.saxion.app.SaxionApp;

import java.awt.*;
import java.io.*;


public class TileManager {
    RiftRaiders game;
    Tiles[] tile;
    int tileWidth;  // Width of a single tile
    int tileHeight; // Height of a single tile
    final int num_tiles = 37;
    int mapTileNumber[][];
    Rectangle staticHitbox;

    public TileManager(RiftRaiders game, int tileWidth, int tileHeight) {
        this.game = game;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        // number of tiles
        tile = new Tiles[num_tiles];
        mapTileNumber = new int[game.maxScreencol][game.maxScreenrow];

        GetTileImage();
        loadmap();
    }
    /*
    public boolean moving2(){
        //check all the tiles in a radius of 3 tiles
        // return the collision status true or false
        // check if player is within ... number pixels of that tile, if so stop movement that direction


        for (int i = 0; i < ; i++) {

        }

    }

    */
    public void static_hitbox(int x,int y){
        int hitboxWidth = tileWidth;
        int hitboxHeight = tileHeight;
        //int hitboxWidth = 100;
        //int hitboxHeight = 100;
        int centerX = x;
        int centerY = y;
        staticHitbox = new Rectangle(centerX, centerY, hitboxWidth, hitboxHeight);
        SaxionApp.drawRectangle(centerX, centerY, hitboxWidth, hitboxHeight);
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
                tile[i- 1].collision = false;
            }
            // this code for special tiles
            tile[15].collision = true;
            tile[16].collision = true;
            tile[17].collision = true;
            tile[18].collision = true;
            /*
            tile[0] = new Tiles();
            tile[0].image = "Sprites/Tiles/New version (with numbers)/017.png";
            hitbox= true

            tile[1] = new Tiles();
            tile[1].image = "Sprites/Tiles/New version (with numbers)/016.png";
            */
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void loadmap(){
        try{

            InputStream is = new FileInputStream("Sprites/maps/main_map");
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
            if(tile[tileNum].collision){
                static_hitbox(x,y);
            }
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
