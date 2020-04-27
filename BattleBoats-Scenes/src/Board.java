import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import java.io.File;

public class Board extends Parent {

    private Pane pane = new Pane();
    private VBox rows = new VBox();
    private boolean enemy = false;
    public int ships = 5;

    /*Constructor*/
    public Board(boolean enemy, EventHandler<? super MouseEvent> handler) {
        this.enemy = enemy;

        for (int y = 0; y < 12; y++) {
            HBox row = new HBox();
            for (int x = 0; x < 12; x++) {
                Cell c = new Cell(x, y, this);
                c.setOnMouseClicked(handler);
                row.getChildren().add(c);
            }

            rows.getChildren().add(row);
        }
        getChildren().add(pane);
        pane.getChildren().add(rows);
    }

    /*placeShip*/
    public boolean placeShip(Ship ship, int x, int y) {
        if (canPlaceShip(ship, x, y)) {
            int length = ship.len;

            if (ship.vertical) {
                for (int i = y; i < y + length; i++) {
                    Cell cell = getCell(x, i);
                    cell.ship = ship;
                }
                if (!enemy) {
                    placeImage(ship);
                }
            }
            else {
                for (int i = x; i < x + length; i++) {
                    Cell cell = getCell(i, y);
                    cell.ship = ship;
                }
                if (!enemy){
                    placeImage(ship);
                }
            }

            return true;
        }

        return false;
    }

    /*placeImage*/
    public void placeImage(Ship ship) {
        final ImageView imageView = new ImageView();
        Image boatImg = null;
        try {
            File file = new File("src/Assets/"+ship.name);
            boatImg = new Image(new FileInputStream(file));
        } catch (FileNotFoundException e) {

        }
        imageView.setImage(boatImg);
        if (ship.vertical) {
            imageView.setY(31 * ((double) ship.y + ship.len / 2.0) - 15);
            imageView.setX(31 * ((double) ship.x - ship.len / 2.0) + 15);
            imageView.setFitHeight(30);
            imageView.setFitWidth(30 * ship.len);
            imageView.setRotate(90);
        }
        else {
            imageView.setFitHeight(30);
            imageView.setFitWidth(30 * ship.len);
            imageView.setY(31 * ship.y);
            imageView.setX(31 * ship.x);
        }
        pane.getChildren().add(imageView);
    }

    /*getCell*/
    public Cell getCell(int x, int y) {
        return (Cell)((HBox)rows.getChildren().get(y)).getChildren().get(x);
    }

    /*getNeighbors*/
    private Cell[] getNeighbors(int x, int y) {
        Point2D[] points = new Point2D[] {
                new Point2D(x,y)
        };

        List<Cell> neighbors = new ArrayList<Cell>();

        for (Point2D p : points) {
            if (isValidPoint(p)) {
                neighbors.add(getCell((int)p.getX(), (int)p.getY()));
            }
        }

        return neighbors.toArray(new Cell[0]);
    }

    /*canPlaceShip*/
    private boolean canPlaceShip(Ship ship, int x, int y) {
        int length = ship.len;

        if (ship.vertical) {
            for (int i = y; i < y + length; i++) {
                if (!isValidPoint(x, i))
                    return false;

                Cell cell = getCell(x, i);
                if ((cell.ship != null) || (cell.isObstructed == true))
                    return false;

                for (Cell neighbor : getNeighbors(x, i)) {
                    if (!isValidPoint(x, i))
                        return false;

                    if ((neighbor.ship != null) || (neighbor.isObstructed == true))
                        return false;
                }
            }
        }
        else {
            for (int i = x; i < x + length; i++) {
                if (!isValidPoint(i, y))
                    return false;

                Cell cell = getCell(i, y);
                if ((cell.ship != null) || (cell.isObstructed == true))
                    return false;

                for (Cell neighbor : getNeighbors(i, y)) {
                    if (!isValidPoint(i, y))
                        return false;

                    if ((neighbor.ship != null) || (neighbor.isObstructed == true))
                        return false;
                }
            }
        }

        return true;
    }

    /*isValidPoint*/
    private boolean isValidPoint(Point2D point) {
        return isValidPoint(point.getX(), point.getY());
    }

    /*isValidPoint*/
    private boolean isValidPoint(double x, double y) {
        return x >= 0 && x < 12 && y >= 0 && y < 12;
    }


}