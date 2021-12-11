package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class NewGameController {

    @FXML
    public Button playerVsPlayerButton;
    @FXML
    public Button playerVsAiButton;
    @FXML
    public Button aiVsAiButton;
    @FXML
    public Button exitButton;
    @FXML
    public Button backToMenuButton;


    @FXML
    public void playerVsPlayerAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/loginPlayer1Player2Screen.fxml"));
        Parent pane =(Parent) fxmlLoader.load();
        Scene scene = new Scene(pane);

        Stage stage = (Stage) ((Node)playerVsPlayerButton).getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("Players login");
        stage.show();

    }
    @FXML
    public void playerVsAiAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/loginPlayerLevelAiScreen.fxml"));
        Parent pane =(Parent) fxmlLoader.load();
        LoginPlayerLevelAiController loginPlayerLevelAiController = (LoginPlayerLevelAiController) fxmlLoader.getController();
        Scene scene = new Scene(pane);

        loginPlayerLevelAiController.setStartGameButtonDisable();

        Stage stage = (Stage) ((Node)playerVsAiButton).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);

        stage.setTitle("Player VS AI");
        stage.show();

    }
    @FXML
    public void aiVsAiAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/levelAiLevelAiScreen.fxml"));
        Parent pane =(Parent) fxmlLoader.load();
        LevelAiLevelAiController levelAiLevelAiController = (LevelAiLevelAiController) fxmlLoader.getController();
        Scene scene = new Scene(pane);

        levelAiLevelAiController.setStartGameButtonDisable();

        Stage stage = (Stage) ((Node)aiVsAiButton).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("AI Vs AI");
        stage.show();


        //do wyrzucenia ułatwienie testowania
        levelAiLevelAiController.easyRadioButton1.setSelected(true);
        levelAiLevelAiController.easyRadioButton2.setSelected(true);
        levelAiLevelAiController.startGameButton.setDisable(false);
        //
    }
    @FXML
    public void exitAction(){
        ((Stage) exitButton.getScene().getWindow()).close();
    }
    @FXML
    public void  backToMenuAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/menuScreen.fxml"));
        Parent pane =(Parent) fxmlLoader.load();
        Scene scene = new Scene(pane);
        Stage stage = (Stage) ((Node)backToMenuButton).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("MENU");
        stage.show();

    }


}
