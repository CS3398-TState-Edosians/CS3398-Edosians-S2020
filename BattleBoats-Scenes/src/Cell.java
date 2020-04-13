import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Cell extends Rectangle {

    public int x, y;
    public Ship ship = null;
    public boolean wasShot = false;

    private Board board;

    /*Constructor*/
    public Cell(int x, int y, Board board) {
        super(30, 30);
        this.x = x;
        this.y = y;
        this.board = board;
        Image oceanImage = null;
        try {
            File file = new File("src/Assets/ClearOcean.png");
            oceanImage = new Image(new FileInputStream(file));
        } catch (FileNotFoundException e) {

        }
        setFill(new ImagePattern(oceanImage));
        //setFill(Color.LIGHTGRAY);
        setStroke(Color.BLACK);
        setStrokeWidth(1);
    }

    /*shoot*/
    public boolean shoot() {
        wasShot = true;
        setFill(Color.BLACK);

        if (ship != null) {
            ship.hit();
            setFill(Color.RED);
            if (!ship.isAlive()) {
                board.ships--;
            }

            return true;
        }

        return false;
    }


}
