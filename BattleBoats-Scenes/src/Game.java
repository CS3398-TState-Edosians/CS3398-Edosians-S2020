import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.Random;
public class Game {

    private boolean running = false;
    private Board enemyBoard, playerBoard;
    private TextArea chatBox;
    private int shipsToPlace = 5;
    private boolean enemyTurn = false;
    private Random random = new Random();
    private Stage window;

    public Game(Stage window){this.window = window;}
    private boolean isPredicting = false;
    private boolean isPredictingDirection = false;
    private Cell lastSpotted = null;
    private int predictionDirection;

    /*createContent*/
    public Parent createContent() {
        BorderPane root = new BorderPane();
        root.setPrefSize(600, 100);
        chatBox = new TextArea();
        chatBox.setEditable(false);

        enemyBoard = new Board(true, event -> {
            if (!running)
                return;

            Cell cell = (Cell) event.getSource();
            int enemyShips = enemyBoard.ships;
            if (cell.wasShot)
                return;
            cell.shoot();
            enemyTurn = true;

            if(enemyBoard.ships<enemyShips)
            {
                String name;
                int type = cell.ship.type;
                switch (type) {
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

                displayMessage("You Sank an Enemy " + name +"!");
                enemyBoard.placeImage(cell.ship);
            }
            if (enemyBoard.ships == 0) {
                displayMessage("YOU WIN");
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Scenes/WinScreen.fxml"));
                Parent MenuRoot;
                try {
                    MenuRoot = loader.load();
                    Scene MenuPage = new Scene(MenuRoot, 600, 400);
                    window.setScene(MenuPage);
                } catch (IOException e) {
                    e.printStackTrace();
                }

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

            if (isPredictingDirection) {
                do {
                    x = lastSpotted.x;
                    y = lastSpotted.y;
                    choice = predictionDirection;
                    switch (choice) {
                        case 0:
                            x++;
                            break;
                        case 1:
                            y++;
                            break;
                        case 2:
                            y--;
                            break;
                        case 3:
                            x--;
                            break;
                    }
                    if (x >= 12 || y >= 12) {
                        switch (predictionDirection) {
                            case 0:
                                predictionDirection = 3;
                                break;
                            case 1:
                                predictionDirection = 2;
                                break;
                            case 2:
                                predictionDirection = 1;
                                break;
                            case 3:
                                predictionDirection = 0;
                                break;
                        }
                    }
                } while (x >= 12 || y >= 12);


                cell = playerBoard.getCell(x, y);
            } else if (isPredicting) {
                do {
                    x = lastSpotted.x;
                    y = lastSpotted.y;
                    choice = random.nextInt(3);
                    switch (choice) {
                        case 0:
                            x++;
                            break;
                        case 1:
                            y++;
                            break;
                        case 2:
                            y--;
                            break;
                        case 3:
                            x--;
                            break;
                    }
                } while (x >= 12 || y >= 12);

                cell = playerBoard.getCell(x, y);
                if (cell.ship != null) {
                    isPredictingDirection = true;
                    predictionDirection = choice;
                }
            } else {
                x = random.nextInt(12);
                y = random.nextInt(12);
                cell = playerBoard.getCell(x, y);
            }

            if (cell.ship != null) {
                lastSpotted = cell;
            }

            if (cell.wasShot) {
                continue;
            }
            else{
                enemyTurn = false;
            }
            cell.shoot();

            if (playerBoard.ships < playerShips) {
                isPredicting = false;
                isPredictingDirection = false;
                String name;
                int type = cell.ship.type;
                switch (type) {
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
            } else if (cell.ship != null) {
                isPredicting = true;
                phrasechance = random.nextInt(3);
                if (phrasechance == 3) {
                    displayMessage(phraseGenerator(false));
                }
            } else {
                    isPredicting = false;
                    isPredictingDirection = false;
                }
                if (playerBoard.ships == 0) {
                    displayMessage("YOU LOSE");
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("Scenes/LoseScreen.fxml"));
                    Parent MenuRoot;
                    try {
                        MenuRoot = loader.load();
                        Scene MenuPage = new Scene(MenuRoot, 600, 400);
                        window.setScene(MenuPage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        }
    }

        private String phraseGenerator(boolean isAngry){

            String phrase = null;
            int phrasenumber;

            phrasenumber = random.nextInt(7);
            if (isAngry) {
                switch (phrasenumber) {
                    case 1:
                        phrase = "Enemy: How dare you! \n";
                        break;
                    case 2:
                        phrase = "Enemy: You are a tough opponent... \n";
                        break;
                    case 3:
                        phrase = "Enemy: You win  this time. \n";
                        break;
                    case 4:
                        phrase = "Enemy: Impossible! \n";
                        break;
                    case 5:
                        phrase = "Enemy: Failure is unacceptable. \n";
                        break;
                    case 6:
                        phrase = "Enemy: Slick Move, Salior. \n";
                        break;
                    case 7:
                        phrase = "Enemy: Moby Dick!. \n";
                        break;
                }
            } else {
                switch (phrasenumber) {
                    case 1:
                        phrase = "Enemy: Another perfect shot. \n";
                        break;
                    case 2:
                        phrase = "Enemy: Too Easy. \n";
                        break;
                    case 3:
                        phrase = "Enemy: I've got you now. \n";
                        break;
                    case 4:
                        phrase = "Enemy: Tough luck... \n";
                        break;
                    case 5:
                        phrase = "Enemy: I am the battle boat master! \n";
                        break;
                    case 6:
                        phrase = "Enemy: Loser. \n";
                        break;
                    case 7:
                        phrase = "Enemy: Nice try, Not! \n";
                        break;
                }
            }
            return phrase;

        }

        public void displayMessage (String message)
        {
            this.chatBox.appendText(message);
        }


    }
