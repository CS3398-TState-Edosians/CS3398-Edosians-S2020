import javafx.application.Platform;
import javafx.fxml.FXML;
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
    public User user;

    @FXML
    public void initialize(){
        user = User.readUserFromFile();
        msgLabel.setText("Welcome "+ user.getUserName());
    }

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
