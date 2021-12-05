package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.Controller;

import java.io.IOException;

public class Player1ViewController {

    @FXML
    private Button switchToP2Button;

    private Controller controller;
    @FXML
    public void SignInP2() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/signInP2.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        SignInP2Controller signInP2Controller = (SignInP2Controller) fxmlLoader.getController();
        signInP2Controller.setController(controller);
        Stage stage = new Stage();
        stage.setScene(new Scene(root2));
        stage.setResizable(false);
        stage.show();
        ((Stage) switchToP2Button.getScene().getWindow()).close();
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
