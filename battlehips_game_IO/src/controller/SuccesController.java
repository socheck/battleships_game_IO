package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class SuccesController {

    @FXML
    public Button okButton;
    @FXML
    public Label textLabel;


    public void okAction(){
        ((Stage) okButton.getScene().getWindow()).close();

    }

}
