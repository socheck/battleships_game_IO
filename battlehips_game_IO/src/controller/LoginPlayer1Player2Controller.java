package controller;

import db.DbConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import bs_game_backend.Controller;
import bs_game_backend.User;

import java.io.IOException;
import java.util.ArrayList;

public class LoginPlayer1Player2Controller {

    @FXML
    public Button startGameButton;
    @FXML
    public Button backToMenuButton;
    @FXML
    public Button loginPlayer1Button;
    @FXML
    public Button loginPlayer2Button;
    @FXML
    public Label player1Label;
    @FXML
    public Label player2Label;
    @FXML
    public Pane player1Pane;
    @FXML
    public Pane player2Pane;
    @FXML
    public Label pleaseLoginLabel;
    private Controller controller;
    private DbConnection dbConnection;
    private ArrayList<User> userArrayList;
    private User player1;
    private User player2;
    private LoginPlayer1Player2Controller loginPlayer1Player2Controller;

    public void initialize(){
        dbConnection = new DbConnection();
        userArrayList = dbConnection.getUser_list();
    }

    public void setLoginPlayer1Player2Controller(LoginPlayer1Player2Controller loginPlayer1Player2Controller) {
        this.loginPlayer1Player2Controller = loginPlayer1Player2Controller;
    }

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
        String css = this.getClass().getResource("/css/aplication.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.show();

        controller.setPlayer1Is(true);
        controller.setPlayer2Is(true);
        controller.createBoardPl1();
        controller.insertBoardPl1(scene);
        controller.setPlayer1ViewController(player1ViewController);
        controller.setPlayer1(player1);
        controller.setPlayer2(player2);
        player1ViewController.setSwitchToP2ButtonDisabel();
        player1ViewController.startGameAiButton.setDisable(true);
        player1ViewController.startGameAiButton.setVisible(false);
        player1ViewController.player1Pane.getChildren().add(controller.getPlayer1().getPhoto());
        player1ViewController.playerUsernameLabel.setText(controller.getPlayer1().getUsername());
    }
    public void  backToMenuAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/menuScreen.fxml"));
        Parent pane =(Parent) fxmlLoader.load();
        Scene scene = new Scene(pane);
        Stage stage = (Stage) ((Node)backToMenuButton).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        String css = this.getClass().getResource("/css/aplication.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("MENU");
        stage.show();

    }
    @FXML
    public void loginPlayer1Action() throws IOException {
        loginPlayer1Button.setDisable(true);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/loginPlayerScreen.fxml"));
        Parent pane =(Parent) fxmlLoader.load();
        LoginPlayerController loginPlayerController = (LoginPlayerController) fxmlLoader.getController();
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Log in");
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        String css = this.getClass().getResource("/css/aplication.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.show();
        loginPlayerController.playerLoginLabel.setText("Player 1 Login");
        loginPlayerController.setLoginPlayer1Player2Controller(loginPlayer1Player2Controller);
        loginPlayerController.setPlayer1(true);
        loginPlayer1Button.setDisable(false);
    }
    @FXML
    public void loginPlayer2Action() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/loginPlayerScreen.fxml"));
        Parent pane =(Parent) fxmlLoader.load();
        LoginPlayerController loginPlayerController = (LoginPlayerController) fxmlLoader.getController();
        Stage stage = new Stage();
        stage.setTitle("Log in");
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        String css = this.getClass().getResource("/css/aplication.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.show();
        loginPlayerController.playerLoginLabel.setText("Player 2 Login");
        loginPlayerController.setLoginPlayer1Player2Controller(loginPlayer1Player2Controller);
        loginPlayerController.setPlayer1(false);
    }
    public void checkPlayersLogged(){
        if(player1 != null && player2 != null){
            startGameButton.setDisable(false);
        }else {
            startGameButton.setDisable(true);
        }

    }

    public User getPlayer1() {
        return player1;
    }

    public void setPlayer1(User player1) {
        this.player1 = player1;
        player1Pane.getChildren().add(player1.getPhoto());
        loginPlayer2Button.setDisable(false);
        pleaseLoginLabel.setText("Pleas login player 2");
        loginPlayer1Button.setDisable(true);
        loginPlayer2Button.setVisible(true);
        loginPlayer1Button.setVisible(false);
    }

    public User getPlayer2() {
        return player2;

    }

    public void setPlayer2(User player2) {
        this.player2 = player2;
        player2Pane.getChildren().add(player2.getPhoto());
        pleaseLoginLabel.setText("");
        loginPlayer2Button.setDisable(true);
        loginPlayer2Button.setVisible(false);
        startGameButton.setVisible(true);

    }
}
