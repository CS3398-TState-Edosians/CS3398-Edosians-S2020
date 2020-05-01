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

    /*initialize*/
    @FXML
    public void initialize(){
        user = User.readUserFromFile();
        msgLabel.setText("Welcome "+ user.getUserName());
    }

    /*SinglePlayerBtn*/
    public void SinglePlayerBtn(MouseEvent mouseEvent)throws IOException {
        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        Game game = new Game(window);
        Scene scene = new Scene(game.createContent());
        window.setScene(scene);
    }



    /*ExitBtn*/
    public void ExitBtn() {
        Platform.exit();
    }


}
