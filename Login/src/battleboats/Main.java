package battleboats;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("resources/LoginPage.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Parent loginRoot = loader.load();
        primaryStage.setTitle("BattleBoats");
        primaryStage.setScene(new Scene(loginRoot, 600, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
