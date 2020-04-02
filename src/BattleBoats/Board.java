package BattleBoats;


import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private Cell[][] map; // rows, columns
    private int columns = 20;   // 10x10 is Standard Battleship Size
    private int rows = 20;

    public Board(final int rows, final int columns) {
        this.columns = columns;
        this.rows = rows;
        initializeBoard();
    }

    public void loadCellValuesFromStringRows(List<String> stringRows) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                char character = stringRows.get(i).charAt(j);
                int value = Integer.valueOf(String.valueOf(character));
                map[i][j] = new Cell(this, i + 1, j + 1, value);
            }
        }
    }

    public void initializeBoard() {
        map = new Cell[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                map[i][j] = new Cell(this, i + 1, j + 1, 0);  // No Obstructions Set to All Zero
            }
        }
    }

    public final void printBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (map[i][j].getValue() == 1)
                    System.out.print('X');

                if (map[i][j].getValue() == 0)
                    System.out.print('O');
            }
            System.out.print("\n");
        }
    }

    public final Cell getCellAtPosition(final int row, final int column) {
        if (row > rows || row < 1) {
            return null;
        }

        if (column > columns || column < 1) {
            return null;
        }

        Cell Cell = map[row - 1][column - 1];
        return Cell;
    }

    public final List<Cell> getCells() {
        ArrayList<Cell> Cells = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Cells.add(map[i][j]);
            }
        }

        return Cells;
    }

    private Cell[] getNeighbors(int x, int y) {
        Point2D[] points = new Point2D[] {
                new Point2D(x - 1, y),
                new Point2D(x + 1, y),
                new Point2D(x, y - 1),
                new Point2D(x, y + 1)
        };

        List<Cell> neighbors = new ArrayList<Cell>();

        for (Point2D p : points) {
            if (isValidPoint(p)) {
                neighbors.add(getCell((int)p.getX(), (int)p.getY()));
            }
        }

        return neighbors.toArray(new Cell[0]);
    }

    private boolean canPlaceShip(Boat boat, int x, int y) {
        int length = boat.type;

        if (boat.vertical) {
            for (int i = y; i < y + length; i++) {
                if (!isValidPoint(x, i))
                    return false;

                Cell cell = getCell(x, i);
                if (cell.boat != null)
                    return false;

                for (Cell neighbor : getNeighbors(x, i)) {
                    if (!isValidPoint(x, i))
                        return false;

                    if (neighbor.boat != null)
                        return false;
                }
            }
        }
        else {
            for (int i = x; i < x + length; i++) {
                if (!isValidPoint(i, y))
                    return false;

                Cell cell = getCell(i, y);
                if (cell.boat != null)
                    return false;

                for (Cell neighbor : getNeighbors(i, y)) {
                    if (!isValidPoint(i, y))
                        return false;

                    if (neighbor.boat != null)
                        return false;
                }
            }
        }

        return true;
    }
    private boolean isValidPoint(Point2D point) {
        return isValidPoint(point.getX(), point.getY());
    }

    private boolean isValidPoint(double x, double y) { return x >= 0 && x < rows && y >= 0 && y < columns;}

    public boolean placeBoat(Boat boat, int x, int y) {
        if (canPlaceShip(boat, x, y)) {
            int length = boat.type;

            if (boat.vertical) {
                for (int i = y; i < y + length; i++) {
                    Cell cell = getCell(x, i);
                    cell.boat = boat;
                    if (!enemy) {
                        cell.setFill(Color.WHITE);
                        cell.setStroke(Color.GREEN);
                    }
                }
            }
            else {
                for (int i = x; i < x + length; i++) {
                    Cell cell = getCell(i, y);
                    cell.boat = boat;
                    if (!enemy) {
                        cell.setFill(Color.WHITE);
                        cell.setStroke(Color.GREEN);
                    }
                }
            }

            return true;
        }

        return false;
    }



    public void shipDestroyed(){

        ChatEntry shipdestroyed = new ChatEntry(1,"Game Host","Boat Destroyed!");

    }
}
