import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginController {

    public TextField username = null;
    public static String userStr;
    public void onPlayEvent(MouseEvent mouseEvent) throws IOException {

         userStr = username.getCharacters().toString();

        if(!userStr.isEmpty()) {

            //load menu page
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Scenes/Menu.fxml"));
            Parent loginRoot = loader.load();
            MenuController cn = loader.getController();
            cn.msgLabel.setText("welcome "+ userStr);
            Scene MenuPage = new Scene(loginRoot, 600, 400);

            //get current Stage
            Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            //set the menu page
            window.setScene(MenuPage);
        }
    }
}
