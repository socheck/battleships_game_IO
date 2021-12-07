package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import sample.Controller;

import java.io.IOException;

public class LoginPlayerLevelAiController {

    @FXML
    public Button loginPlayer1Button;
    @FXML
    public Button backToMenuButton;
    @FXML
    public Button startGameButton;
    @FXML
    public RadioButton easyRadioButton;
    @FXML
    public RadioButton mediumRadioButton;
    @FXML
    public RadioButton hardRadioButton;

    private Controller controller;
    private int aiLevel = 0;

    @FXML
    public void startGameAction() throws IOException { // przełączenie na ustawianie statków gracza 1

        controller = new Controller();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/player1View.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        Player1ViewController player1ViewController = (Player1ViewController) fxmlLoader.getController();
        player1ViewController.setController(controller);
        Stage stage = new Stage();
        Scene scene = new Scene(root2);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setTitle("player1View.fxml");
        stage.show();
        controller.setPlayer1Is(true);
        controller.setAi1Is(true);
        controller.setAiLevel(aiLevel);
        controller.createBoardPl1();
        controller.insertBoardPl1(scene);
        controller.setPlayer1ViewController(player1ViewController);

        controller.createBoardAi1();

        player1ViewController.setSwitchToP2ButtonDisabel();
        player1ViewController.startGameAiButton.setDisable(true);
        player1ViewController.setNextGameWithAi(true);  // następna gra z botem

        ((Stage) startGameButton.getScene().getWindow()).close();

    }
    @FXML
    public void loginPlayer1Action() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/loginPlayerScreen.fxml"));
        Parent pane =(Parent) fxmlLoader.load();
        LoginPlayerController loginPlayerController = (LoginPlayerController) fxmlLoader.getController();
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Log in");
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
        loginPlayerController.playerLoginLabel.setText("Player 1 Login");
    }

    @FXML
    public void backToMenuAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/menuScreen.fxml"));
        Parent pane =(Parent) fxmlLoader.load();
        Stage primaryStage = new Stage();
        primaryStage.setTitle("MENU");
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
        ((Stage) backToMenuButton.getScene().getWindow()).close();

    }
    @FXML
    public void easyAction(){

        mediumRadioButton.setSelected(false);
        hardRadioButton.setSelected(false);
        aiLevel = 0;
        setStartGameButtonEnable();
    }
    @FXML
    public void mediumAction(){
        easyRadioButton.setSelected(false);
        hardRadioButton.setSelected(false);
        aiLevel = 1;
        setStartGameButtonEnable();
    }
    @FXML
    public void hardAction(){
        mediumRadioButton.setSelected(false);
        easyRadioButton.setSelected(false);
        aiLevel = 2;
        setStartGameButtonEnable();
    }
    public void setStartGameButtonDisable(){
        startGameButton.setDisable(true);
    }
    public void setStartGameButtonEnable(){
        startGameButton.setDisable(false);
    }
}
