package GameData;

import main.RiftRaiders;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveLoad {
    RiftRaiders game;

    public SaveLoad(RiftRaiders game) {
        this.game = game;

    }
    public void save() {
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("GameData.dat"));
            GameStorage gs = new GameStorage;

            gs.level = game.player.speed;

        } catch (Exception e) {
            System.out.println("Save exception error");
        }

    }
    public void load() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("GameData.dat"));
            GameStorage game = (RiftRaiders) ois.readObject();

            game.player.level = game.level;
        }catch (Exception e) {
            System.out.println("Load exception error");
        }
    }
}
