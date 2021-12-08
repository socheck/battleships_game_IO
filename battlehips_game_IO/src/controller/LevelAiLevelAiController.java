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
    private int ai1Level = 0,ai2Level = 0;
    private boolean ai1LevelSet = false, ai2LevelSet = false;
    private Controller controller;


    @FXML
    public void startGameAction() throws IOException { // przełączenie na ustawianie statków gracza 1
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

        stage.show();
        try{
            ((Stage) startGameButton.getScene().getWindow()).close();
        }catch (Exception e){

        }
        controller.setPlayer1BattleViewController(player1BattleViewController);
        player1BattleViewController.nextButton.setDisable(true);
        player1BattleViewController.nextPlayerReadyButton.setDisable(true);
        player1BattleViewController.playerNumberLabel.setText("Tura AI  1");
        player1BattleViewController.continueButton.setDisable(true);

        controller.setAi1Is(true);
        controller.setAi2Is(true);
        controller.setAi1Level(ai1Level);
        controller.setAi2Level(ai2Level);

        controller.aiVsAiStartGame();


        controller.createBoardAi1();
        controller.createBoardAi2();
        controller.insertBoardAi1Ship(scene);
        controller.insertBoardAi2Shoot(scene);
        //ustawiono staki 1 i strzelanie 2
        ((Stage) startGameButton.getScene().getWindow()).close();

    }


    @FXML
    public void easyAction1(){
        mediumRadioButton1.setSelected(false);
        hardRadioButton1.setSelected(false);
        ai1Level = 0;
        ai1LevelSet = true;
        setStartGameButtonEnable();

    }
    @FXML
    public void mediumAction1(){
        easyRadioButton1.setSelected(false);
        hardRadioButton1.setSelected(false);
        ai1Level = 1;
        ai1LevelSet = true;
        setStartGameButtonEnable();
    }
    @FXML
    public void hardAction1(){
        mediumRadioButton1.setSelected(false);
        easyRadioButton1.setSelected(false);
        ai1Level = 2;
        ai1LevelSet = true;
        setStartGameButtonEnable();
    }
    @FXML
    public void easyAction2(){
        mediumRadioButton2.setSelected(false);
        hardRadioButton2.setSelected(false);
        ai2Level = 0;
        ai2LevelSet = true;
        setStartGameButtonEnable();
    }
    @FXML
    public void mediumAction2(){
        hardRadioButton2.setSelected(false);
        easyRadioButton2.setSelected(false);
        ai2Level = 1;
        ai2LevelSet = true;
        setStartGameButtonEnable();
    }
    @FXML
    public void hardAction2(){
        mediumRadioButton2.setSelected(false);
        easyRadioButton2.setSelected(false);
        ai2Level = 2;
        ai2LevelSet = true;
        setStartGameButtonEnable();

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
    public void setStartGameButtonEnable(){
        if(ai1LevelSet && ai2LevelSet) {
            startGameButton.setDisable(false);
        }
    }
    public void setStartGameButtonDisable(){
        startGameButton.setDisable(true);
    }
}

