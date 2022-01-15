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
        String css = this.getClass().getResource("/css/aplication.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setResizable(false);
        controller.setPlayer1BattleViewController(player1BattleViewController);
        controller.player2Board.setEnableShot(true);
        controller.player2Board.render();
        controller.insertBoardPl1Ready(scene);
        controller.insertBoardPl2Ready(scene);

        stage.show();
        try{
            ((Stage) goP1Turn.getScene().getWindow()).close();
        }catch (Exception e){

        }
        player1BattleViewController.nextButton.setDisable(true);
        player1BattleViewController.nextPlayerReadyButton.setDisable(true);
        player1BattleViewController.playerNumberLabel.setText("Tura Gracza Nr 1");
        player1BattleViewController.player1Label.setText(controller.getPlayer1().getUsername());
        player1BattleViewController.player2Label.setText(controller.getPlayer2().getUsername());
        player1BattleViewController.player1Pane.getChildren().add(controller.getPlayer1().getPhoto());
        player1BattleViewController.player2Pane.getChildren().add(controller.getPlayer2().getPhoto());


        player1BattleViewController.exitButton.setDisable(true);

    }
}
