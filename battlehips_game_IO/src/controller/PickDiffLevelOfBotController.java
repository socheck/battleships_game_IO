package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.io.IOException;


public class PickDiffLevelOfBotController {
    private String DifficultyLevel;
    @FXML
    private RadioButton easyLevelRButton;
    @FXML
    private RadioButton mediumLevelRButton;
    @FXML
    private RadioButton hardLevelRButton;
    @FXML
    private Button startGameButton;


    public String pickDifficultyLevel(){
        return null;
    }

    @FXML
    public void changetologin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/logowanie.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root2));
        stage.setResizable(false);
        stage.setTitle("logowanie.fxml");
        stage.show();
        ((Stage) startGameButton.getScene().getWindow()).close();
    }
}
