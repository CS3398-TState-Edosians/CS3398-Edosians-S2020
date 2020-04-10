import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextArea;
import java.awt.*;
import java.util.Random;


public class Game {

    private boolean running = false;
    private Board enemyBoard, playerBoard;
    private TextArea chatBox;
    private int shipsToPlace = 5;
    private boolean enemyTurn = false;
    private Random random = new Random();

    /*createContent*/
    public Parent createContent() {
        BorderPane root = new BorderPane();
        root.setPrefSize(600, 100);
        chatBox = new TextArea();

        enemyBoard = new Board(true, event -> {
            if (!running)
                return;

            Cell cell = (Cell) event.getSource();
            int enemyShips = enemyBoard.ships;
            if (cell.wasShot)
                return;

            enemyTurn = !cell.shoot();

            if(enemyBoard.ships<enemyShips)
            {
                displayMessage("You Sank an Enemy Ship! \n");
            }
            if (enemyBoard.ships == 0) {
                displayMessage("YOU WIN");
                System.exit(0);
            }

            if (enemyTurn)
                enemyMove();

        });

        playerBoard = new Board(false, event -> {
            if (running)
                return;

            Cell cell = (Cell) event.getSource();
            if (playerBoard.placeShip(new Ship(shipsToPlace, event.getButton() == MouseButton.PRIMARY, false), cell.x, cell.y)) {
                if (--shipsToPlace == 0) {
                    startGame();
                }
            }

        });

        VBox vbox = new VBox(40, enemyBoard, playerBoard);
        vbox.setAlignment(Pos.TOP_LEFT);
        root.setLeft(vbox);
        root.setRight(chatBox);
        return root;
    }

    /*startGame*/
    private void startGame() {
        int type = 5;

        // place enemy ships
        while (type > 0) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            if (enemyBoard.placeShip(new Ship(type, Math.random() < 0.5, true), x, y)) {
                type--;
            }
        }

        running = true;
    }

    /*enemyMove*/
    private void enemyMove() {
        while (enemyTurn) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);
            int playerShips = playerBoard.ships;

            Cell cell = playerBoard.getCell(x, y);
            if (cell.wasShot)
                continue;

            enemyTurn = cell.shoot();

            if(playerBoard.ships < playerShips)
            {
                displayMessage("Enemy Sunk a Ship! \n");
            }
            if (playerBoard.ships == 0) {
                displayMessage("YOU LOSE");
                System.exit(0);
            }
        }

    }

    public void displayMessage(String message)
    {
        this.chatBox.appendText(message);
    }


}
