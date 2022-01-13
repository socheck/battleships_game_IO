package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.Controller;

import java.io.*;

public class Player1ViewController {

    @FXML
    public Button switchToP2Button;
    @FXML
    private Button randomButton;
    @FXML
    public Button startGameAiButton;
    @FXML
    public Button clearBoardButton;
    @FXML
    public Pane player1Pane;
    @FXML
    public Label playerUsernameLabel;

    private boolean nextGameWithAi = false; // true następna gra jest z botem
    private Controller controller;
    private Scene scene;

    public void initialize(){
        switchToP2Button.setDisable(false);

    }
    @FXML
    public void SignInP2() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/signInP2.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        SignInP2Controller signInP2Controller = (SignInP2Controller) fxmlLoader.getController();
        signInP2Controller.setController(controller);

        scene = new Scene(root2);
//        Stage stage = new Stage();
//        stage.setScene(scene);
//        stage.setResizable(false);
//        stage.setTitle("signInP2.fxml");
//        stage.show();
//        ((Stage) switchToP2Button.getScene().getWindow()).close();

        Stage stage = (Stage) ((Node)switchToP2Button).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setTitle("signInP2.fxml");
        stage.show();
    }


    public void randomAction() throws FileNotFoundException {
        controller.randomPositionShipAi(controller.getList_of_ships1(), controller.player1Board);

        if (!isNextGameWithAi()) {
            setSwitchToP2ButtonEnable();
        }
        if (isNextGameWithAi()) {
            setStartGameAiButtonEnable();
        }

        //System.out.println(controller.player1Board.getInitilaState(controller.player1Board));



    }
    @FXML
    public void startGameAiAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/player1BattleView.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        Player1BattleViewController player1BattleViewController = (Player1BattleViewController) fxmlLoader.getController();
        player1BattleViewController.setController(controller);
        Stage stage = new Stage();
        Scene scene = new Scene(root2);
        stage.setTitle("player1BattleView");
        stage.setScene(scene);
        stage.setResizable(false);
        controller.setPlayer1BattleViewController(player1BattleViewController);
        controller.insertBoardPl1Ready(scene);
        controller.insertBoardShoot(scene,controller.ai1Board);
        controller.player1BattleViewController.player1Pane.getChildren().add(controller.getPlayer1().getPhoto());
        controller.player1BattleViewController.player2Pane.getChildren().add(controller.getPlayer2().getPhoto());

        stage.show();
        try{
            ((Stage) startGameAiButton.getScene().getWindow()).close();
        }catch (Exception e){

        }
        player1BattleViewController.nextButton.setDisable(true);
        player1BattleViewController.nextPlayerReadyButton.setDisable(true);
        player1BattleViewController.playerNumberLabel.setText("Tura Gracza Nr 1");
        player1BattleViewController.player1Label.setText(controller.getPlayer1().getUsername());
        player1BattleViewController.player2Label.setText("AI level " + controller.getAi1Level());
        player1BattleViewController.continueButton.setDisable(true);
        player1BattleViewController.nextPlayerReadyButton.setVisible(false);
        player1BattleViewController.nextButton.setVisible(false);






    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public Button getSwitchToP2Button() {
        return switchToP2Button;
    }
    public void setSwitchToP2ButtonDisabel(){      //ustwaienie przycisku na nie klikalny
        switchToP2Button.setDisable(true);
     }
    public void setSwitchToP2ButtonEnable(){             //ustwaienie przycisku na klikalny
        switchToP2Button.setDisable(false);
    }
    public void setStartGameAiButtonDisable(){
        startGameAiButton.setDisable(true);
    }
    public void setStartGameAiButtonEnable(){
        startGameAiButton.setDisable(false);
    }
    public boolean isNextGameWithAi() {
        return nextGameWithAi;
    }
    public void setNextGameWithAi(boolean nextGameWithAi) {
        this.nextGameWithAi = nextGameWithAi;
    }
    public Button getStartGameAiButton() {
        return startGameAiButton;
    }

    @FXML
    public void clearBoardAction(){
        controller.clearShipOnBoardPL(controller.getList_of_ships1(), controller.player1Board);
        switchToP2Button.setDisable(true);
        startGameAiButton.setDisable(true);
    }
}

