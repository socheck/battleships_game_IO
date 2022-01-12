package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.Controller;

import java.io.IOException;

public class SignInP2Controller {

    @FXML
    private Button login_pl_2;

    Controller controller;

    @FXML
    public void changetopl2() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/player2View.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        Player2ViewController player2ViewController = (Player2ViewController) fxmlLoader.getController();
        player2ViewController.setController(controller);
        Scene scene = new Scene(root2);
        Stage stage = (Stage) ((Node)login_pl_2).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setTitle("player2View.fxml");
        stage.show();

        controller.createBoardPl2();
        controller.insertBoardPl2(scene);
        controller.setPlayer2ViewController(player2ViewController);
        player2ViewController.setStartGameButtonDisable();
        player2ViewController.player2Pane.getChildren().add(controller.getPlayer2().getPhoto());
        player2ViewController.playerUsernameLabel.setText(controller.getPlayer2().getUsername());

    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}

