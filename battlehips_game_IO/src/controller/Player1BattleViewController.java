package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.Controller;

import java.io.IOException;

public class Player1BattleViewController {

    @FXML
    public Button nextButton;
    @FXML
    public Button nextPlayerReadyButton;
    @FXML
    public Label playerNumberLabel;
    @FXML
    public Button continueButton;
    @FXML
    public Label  player1Label;
    @FXML
    public Label  player2Label;
    @FXML
    public Button backToMenuButton;

    Controller controller;
    private boolean ifPlayer1 = true; //true oznacza gracza 1

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void nextAction(){
        nextButton.setDisable(true);
        nextPlayerReadyButton.setDisable(false);
        controller.hideBoardPl1(nextButton.getScene());
        controller.hideBoardPl2(nextButton.getScene());
    }
    public void player2ReadyAction() {
        if (ifPlayer1) { // skonczyła się tura gracza 1 i przłączamy na gracza 2
            controller.insertBoardShip(nextPlayerReadyButton.getScene(), controller.player2Board);
            controller.insertBoardShoot(nextPlayerReadyButton.getScene(), controller.player1Board);
            nextPlayerReadyButton.setDisable(true);
            ifPlayer1 = false;
            playerNumberLabel.setText("Tura Gracza Nr 2");
        }else { // przełączamy ba gracza 1
            controller.insertBoardShip(nextPlayerReadyButton.getScene(), controller.player1Board);
            controller.insertBoardShoot(nextPlayerReadyButton.getScene(), controller.player2Board);
            nextPlayerReadyButton.setDisable(true);
            ifPlayer1 = true;
            playerNumberLabel.setText("Tura Gracza Nr 1");
        }
    }

    public void continueAction(){
        ((Stage)(continueButton.getScene().getWindow())).close();
    }

    public void setContinueButtonDisable(){
        continueButton.setDisable(true);
    }
    public void setContinueButtonEnable(){
        continueButton.setDisable(false);
    }
    public void setNextButtonDisable(){
        nextButton.setDisable(true);
    }
    public void setNextButtonEnable(){
        nextButton.setDisable(false);
    }
    @FXML
    public void  backToMenuAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/menuScreen.fxml"));
        Parent pane =(Parent) fxmlLoader.load();
        Stage primaryStage = new Stage();
        primaryStage.setTitle("MENU");
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();

        ((Stage) backToMenuButton.getScene().getWindow()).close();
        return;

    }



}
