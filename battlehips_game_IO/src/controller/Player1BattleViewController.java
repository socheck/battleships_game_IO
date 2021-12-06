package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sample.Controller;

public class Player1BattleViewController {

    @FXML
    public Button nextButton;
    @FXML
    public Button nextPlayerReadyButton;

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
        if (ifPlayer1) {
            controller.insertBoardPl2Ship(nextPlayerReadyButton.getScene());
            controller.insertBoardPl1Shoot(nextPlayerReadyButton.getScene());
            nextPlayerReadyButton.setDisable(true);
            ifPlayer1 = false;
        }else {
            controller.insertBoardPl1Ship(nextPlayerReadyButton.getScene());
            controller.insertBoardPl2Shoot(nextPlayerReadyButton.getScene());
            nextPlayerReadyButton.setDisable(true);
            ifPlayer1 = true;
        }
    }



}
