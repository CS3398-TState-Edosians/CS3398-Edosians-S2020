package BattleBoats;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GameSession {

    private Board board = null;
    private Simulator simulator = null;
    private int maxGenerations = -1;

    public void startSimulation() throws IOException {
        createBoard();
        createSimulator(maxGenerations);
        runSimulation();
    }

    private void createBoard() throws IOException {
        List<String> rows = loadBoardFile();
        maxGenerations = Integer.valueOf(rows.get(20));
        board = new Board(rows.size()-1, rows.get(0).length());
        board.loadCellValuesFromStringRows(rows);

        System.out.println("Input map" + ":");
        board.printBoard();
    }

    /** Loads the given txt file and stores its every row in a String list. */
    private List<String> loadBoardFile() throws IOException {
        ArrayList<String> rows = new ArrayList<>();

        URL path = GameSession.class.getResource("startinggrid.txt");
        File f = new File(path.getFile());

        BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
        try {
            String line = bufferedReader.readLine();

            while (line != null) {
                rows.add(line);
                line = bufferedReader.readLine();
            }
        } finally {
            bufferedReader.close();
        }

        return rows;
    }

    private void createSimulator(int maxGenerations) {
        simulator = new Simulator(board);
        simulator.setMaxGenerations(maxGenerations);
    }

    private void runSimulation() {
        simulator.runSimulation();
    }


}