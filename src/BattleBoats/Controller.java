package BattleBoats;

import java.net.URL;
import java.util.LinkedList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.*;


public class Controller implements Initializable {
    AttackEvent currentAttackEvent = new AttackEvent();
    LinkedList<ChatEntry> chatTranscript = new LinkedList<ChatEntry>();

    @FXML
    private TextArea chatLog;

    @FXML
    private TextField chatEntry;

    @FXML
    private TextField entryAuthor;

    @FXML
    private Button postButton;

    @FXML
    private Button cancelButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        entryAuthor.textProperty().bindBidirectional(currentAttackEvent.attackPlayerProperty());
        chatEntry.textProperty().bindBidirectional(currentAttackEvent.attackTextProperty());
        refreshGameBoard();
    }

    @FXML
    private void postButtonClicked(ActionEvent event) {
        if((chatEntry.getLength() > 3)&&(entryAuthor.getLength() > 1)){
            ChatEntry t = new ChatEntry();
            t.setEntryAuthor(entryAuthor.getText());
            t.setEntryText(chatEntry.getText());
            chatTranscript.add(t);
            refreshGameBoard();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Your post must have both an author and entry text.");
            alert.setTitle("Not Enough Data");
            alert.getButtonTypes().remove(0, 2);
            alert.getButtonTypes().add(0, ButtonType.OK);
            Optional<ButtonType> confirmationResponse = alert.showAndWait();
        }
    }

    @FXML
    private void cancelButtonClicked(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Are you sure you want to cancel?");
        alert.setTitle("Cancelling Update");
        alert.getButtonTypes().remove(0, 2);
        alert.getButtonTypes().add(0, ButtonType.YES);
        alert.getButtonTypes().add(1, ButtonType.NO);
        Optional<ButtonType> confirmationResponse = alert.showAndWait();
        if(confirmationResponse.get() == ButtonType.YES) {
            chatEntry.clear();
        }
    }

    void setChatTranscript(LinkedList<ChatEntry> parentChatTranscript){
        this.chatTranscript = parentChatTranscript;
    }

    void refreshGameBoard()
    {
        chatLog.clear();
        chatTranscript.forEach((test) -> {
            chatLog.appendText(test.getEntryAuthor() +": " + test.getEntryText() + "\n");
        });

    }


}
