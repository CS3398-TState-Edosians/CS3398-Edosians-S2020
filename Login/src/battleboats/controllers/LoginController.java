package battleboats.controllers;

import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginController {
    public TextField username = null;

    public void onPlayEvent(MouseEvent mouseEvent) {
        String usernm = new String(username.getCharacters().toString());
        if(!usernm.isEmpty())
            System.out.println(usernm);
    }
}
