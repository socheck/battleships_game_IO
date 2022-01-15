package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
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
    public Button exitButton;
    @FXML
    public Label  player1Label;
    @FXML
    public Label  player2Label;
    @FXML
    public Button backToMenuButton;
    @FXML
    public Pane player1Pane;
    @FXML
    public Pane player2Pane;
    @FXML
    public Pane winnerPane;

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


        if (playerNumberLabel.getText().equals("Tura Gracza Nr 2") ){
            playerNumberLabel.setText("Next player: " + controller.getPlayer1().getUsername());
        }else if (playerNumberLabel.getText().equals("Tura Gracza Nr 1") ){
            playerNumberLabel.setText("Next player: " + controller.getPlayer2().getUsername());
        }else{
            playerNumberLabel.setText("Switching Players");
        }

    }
    public void player2ReadyAction() {
        if (ifPlayer1) { // skonczyła się tura gracza 1 i przłączamy na gracza 2
            controller.insertBoardShip(nextPlayerReadyButton.getScene(), controller.player2Board);
            controller.insertBoardShoot(nextPlayerReadyButton.getScene(), controller.player1Board);
            nextPlayerReadyButton.setDisable(true);
            ifPlayer1 = false;
            playerNumberLabel.setText("Tura Gracza Nr 2");
            player1Label.setText(controller.getPlayer2().getUsername());
            player2Label.setText(controller.getPlayer1().getUsername());
            player1Pane.getChildren().add(controller.getPlayer2().getPhoto());
            player2Pane.getChildren().add(controller.getPlayer1().getPhoto());

        }else { // przełączamy na gracza 1
            controller.insertBoardShip(nextPlayerReadyButton.getScene(), controller.player1Board);
            controller.insertBoardShoot(nextPlayerReadyButton.getScene(), controller.player2Board);
            nextPlayerReadyButton.setDisable(true);
            ifPlayer1 = true;
            playerNumberLabel.setText("Tura Gracza Nr 1");
            player1Label.setText(controller.getPlayer1().getUsername());
            player2Label.setText(controller.getPlayer2().getUsername());
            player1Pane.getChildren().add(controller.getPlayer1().getPhoto());
            player2Pane.getChildren().add(controller.getPlayer2().getPhoto());
        }
    }

    public void exitAction(){
        controller.setExitToMenu(true);
        ((Stage)(exitButton.getScene().getWindow())).close();
    }

    public void setContinueButtonDisable(){
        exitButton.setDisable(true);
    }
    public void setContinueButtonEnable(){
        exitButton.setDisable(false);
    }
    public void setNextButtonDisable(){
        nextButton.setDisable(true);
    }
    public void setNextButtonEnable(){
        nextButton.setDisable(false);
    }
    @FXML
    public void  backToMenuAction() throws IOException {
        controller.setExitToMenu(true);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/menuScreen.fxml"));
        Parent pane =(Parent) fxmlLoader.load();
        Stage primaryStage = new Stage();
        primaryStage.setTitle("MENU");
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        String css = this.getClass().getResource("/css/aplication.css").toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.show();

        ((Stage) backToMenuButton.getScene().getWindow()).close();
        return;

    }



}
