package controller;

import db.DbConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class LoginPlayerController {
    @FXML
    public Button exitButton;
    @FXML
    public Button loginButton;
    @FXML
    public TextField loginTextField;
    @FXML
    public PasswordField passwordTextField;
    @FXML
    public Label playerLoginLabel;
    private DbConnection dbConnection;


    public void initialize(){
        dbConnection = new DbConnection();

    }

    @FXML
    public void exitAction(){
        ((Stage) exitButton.getScene().getWindow()).close();
    }
    @FXML
    public void loginAction(){
        ((Stage) loginButton.getScene().getWindow()).close();
         String login = loginTextField.getText();
         String password = passwordTextField.getText();

    }


}
