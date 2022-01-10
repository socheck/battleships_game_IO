package controller;

import db.DbConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.User;

import java.util.ArrayList;


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
    @FXML
    public Label errorDataLabel;
    private boolean isPlayer1 = true;
    private boolean plyer1WithAi = false;
    private DbConnection dbConnection;
    private ArrayList<User> userArrayList;
    private LoginPlayer1Player2Controller loginPlayer1Player2Controller;
    private LoginPlayerLevelAiController loginPlayerLevelAiController;

    public boolean isPlyer1WithAi() {
        return plyer1WithAi;
    }

    public void setPlyer1WithAi(boolean plyer1WithAi) {
        this.plyer1WithAi = plyer1WithAi;
    }

    public LoginPlayerLevelAiController getLoginPlayerLevelAiController() {
        return loginPlayerLevelAiController;
    }

    public void setLoginPlayerLevelAiController(LoginPlayerLevelAiController loginPlayerLevelAiController) {
        this.loginPlayerLevelAiController = loginPlayerLevelAiController;
    }

    public void initialize(){
        dbConnection = new DbConnection();
        userArrayList = dbConnection.getUser_list();
    }

    public void setLoginPlayer1Player2Controller(LoginPlayer1Player2Controller loginPlayer1Player2Controller) {
        this.loginPlayer1Player2Controller = loginPlayer1Player2Controller;
    }

    public void setPlayer1(boolean player1) {
        isPlayer1 = player1;
    }

    @FXML
    public void exitAction(){
        ((Stage) exitButton.getScene().getWindow()).close();
    }
    @FXML
    public void loginAction(){
        errorDataLabel.setText("");
        String login = loginTextField.getText();
        String password = passwordTextField.getText();

        for (User u:
             userArrayList) {
            if(plyer1WithAi){
                if(u.getPassword().equals(password) && u.getUsername().equals(login)) {
                    loginPlayerLevelAiController.setPlayer1(u);
                    loginPlayerLevelAiController.player1Label.setText(u.getUsername());
                    return;
                }
            }
            if(u.getPassword().equals(password) && u.getUsername().equals(login)){

                if(isPlayer1 && loginPlayer1Player2Controller.getPlayer2() == null){
                    loginPlayer1Player2Controller.setPlayer1(u);
                    loginPlayer1Player2Controller.player1Label.setText(u.getUsername());
                    return;
                }
                if(!isPlayer1 && loginPlayer1Player2Controller.getPlayer1() == null){
                    loginPlayer1Player2Controller.setPlayer2(u);
                    loginPlayer1Player2Controller.player2Label.setText(u.getUsername());
                    return;
                }

                if(isPlayer1){
                    if(loginPlayer1Player2Controller.getPlayer2().getUsername().equals(login)){
                        errorDataLabel.setText("This player is playing.");
                        return;
                    }
                    loginPlayer1Player2Controller.setPlayer1(u);
                    loginPlayer1Player2Controller.player1Label.setText(u.getUsername());
                    return;
                }
                if(!isPlayer1 ){
                    if(loginPlayer1Player2Controller.getPlayer1().getUsername().equals(login)){
                        errorDataLabel.setText("This player is playing.");
                        return;
                    }
                    loginPlayer1Player2Controller.setPlayer2(u);
                    loginPlayer1Player2Controller.player2Label.setText(u.getUsername());
                    return;
                }
            }
        }
       errorDataLabel.setText("Invalid data");







    }


}
