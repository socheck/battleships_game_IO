package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.Controller;

import java.io.IOException;

public class LogowanieController {

    @FXML
    private Button login_pl_1;

    private Controller controller;


    @FXML
    public void  changetopl1() throws IOException {
        controller = new Controller();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/player1View.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        Player1ViewController player1ViewController = (Player1ViewController) fxmlLoader.getController();
        player1ViewController.setController(controller);
        Stage stage = new Stage();
        Scene scene = new Scene(root2);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setTitle("player1View.fxml");
        stage.show();

        controller.createBoard1();
        controller.insertBoardPl1(scene);
        controller.setPlayer1ViewController(player1ViewController);
        player1ViewController.setSwitchToP2ButtonDisabel();
        ((Stage) login_pl_1.getScene().getWindow()).close();

    }

    @FXML
    public void changeToPickDiffLevelPlayerVsAI() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/pickDiffLevelOfBot.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root2));
        stage.setResizable(false);
        stage.setTitle("Pick Level of Bot");
        stage.show();
        ((Stage) login_pl_1.getScene().getWindow()).close();


    }
}
