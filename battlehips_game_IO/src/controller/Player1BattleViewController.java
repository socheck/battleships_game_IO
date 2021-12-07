package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.Controller;

public class Player1BattleViewController {

    @FXML
    public Button nextButton;
    @FXML
    public Button nextPlayerReadyButton;
    @FXML
    public Label playerNumberLabel;
    @FXML
    public Button continueButton;

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
            controller.insertBoardPl2Ship(nextPlayerReadyButton.getScene());
            controller.insertBoardPl1Shoot(nextPlayerReadyButton.getScene());
            nextPlayerReadyButton.setDisable(true);
            ifPlayer1 = false;
            playerNumberLabel.setText("Tura Gracza Nr 2");
        }else { // przełączamy ba gracza 1
            controller.insertBoardPl1Ship(nextPlayerReadyButton.getScene());
            controller.insertBoardPl2Shoot(nextPlayerReadyButton.getScene());
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



}
