package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.Controller;

import java.io.IOException;

public class Player2ViewController {

    @FXML
    private Button startGameButton;

    Controller controller;
    @FXML
    public void BufforToP1View() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/bufforToP1View.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        BufforToP1Controller bufforToP1Controller = (BufforToP1Controller) fxmlLoader.getController();
        bufforToP1Controller.setController(controller);
        Stage stage = new Stage();
        stage.setScene(new Scene(root2));
        stage.setResizable(false);
        stage.show();
        try{
            ((Stage) startGameButton.getScene().getWindow()).close();
        }catch (Exception e){

        }


    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
