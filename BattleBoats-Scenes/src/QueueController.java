import javafx.fxml.FXML;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;


public class QueueController {

    public Label QueueTimeLabel;
    public Button QueueStartBtn;
    public Button QueueStopBtn;

    private double elapsedTime = 0.0;
    private boolean isQueueRunning = false;
    private Timeline timeline;
    StringProperty DisplayedValue;

    /*initialize*/
    @FXML
    public void initialize(){
        timeline = new Timeline(new KeyFrame(Duration.millis(1000),ae-> updateTime()));
        DisplayedValue = new SimpleStringProperty(getCurrentTimeString());
        QueueTimeLabel.textProperty().bind(DisplayedValue);
        QueueStopBtn.setDisable(true); //disable stop button
    }

    /*QueueStartTime*/
    public void QueueStartTime(ActionEvent actionEvent) {
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        updateBtnState();
    }

    /*QueueStopTime*/
    public void QueueStopTime(ActionEvent actionEvent) {
        timeline.stop();
        resetTime();
        updateBtnState();
    }

    /*updateTime*/
    private void updateTime(){
        elapsedTime++;
        DisplayedValue.setValue(getCurrentTimeString());
    }

    /*resetTime*/
    private void resetTime(){
        elapsedTime = 0;
        DisplayedValue.setValue(getCurrentTimeString());
    }

    /*getCurrentTimeString*/
    private String getCurrentTimeString(){
        int minutes = (int) elapsedTime / 60;
        int seconds = (int) elapsedTime % 60;
        return minutes + ":" + seconds;
    }

    /*updateBtnState*/
    private void updateBtnState(){
        if(isQueueRunning){
            QueueStartBtn.setDisable(false);
            QueueStopBtn.setDisable(true);
            isQueueRunning = false;
        }
        else{
            QueueStartBtn.setDisable(true);
            QueueStopBtn.setDisable(false);
            isQueueRunning = true;
        }

    }

    /*loadMenu*/
    public void loadMenu(ActionEvent actionEvent) throws IOException {
        //load menu page
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Scenes/Menu.fxml"));
        Parent MenuRoot = loader.load();

        //set welcome message
        MenuController cn = loader.getController();
        cn.msgLabel.setText("welcome ");

        //get current Stage
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        //set the menu page
        Scene MenuPage = new Scene(MenuRoot, 600, 400);
        window.setScene(MenuPage);
    }


}