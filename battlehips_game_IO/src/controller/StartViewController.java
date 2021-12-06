package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.Controller;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class StartViewController {

    @FXML
    private Button napieprzajbutton;

    @FXML
    private Button playerVsBotButton;

    private String gameMode;



    @FXML
    public void changetologin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/logowanie.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root2));
        stage.setResizable(false);
        stage.setTitle("logowanie.fxml");
        gameMode = "PVP";
        stage.show();
        ((Stage) napieprzajbutton.getScene().getWindow()).close();
    }

    @FXML
    public void changetologinPVAI() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/logowanie.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root2));
        stage.setResizable(false);
        stage.setTitle("logowanie.fxml");
        gameMode = "PVAI";
        stage.show();
        ((Stage) napieprzajbutton.getScene().getWindow()).close();
    }

    public void modeClicked(ActionEvent event){
        Button b = (Button) event.getSource();
        gameMode  = b.getText();
    }



    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }


}
