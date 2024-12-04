package tiles;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class Level {
    private int[][] levelData;
    private int width;
    private int height;

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        levelData = new int[width][height];
    }

    // Get tile ID at a specific position
    public int getTile(int x, int y) {
        return levelData[x][y];
    }
    public void setTile(int x, int y, int value) {
        levelData[x][y] = value;
    }
    public void save(String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write(width + " " + height); // Write width and height first
        writer.newLine();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                writer.write(levelData[x][y] + " ");
            }
            writer.newLine();
        }
        writer.close();
    }
    public static Level load(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String[] dimensions = reader.readLine().split(" ");
        int width = Integer.parseInt(dimensions[0]);
        int height = Integer.parseInt(dimensions[1]);

        Level level = new Level(width, height);
        for (int y = 0; y < height; y++) {
            String[] row = reader.readLine().split(" ");
            for (int x = 0; x < width; x++) {
                level.setTile(x, y, Integer.parseInt(row[x]));
            }
        }
        reader.close();
        return level;
    }

}
