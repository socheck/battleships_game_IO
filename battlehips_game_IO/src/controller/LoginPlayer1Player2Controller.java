package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.Controller;

import java.io.IOException;

public class LoginPlayer1Player2Controller {

    @FXML
    public Button startGameButton;
    @FXML
    public Button backToMenuButton;
    @FXML
    public Button loginPlayer1Button;
    @FXML
    public Button loginPlayer2Button;
    private Controller controller;


    @FXML
    public void startGameAction() throws IOException {
        controller = new Controller();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/player1View.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        Player1ViewController player1ViewController = (Player1ViewController) fxmlLoader.getController();
        player1ViewController.setController(controller);

        Scene scene = new Scene(root2);

        Stage stage = (Stage) ((Node)startGameButton).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("player1View.fxml");
        stage.show();

        controller.setPlayer1Is(true);
        controller.setPlayer2Is(true);
        controller.createBoardPl1();
        controller.insertBoardPl1(scene);
        controller.setPlayer1ViewController(player1ViewController);

        player1ViewController.setSwitchToP2ButtonDisabel();
        player1ViewController.startGameAiButton.setDisable(true);
        player1ViewController.startGameAiButton.setVisible(false);


    }
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
    @FXML
    public void loginPlayer1Action() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/loginPlayerScreen.fxml"));
        Parent pane =(Parent) fxmlLoader.load();
        LoginPlayerController loginPlayerController = (LoginPlayerController) fxmlLoader.getController();
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Log in");
        stage.setScene(new Scene(pane));
        stage.show();
        loginPlayerController.playerLoginLabel.setText("Player 1 Login");
    }
    @FXML
    public void loginPlayer2Action() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/loginPlayerScreen.fxml"));
        Parent pane =(Parent) fxmlLoader.load();
        LoginPlayerController loginPlayerController = (LoginPlayerController) fxmlLoader.getController();
        Stage stage = new Stage();
        stage.setTitle("Log in");
        stage.setScene(new Scene(pane));
        stage.show();
        loginPlayerController.playerLoginLabel.setText("Player 2 Login");

    }



}
