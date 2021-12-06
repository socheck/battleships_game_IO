package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.Controller;

import java.io.IOException;

public class BufforToP1Controller {

    @FXML
    private Button goP1Turn;

    Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @FXML
    public void player1BattleView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/player1BattleView.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        Player1BattleViewController player1BattleViewController = (Player1BattleViewController) fxmlLoader.getController();
        player1BattleViewController.setController(controller);
        Stage stage = new Stage();
        Scene scene = new Scene(root2);
        stage.setTitle("player1BattleView");
        stage.setScene(scene);
        stage.setResizable(false);
//        controller.player1Board.setAI(true);
//        controller.player1Board.render();
        controller.setPlayer1BattleViewController(player1BattleViewController);
        controller.insertBoardPl1Ready(scene);
        controller.player2Board.setAI(true);
        controller.player2Board.render();
        controller.insertBoardPl2Ready(scene);

        stage.show();
        try{
            ((Stage) goP1Turn.getScene().getWindow()).close();
        }catch (Exception e){

        }
        player1BattleViewController.nextButton.setDisable(true);
        player1BattleViewController.nextPlayerReadyButton.setDisable(true);

    }
}
