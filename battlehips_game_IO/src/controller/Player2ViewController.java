package controller;

import controller.BufforToP1Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.Controller;

import java.io.IOException;

public class Player2ViewController {

    @FXML
    private Button startGameButton;
    @FXML
    private Button randomButton;
    @FXML
    private Button clearBoardButton;

    private Controller controller;
    @FXML
    public void BufforToP1View() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/bufforToP1View.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        BufforToP1Controller bufforToP1Controller = (BufforToP1Controller) fxmlLoader.getController();
        bufforToP1Controller.setController(controller);
        Scene scene = new Scene(root2);
//        Stage stage = new Stage();
//        stage.setScene(new Scene(root2));
//        stage.setResizable(false);
//        stage.setTitle("bufforToP1View.fxml");
//        stage.show();

        Stage stage = (Stage) ((Node)startGameButton).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setTitle("bufforToP1View.fxml");
        stage.show();

//        try{
//            ((Stage) startGameButton.getScene().getWindow()).close();
//        }catch (Exception e){
//
//        }


    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public Button getStartGameButton() {
        return startGameButton;
    }
    public void setStartGameButtonDisable(){
        startGameButton.setDisable(true);

    }
    public void setStartGameButtonEnable(){
        startGameButton.setDisable(false);

    }
    public void setStartGameEnable(){             //ustwaienie przycisku na klikalny
        startGameButton.setDisable(false);
    }
    public void randomAction(){

        controller.randomPositionShipPlayer2(randomButton.getScene());
    }
    @FXML
    public void clearBoardAction(){
        controller.clearShipOnBoardPl2(clearBoardButton.getScene());
        startGameButton.setDisable(true);
    }
}
