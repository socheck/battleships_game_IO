package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class StartViewController {

    @FXML
    private Button playerVsPlayerButton;

    @FXML
    private Button playerVsBotButton;
    @FXML
    private Button botVsBotButton;

    private String gameMode;



    @FXML
    public void playerVsPlayerAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/logowanie.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        LogowanieController logowanieController = (LogowanieController) fxmlLoader.getController();
        Stage stage = new Stage();
        stage.setScene(new Scene(root2));
        stage.setResizable(false);
        stage.setTitle("logowanie.fxml");
        stage.show();
        ((Stage) playerVsPlayerButton.getScene().getWindow()).close();
        logowanieController.setGameMode(0);

    }

    @FXML
    public void playerVsBotAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/logowanie.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        LogowanieController logowanieController = (LogowanieController) fxmlLoader.getController();

        Stage stage = new Stage();
        stage.setScene(new Scene(root2));
        stage.setResizable(false);
        stage.setTitle("logowanie.fxml");

        stage.show();
        ((Stage) playerVsBotButton.getScene().getWindow()).close();
        logowanieController.setGameMode(1);
    }
    @FXML
    public void botVsBotAction(){


    }


    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }


}
