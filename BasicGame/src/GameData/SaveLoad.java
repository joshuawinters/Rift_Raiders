package GameData;

import main.RiftRaiders;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class SaveLoad {
    RiftRaiders game;

    public SaveLoad(RiftRaiders game) {
        this.game = game;

    }
    public void save() {
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("GameData.ser"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public void load() {

    }
}
