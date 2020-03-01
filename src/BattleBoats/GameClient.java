package BattleBoats;

import javafx.application.Application;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Scene;
        import javafx.scene.layout.GridPane;
        import javafx.stage.Stage;

        import java.util.LinkedList;

public class GameClient extends Application {
    Controller controller1;
    Controller controller2;
    //Controller controller3;
    LinkedList<ChatEntry> chatTranscript = new LinkedList<ChatEntry>();
    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("GameClient.fxml"));
        GridPane grid1 = loader1.load();
        Stage gameClient1 = new Stage();
        Scene scene1 = new Scene(grid1);
        controller1 = loader1.getController();
        controller1.setChatTranscript(chatTranscript);
        gameClient1.setTitle("BattleBoat Player 1");
        gameClient1.setScene(scene1);
        gameClient1.setResizable(false);
        gameClient1.show();

        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("GameClient.fxml"));
        GridPane grid2 = loader2.load();
        Stage gameClient2 = new Stage();
        Scene scene2 = new Scene(grid2);
        controller2 = loader2.getController();
        controller2.setChatTranscript(chatTranscript);
        gameClient2.setTitle("CBattleBoat Player 2");
        gameClient2.setScene(scene2);
        gameClient2.setResizable(false);
        gameClient2.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
