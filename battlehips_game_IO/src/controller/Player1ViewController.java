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
    @FXML
    private Button randomButton;

    private Controller controller;
    Scene scene;

    public void initialize(){
        switchToP2Button.setDisable(false);
    }
    @FXML
    public void SignInP2() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/signInP2.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        SignInP2Controller signInP2Controller = (SignInP2Controller) fxmlLoader.getController();
        signInP2Controller.setController(controller);
        Stage stage = new Stage();
        scene = new Scene(root2);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("signInP2.fxml");
        stage.show();
        ((Stage) switchToP2Button.getScene().getWindow()).close();
    }


    public void randomAction(){

        controller.randomPositionShipPlayer1(randomButton.getScene());
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public Button getSwitchToP2Button() {
        return switchToP2Button;
    }
    public void setSwitchToP2ButtonDisabel(){      //ustwaienie przycisku na nie klikalny
        switchToP2Button.setDisable(true);
     }
    public void setSwitchToP2ButtonEnable(){             //ustwaienie przycisku na klikalny
        switchToP2Button.setDisable(false);
    }
}

