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
        stage.show();
        controller.createBoard1();
        controller.insertBoardPl1(scene);
        ((Stage) login_pl_1.getScene().getWindow()).close();

    }
}
