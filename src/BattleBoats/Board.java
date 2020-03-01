package BattleBoats;


import java.util.ArrayList;
import java.util.List;

public class Board {

    private Soul[][] map; // rows, columns
    private int columns = 0;
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
                map[i][j] = new Soul(this, i + 1, j + 1, value);
            }
        }
    }

    public void initializeBoard() {
        map = new Soul[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                map[i][j] = new Soul(this, i + 1, j + 1, 0);
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

    public final Soul getSoulAtPosition(final int row, final int column) {
        if (row > rows || row < 1) {
            return null;
        }

        if (column > columns || column < 1) {
            return null;
        }

        Soul soul = map[row - 1][column - 1];
        return soul;
    }

    public final List<Soul> getSouls() {
        ArrayList<Soul> souls = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                souls.add(map[i][j]);
            }
        }

        return souls;
    }

}
