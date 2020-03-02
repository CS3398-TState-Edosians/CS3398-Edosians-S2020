package BattleBoats;


import java.util.ArrayList;
import java.util.List;

public class Board {

    private Cell[][] map; // rows, columns
    private int columns = 0;   // 10x10 is Standard Battleship Size
    private int rows = 0;

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

}
