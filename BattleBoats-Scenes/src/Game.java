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
    private boolean isPredicting = false;
    private boolean isPredictingDirection = false;
    private Cell lasthit = null;
    private int predictionDirection;

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
                String name;
                int type = cell.ship.type;
                switch (type){
                    case 5:
                        name = "aircraft carrier";
                        break;
                    case 4:
                        name = "battleship";
                        break;
                    case 3:
                        name = "destroyer";
                        break;
                    case 2:
                        name = "submarine";
                        break;
                    default:
                        name = "cruiser";
                }
                displayMessage("You Sank an Enemy " + name +"! \n");
                displayMessage(phraseGenerator(true));
                enemyBoard.placeImage(cell.ship);
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
            if (playerBoard.placeShip(new Ship(shipsToPlace, event.getButton() == MouseButton.PRIMARY, false, cell.x, cell.y), cell.x, cell.y)) {
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

            if (enemyBoard.placeShip(new Ship(type, Math.random() < 0.5, true, x, y), x, y)) {
                type--;
            }
        }

        running = true;
    }

    /*enemyMove*/
    private void enemyMove() {
        while (enemyTurn) {
            int x;
            int y;
            int playerShips = playerBoard.ships;
            int phrasechance;
            Cell cell;
            int choice;

            if(isPredictingDirection)
            {
                x = lasthit.x;
                y = lasthit.y;
                choice = predictionDirection;
                switch(choice){
                    case 1 : x++;
                    case 2 : y++;
                    case 3 : y--;
                    case 4 : x--;
                }
                if(x>12||y>12)
                {
                    x = random.nextInt(12);
                    y = random.nextInt(12);
                }
                cell = playerBoard.getCell(x, y);
            }
            else if(isPredicting)
            {
                do
                {
                    x = lasthit.x;
                    y = lasthit.y;
                    choice = random.nextInt(4);
                    switch(choice){
                        case 1 : x++;
                        case 2 : y++;
                        case 3 : y--;
                        case 4 : x--;
                    }
                }while(x>12||y>12);

                cell = playerBoard.getCell(x, y);
                if(cell.ship != null)
                {
                    isPredictingDirection = true;
                    predictionDirection = choice;
                }
            }
            else
            {
                x = random.nextInt(12);
                y = random.nextInt(12);
                cell = playerBoard.getCell(x, y);
            }

            if (cell.wasShot)
                continue;


            if(playerBoard.ships < playerShips)
            {
                isPredicting= false;
                isPredictingDirection= false;
                String name;
                int type = cell.ship.type;
                switch (type){
                    case 5:
                        name = "aircraft carrier";
                        break;
                    case 4:
                        name = "battleship";
                        break;
                    case 3:
                        name = "destroyer";
                        break;
                    case 2:
                        name = "submarine";
                        break;
                    default:
                        name = "cruiser";
                }
                displayMessage("Enemy Sunk your " + name + "! \n");
                displayMessage(phraseGenerator(false));
            }
            if(cell.ship != null)
            {
                lasthit = cell;
                isPredicting = true;
                phrasechance = random.nextInt(3);
                if(phrasechance==3)
                {
                    displayMessage(phraseGenerator(false));
                }
            }
            else
            {
                isPredicting = false;
                isPredictingDirection = false;
            }
            if (playerBoard.ships == 0) {
                displayMessage("YOU LOSE");
                System.exit(0);
            }
        }

    }

    private String phraseGenerator(boolean isAngry){

        String phrase = null;
        int phrasenumber;

        phrasenumber = random.nextInt(5);
        if(isAngry)
        {
            switch (phrasenumber){
                case 1: phrase = "Enemy: How dare you! \n";
                case 2: phrase = "Enemy: You are a tough opponent... \n";
                case 3: phrase = "Enemy: yo win  this time. \n";
                case 4: phrase = "Enemy: Impossible! \n";
                case 5: phrase = "Enemy: Failure is unacceptable. \n";
            }
        }
        else
        {
            switch (phrasenumber){
                case 1: phrase = "Enemy: Another perfect shot. \n";
                case 2: phrase = "Enemy: Too Easy. \n";
                case 3: phrase = "Enemy: I've got you now. \n";
                case 4: phrase = "Enemy: Tough luck... \n";
                case 5: phrase = "Enemy: I am the battle boat master! \n";
            }
        }
        return phrase;

    }

    public void displayMessage(String message)
    {
        this.chatBox.appendText(message);
    }


}
