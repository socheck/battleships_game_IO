package controller;

import db.DbConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.Controller;
import sample.User;

import java.io.IOException;
import java.util.ArrayList;

public class LevelAiLevelAiController {
    @FXML
    public RadioButton easyRadioButton1;
    @FXML
    public RadioButton mediumRadioButton1;
    @FXML
    public RadioButton hardRadioButton1;
    @FXML
    public RadioButton easyRadioButton2;
    @FXML
    public RadioButton mediumRadioButton2;
    @FXML
    public RadioButton hardRadioButton2;
    @FXML
    public Button startGameButton;
    @FXML
    public Button backToMenuButton;
    @FXML
    public Pane player1Pane;
    @FXML
    public Pane player2Pane;

    private int ai1Level = 0,ai2Level = 0;
    private User playerAI1, playerAI2;
    private boolean ai1LevelSet = false, ai2LevelSet = false;
    private Controller controller;

    private ArrayList<User> aiUserList;


    public void initialize(){
        DbConnection dbConnection = new DbConnection();
        aiUserList = dbConnection.getAI_list();
    }

    @FXML
    public void startGameAction() throws IOException { // przełączenie na ustawianie statków gracza 1
//        setplayerAI();
        controller = new Controller();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/player1BattleView.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        Player1BattleViewController player1BattleViewController = (Player1BattleViewController) fxmlLoader.getController();
        player1BattleViewController.setController(controller);
        Stage stage = new Stage();
        Scene scene = new Scene(root2);
        stage.setTitle("AI vs AI");
        stage.setScene(scene);
        stage.setResizable(false);
        String css = this.getClass().getResource("/css/aplication.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.show();
        try{
            ((Stage) startGameButton.getScene().getWindow()).close();
        }catch (Exception e){

        }
        controller.setPlayer1BattleViewController(player1BattleViewController);


        player1BattleViewController.playerNumberLabel.setText("Tura AI  1");


        controller.setAi1Is(true);
        controller.setAi2Is(true);
        controller.setAi1Level(ai1Level);
        controller.setAi2Level(ai2Level);

        controller.createBoardAi1();
        controller.createBoardAi2();
        controller.insertBoardShip(scene, controller.ai1Board);
        controller.insertBoardShoot(scene,controller.ai2Board);
        controller.setPlayer1(playerAI1);
        controller.setPlayer2(playerAI2);
        controller.player1BattleViewController.player1Pane.getChildren().add(playerAI1.getPhoto());
        controller.player1BattleViewController.player2Pane.getChildren().add(playerAI2.getPhoto());
        player1BattleViewController.nextButton.setDisable(true);
        controller.player1BattleViewController.nextButton.setVisible(false);
        player1BattleViewController.nextPlayerReadyButton.setDisable(true);
        controller.player1BattleViewController.nextPlayerReadyButton.setVisible(false);
        //ustawiono staki 1 i strzelanie 2
        ((Stage) startGameButton.getScene().getWindow()).close();
        controller.aiVsAiStartGame();


    }


    @FXML
    public void easyAction1(){

        mediumRadioButton1.setSelected(false);
        hardRadioButton1.setSelected(false);
        ai1Level = 0;
        ai1LevelSet = true;
        setStartGameButtonEnable();

        easyRadioButton2.setDisable(true);
        mediumRadioButton2.setDisable(false);
        hardRadioButton2.setDisable(false);
        setAiUser1();
    }
    @FXML
    public void mediumAction1(){
        easyRadioButton1.setSelected(false);
        hardRadioButton1.setSelected(false);
        ai1Level = 1;
        ai1LevelSet = true;
        setStartGameButtonEnable();

        easyRadioButton2.setDisable(false);
        mediumRadioButton2.setDisable(true);
        hardRadioButton2.setDisable(false);
        setAiUser1();
    }
    @FXML
    public void hardAction1(){
        mediumRadioButton1.setSelected(false);
        easyRadioButton1.setSelected(false);
        ai1Level = 2;
        ai1LevelSet = true;
        setStartGameButtonEnable();
        easyRadioButton2.setDisable(false);
        mediumRadioButton2.setDisable(false);
        hardRadioButton2.setDisable(true);
        setAiUser1();
    }
    @FXML
    public void easyAction2(){
        mediumRadioButton2.setSelected(false);
        hardRadioButton2.setSelected(false);
        ai2Level = 0;
        ai2LevelSet = true;
        setStartGameButtonEnable();
        easyRadioButton1.setDisable(true);
        mediumRadioButton1.setDisable(false);
        hardRadioButton1.setDisable(false);
        setAiUser2();
    }
    @FXML
    public void mediumAction2(){
        hardRadioButton2.setSelected(false);
        easyRadioButton2.setSelected(false);
        ai2Level = 1;
        ai2LevelSet = true;
        setStartGameButtonEnable();
        easyRadioButton1.setDisable(false);
        mediumRadioButton1.setDisable(true);
        hardRadioButton1.setDisable(false);
        setAiUser2();
    }
    @FXML
    public void hardAction2(){
        mediumRadioButton2.setSelected(false);
        easyRadioButton2.setSelected(false);
        ai2Level = 2;
        ai2LevelSet = true;
        setStartGameButtonEnable();
        easyRadioButton1.setDisable(false);
        mediumRadioButton1.setDisable(false);
        hardRadioButton1.setDisable(true);
        setAiUser2();

    }

    @FXML
    public void  backToMenuAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/menuScreen.fxml"));
        Parent pane =(Parent) fxmlLoader.load();
        Scene scene = new Scene(pane);
//        Stage primaryStage = new Stage();
//        primaryStage.setTitle("MENU");
//        primaryStage.setScene(new Scene(pane));
//        primaryStage.show();
//        ((Stage) backToMenuButton.getScene().getWindow()).close();
        String css = this.getClass().getResource("/css/aplication.css").toExternalForm();
        scene.getStylesheets().add(css);
        Stage stage = (Stage) ((Node)backToMenuButton).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setTitle("MENU");
        stage.show();

    }
    public void setStartGameButtonEnable(){
        if(ai1LevelSet && ai2LevelSet) {
            startGameButton.setDisable(false);
        }
    }
    public void setStartGameButtonDisable(){
        startGameButton.setDisable(true);
    }

    public void setAi1Level(int ai1Level) {
        this.ai1Level = ai1Level;
    }

    public void setAi2Level(int ai2Level) {
        this.ai2Level = ai2Level;
    }

    public void setplayerAI(){
        for (User u :
                aiUserList) {
            if(ai1Level == (u.getId()-1)){
                playerAI1 = u;
            }
            if(ai2Level == (u.getId()-1)){
                playerAI2 = u;
            }
        }
    }

    public void setAiUser1(){
        try {
            player2Pane.getChildren().remove(playerAI1.getPhoto());
        } catch (Exception e) {
        }finally {
            for (User u :
                    aiUserList) {
                if ((u.getId() - 1) == ai1Level) {
                    playerAI1 = u;
                    player1Pane.getChildren().add(playerAI1.getPhoto());
                    return;
                }
            }
        }

    }
    public void setAiUser2(){
        try {
            player2Pane.getChildren().remove(playerAI2.getPhoto());
        } catch (Exception e) {
        }finally {
            for (User u :
                    aiUserList) {
                if ((u.getId() - 1) == ai2Level) {
                    playerAI2 = u;
                    player2Pane.getChildren().add(playerAI2.getPhoto());
                    return;
                }
            }
        }

    }

}

