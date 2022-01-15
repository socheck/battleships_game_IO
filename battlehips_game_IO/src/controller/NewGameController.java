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
        LoginPlayer1Player2Controller loginPlayer1Player2Controller = (LoginPlayer1Player2Controller) fxmlLoader.getController();
        Stage stage = (Stage) ((Node)playerVsPlayerButton).getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("Players login");
        String css = this.getClass().getResource("/css/application.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.show();
        loginPlayer1Player2Controller.setLoginPlayer1Player2Controller(loginPlayer1Player2Controller);
        loginPlayer1Player2Controller.startGameButton.setDisable(true);
        loginPlayer1Player2Controller.loginPlayer2Button.setDisable(true);
        loginPlayer1Player2Controller.pleaseLoginLabel.setText("Please login player 1");
        loginPlayer1Player2Controller.loginPlayer2Button.setVisible(false);
        loginPlayer1Player2Controller.startGameButton.setVisible(false);

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
        String css = this.getClass().getResource("/css/application.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("Player VS AI");
        stage.show();
        loginPlayerLevelAiController.setLoginPlayerLevelAiController(loginPlayerLevelAiController);
        loginPlayerLevelAiController.pleaseLoginLabel.setText("Please login player");
        loginPlayerLevelAiController.startGameButton.setVisible(false);
        loginPlayerLevelAiController.easyRadioButton.setVisible(false);
        loginPlayerLevelAiController.mediumRadioButton.setVisible(false);
        loginPlayerLevelAiController.hardRadioButton.setVisible(false);
    }
    @FXML
    public void aiVsAiAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/levelAiLevelAiScreen.fxml"));
        Parent pane =(Parent) fxmlLoader.load();
        LevelAiLevelAiController levelAiLevelAiController = (LevelAiLevelAiController) fxmlLoader.getController();
        Scene scene = new Scene(pane);

        levelAiLevelAiController.setStartGameButtonDisable();
        String css = this.getClass().getResource("/css/application.css").toExternalForm();
        scene.getStylesheets().add(css);
        Stage stage = (Stage) ((Node)aiVsAiButton).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("AI Vs AI");
        stage.show();
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
        String css = this.getClass().getResource("/css/application.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("MENU");
        stage.show();

    }
}
