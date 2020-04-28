import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class WinScreen {
    public void BackToMenu(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Scenes/Menu.fxml"));
        Parent MenuRoot = loader.load();



        //get current Stage
        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();

        //set the menu page
        Scene MenuPage = new Scene(MenuRoot, 600, 400);
        window.setScene(MenuPage);
    }

    public void Exit(MouseEvent mouseEvent) {
        Platform.exit();
    }
}
