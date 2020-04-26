import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;


public class BattleboatsMain extends Application {

/*
    @FXML
    private ImageView imageView;*/

    @Override
    public void init() throws Exception {
        super.init();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Scenes/LoginPage.fxml"));
        Parent loginRoot = loader.load();
        Scene loginPage = new Scene(loginRoot, 600, 400);
        primaryStage.setTitle("Battleship");
        primaryStage.setScene(loginPage);
        primaryStage.setResizable(false);
        primaryStage.show();
        /*File file = new File("Assets/edosian.png");
        Image image = new Image(file.toURI().toString());
        imageView = new ImageView(image);*/


    }


    @Override
    public void stop() throws Exception {
        super.stop();
    }


    public static void main(String[] args) {
        launch(args);
    }


}
