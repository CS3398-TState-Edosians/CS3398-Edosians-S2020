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
    public boolean isObstructed = false;

    private Board board;

    /*Constructor*/
    public Cell(int x, int y, Board board) {
        super(30, 30);
        this.x = x;
        this.y = y;
        this.board = board;

        this.isObstructed = getRandomBoolean(2);

        if (this.isObstructed == false) {
            Image oceanImage = null;
            try {
                File file = new File("src/Assets/ClearOcean.png");
                oceanImage = new Image(new FileInputStream(file));
            } catch (FileNotFoundException e) {

            }
            setFill(new ImagePattern(oceanImage));
        }
        else {
            Image obstructionImage = null;
            try {
                File file = new File("src/Assets/Obstruction.png");
                obstructionImage = new Image(new FileInputStream(file));
            } catch (FileNotFoundException e) {

            }
            setFill(new ImagePattern(obstructionImage));
        }
        setStroke(Color.BLACK);
        setStrokeWidth(1);
    }

    static boolean getRandomBoolean(double probability) {
        double randomValue = Math.random()*100;  //0.0 to 99.9
        return randomValue <= probability;
    }

    /*shoot*/
    public boolean shoot() {
        wasShot = true;
        setFill(Color.BLACK);

        Image missOcean = null;
        try {
            File file = new File("src/Assets/MissOcean.png");
            missOcean = new Image(new FileInputStream(file));
        } catch (FileNotFoundException e) {

        }
        setFill(new ImagePattern(missOcean));

        if (ship != null) {
            ship.hit();

            Image hitImage = null;
            try {
                File file = new File("src/Assets/Hit.png");
                hitImage = new Image(new FileInputStream(file));
            } catch (FileNotFoundException e) {

            }
            setFill(new ImagePattern(hitImage));

            if (!ship.isAlive()) {
                board.ships--;
            }

            return true;
        }

        return false;
    }


}
