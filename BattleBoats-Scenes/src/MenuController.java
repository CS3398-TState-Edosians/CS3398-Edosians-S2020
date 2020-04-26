import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;

public class MenuController {

    public Label msgLabel;

    /*SinglePlayerBtn*/
    public void SinglePlayerBtn(MouseEvent mouseEvent)throws IOException {
        Game game = new Game();
        Scene scene = new Scene(game.createContent());
        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
    }

    /*ExitBtn*/
    public void ExitBtn() {
        Platform.exit();
    }


}
