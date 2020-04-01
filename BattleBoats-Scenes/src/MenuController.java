import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MenuController {

    public Label msgLabel;


    public void SinglePlayerBtn(MouseEvent mouseEvent) {
        Game game = new Game();
        Scene scene = new Scene(game.createContent());
        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
    }


    public void MultiplayerBtn(MouseEvent mouseEvent) {
    }


    public void ExitBtn() {
        Platform.exit();
    }
}
