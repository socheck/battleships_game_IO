package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sample.Controller;

public class Player1BattleViewController {

    @FXML
    public Button nextButton;
    @FXML
    public Button player2ReadyButton;

    Controller controller;
    private boolean ifPlayer1 = true; //true oznacza gracza 1

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void nextAction(){
        nextButton.setDisable(true);
        player2ReadyButton.setDisable(false);
        controller.hideBoardPl1(nextButton.getScene());
        controller.hideBoardPl2(nextButton.getScene());
    }
    public void player2ReadyAction() {
        if (ifPlayer1) {
            controller.insertBoardPl2Ship(player2ReadyButton.getScene());
            controller.insertBoardPl1Shoot(player2ReadyButton.getScene());
            player2ReadyButton.setDisable(true);
            ifPlayer1 = false;
        }else {
            controller.insertBoardPl1Ship(player2ReadyButton.getScene());
            controller.insertBoardPl2Shoot(player2ReadyButton.getScene());
            player2ReadyButton.setDisable(true);
            ifPlayer1 = true;
        }
    }



}
