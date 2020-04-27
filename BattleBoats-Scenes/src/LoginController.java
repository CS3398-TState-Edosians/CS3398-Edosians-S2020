import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.fxml.FXML;

import java.io.IOException;

public class LoginController {

    public TextField username;
    public static String userStr;

    /*onPlayEvent*/
    public void onPlayEvent(MouseEvent mouseEvent) throws IOException {
        userStr = username.getCharacters().toString();

        if(userStr.isEmpty()) {
            /*TODO*/
        }
        else{
            User user = new User(userStr);
            user.writeUserToInfoFile();

            //load menu page
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Scenes/Menu.fxml"));
            Parent MenuRoot = loader.load();


            //get current Stage
            Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();

            //set the menu page
            Scene MenuPage = new Scene(MenuRoot, 600, 400);
            window.setScene(MenuPage);
        }

    }


}
