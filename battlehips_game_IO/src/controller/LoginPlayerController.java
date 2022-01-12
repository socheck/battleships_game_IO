package controller;

import db.DbConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.User;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    public void showSuccesScreen(User u) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/succesScreen.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        SuccesController succesController = (SuccesController) fxmlLoader.getController();
        Stage stage = new Stage();
        Scene scene = new Scene(root2);
        stage.setTitle("Succes");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        succesController.textLabel.setText("Player " + u.getUsername() + " logged.");
        ((Stage) loginButton.getScene().getWindow()).close();
    }
    @FXML
    public void loginAction() throws IOException {
        errorDataLabel.setText("");
        String login = loginTextField.getText();

        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md5.update(StandardCharsets.UTF_8.encode(passwordTextField.getText()));
        String password = String.format("%032x", new BigInteger(1, md5.digest()));


        for (User u:
             userArrayList) {
            if(login.equals(u.getUsername()) && password.equals(u.getPassword()) && u.getId() < 4){
                return;
            }
            if(plyer1WithAi){
                if(u.getPassword().equals(password) && u.getUsername().equals(login)) {
                    loginPlayerLevelAiController.setPlayer1(u);
                    loginPlayerLevelAiController.player1Label.setText(u.getUsername());
                    showSuccesScreen(u);
                    loginPlayerLevelAiController.setStartGameButtonEnable();

                    return;
                }
            }
            if(u.getPassword().equals(password) && u.getUsername().equals(login)){

                if(isPlayer1 && loginPlayer1Player2Controller.getPlayer2() == null){
                    loginPlayer1Player2Controller.setPlayer1(u);
                    loginPlayer1Player2Controller.player1Label.setText(u.getUsername());
                    showSuccesScreen(u);
                    loginPlayer1Player2Controller.checkPlayersLogged();
                    return;
                }
                if(!isPlayer1 && loginPlayer1Player2Controller.getPlayer1() == null){
                    loginPlayer1Player2Controller.setPlayer2(u);
                    loginPlayer1Player2Controller.player2Label.setText(u.getUsername());
                    showSuccesScreen(u);
                    loginPlayer1Player2Controller.checkPlayersLogged();
                    return;
                }

                if(isPlayer1){
                    if(loginPlayer1Player2Controller.getPlayer2().getUsername().equals(login)){
                        errorDataLabel.setText("This player is playing.");
                        return;
                    }
                    loginPlayer1Player2Controller.setPlayer1(u);
                    loginPlayer1Player2Controller.player1Label.setText(u.getUsername());
                    showSuccesScreen(u);
                    loginPlayer1Player2Controller.checkPlayersLogged();
                    return;
                }
                if(!isPlayer1 ){
                    if(loginPlayer1Player2Controller.getPlayer1().getUsername().equals(login)){
                        errorDataLabel.setText("This player is playing.");
                        return;
                    }
                    loginPlayer1Player2Controller.setPlayer2(u);
                    loginPlayer1Player2Controller.player2Label.setText(u.getUsername());
                    showSuccesScreen(u);
                    loginPlayer1Player2Controller.checkPlayersLogged();
                    return;
                }
            }
        }
       errorDataLabel.setText("Invalid data");







    }


}
