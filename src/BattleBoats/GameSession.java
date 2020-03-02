package BattleBoats;

import java.io.IOException;

public class GameSession {

    private Board board = null;
    private Simulator simulator = null;


    public void startSimulation() throws IOException {
        createBoard();
    }

    private void createBoard() throws IOException {

        board = new Board(10, 10);
        //board.printBoard();  <= Should Auto Post to JFX
    }

}