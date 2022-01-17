package controller;

import db.DbConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import bs_game_backend.Controller;
import bs_game_backend.User;

import java.io.IOException;
import java.util.ArrayList;

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
    @FXML
    public Label player1Label;
    @FXML
    public Pane player1Pane;
    @FXML
    public Pane player2Pane;
    @FXML
    public Label pleaseLoginLabel;

    private Controller controller;
    private int aiLevel = 0;
    private User player1;
    private User playerAi;
    private LoginPlayerLevelAiController loginPlayerLevelAiController;
    private ArrayList<User> aiUserList;


    public void initialize(){
        DbConnection dbConnection = new DbConnection();
        aiUserList = dbConnection.getAI_list();
    }

    public User getPlayer1() {
        return player1;
    }

    public void setPlayer1(User player1) {
        this.player1 = player1;
        player1Pane.getChildren().add(player1.getPhoto());
        loginPlayer1Button.setDisable(true);
        loginPlayer1Button.setVisible(false);
        easyRadioButton.setVisible(true);
        mediumRadioButton.setVisible(true);
        hardRadioButton.setVisible(true);
        pleaseLoginLabel.setText("Chose AI level");
    }



    public void setLoginPlayerLevelAiController(LoginPlayerLevelAiController loginPlayerLevelAiController) {
        this.loginPlayerLevelAiController = loginPlayerLevelAiController;
    }

    @FXML
    public void startGameAction() throws IOException {

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
        String css = this.getClass().getResource("/css/application.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.show();
        controller.setPlayer1Is(true);
        controller.setAi1Is(true);
        controller.setAi1Level(aiLevel);
        controller.createBoardPl1();
        controller.insertBoardPl1(scene);
        controller.setPlayer1ViewController(player1ViewController);

        controller.createBoardAi1();
        controller.setPlayer1(player1);
        controller.setPlayer2(playerAi);

        player1ViewController.setSwitchToP2ButtonDisabel();
        player1ViewController.startGameAiButton.setDisable(true);
        player1ViewController.setNextGameWithAi(true);
        player1ViewController.switchToP2Button.setVisible(false);
        player1ViewController.player1Pane.getChildren().add(player1.getPhoto());

        ((Stage) startGameButton.getScene().getWindow()).close();

    }
    @FXML
    public void loginPlayer1Action() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/loginPlayerScreen.fxml"));
        Parent pane =(Parent) fxmlLoader.load();
        LoginPlayerController loginPlayerController = (LoginPlayerController) fxmlLoader.getController();
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Log in");
        Scene scene = new Scene(pane);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        String css = this.getClass().getResource("/css/application.css").toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.show();
        loginPlayerController.playerLoginLabel.setText("Player 1 Login");
        loginPlayerController.setPlyer1WithAi(true);
        loginPlayerController.setLoginPlayerLevelAiController(loginPlayerLevelAiController);
    }

    @FXML
    public void  backToMenuAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/menuScreen.fxml"));
        Parent pane =(Parent) fxmlLoader.load();
        Scene scene = new Scene(pane);
        Stage stage = (Stage) ((Node)backToMenuButton).getScene().getWindow();
        stage.setScene(scene);
        String css = this.getClass().getResource("/css/application.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setResizable(true);
        stage.setTitle("MENU");
        stage.show();

    }
    @FXML
    public void easyAction(){
        mediumRadioButton.setSelected(false);
        hardRadioButton.setSelected(false);
        aiLevel = 0;
        setAiUser();
        startGameButton.setVisible(true);

    }
    @FXML
    public void mediumAction(){
        easyRadioButton.setSelected(false);
        hardRadioButton.setSelected(false);
        aiLevel = 1;
        setAiUser();
        startGameButton.setVisible(true);
    }
    @FXML
    public void hardAction(){
        mediumRadioButton.setSelected(false);
        easyRadioButton.setSelected(false);
        aiLevel = 2;
        setAiUser();
        startGameButton.setVisible(true);


    }
    public void setStartGameButtonDisable(){
        startGameButton.setDisable(true);
    }

    public void setStartGameButtonEnable(){
        if(player1 == null && (aiLevel == 0 || aiLevel == 1 || aiLevel == 2)){
            return;
        }
        startGameButton.setDisable(false);
    }
    public void setAiUser(){

        try {
            player2Pane.getChildren().remove(playerAi.getPhoto());
        } catch (Exception e) {
        }finally {
            for (User u :
                    aiUserList) {
                if((u.getId()-1) == aiLevel){
                    playerAi = u;
                    player2Pane.getChildren().add(playerAi.getPhoto());
                    return;
                }
            }
        }
    }
}
