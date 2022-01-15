package controller;

import db.DbConnection;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import bs_game_backend.ClassToComboBox;
import bs_game_backend.User;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;


public class NewPlayerController {

    @FXML
    public Button creatUserButton;
    @FXML
    public Button backToMenuButton;
    @FXML
    public TextField usernameTextField;
    @FXML
    public PasswordField paswordPaswordField;
    @FXML
    public ComboBox<ClassToComboBox> avatarComboBox;
    @FXML
    public ImageView avatarImageView;
    @FXML
    public Label errorLabel;
    @FXML
    public Label succesLabel;


    ArrayList<ClassToComboBox> avatarsList = new ArrayList<>();

    public void initialize() {
        ObservableList<ClassToComboBox> avatarObservableList;
        ListProperty<ClassToComboBox> listPropertyAvatar;
        ArrayList<String> results = new ArrayList<String>();
        File[] files = new File("src/img/avatars/user").listFiles();
        for (File file : files) {
            if (file.isFile()) {
                results.add(file.getName());
            }
        }
        for (String s :
                results) {
            avatarsList.add(new ClassToComboBox(s.replaceFirst("[.][^.]+$", "")));
        }
        listPropertyAvatar = new SimpleListProperty<>(); //OBSlUGA Comboboxa z listy
        avatarObservableList = FXCollections.observableArrayList(avatarsList);
        listPropertyAvatar.set(avatarObservableList);
        avatarComboBox.itemsProperty().bindBidirectional(listPropertyAvatar);
    }

    public void  createUserAction() {
        errorLabel.setText("");
        if (paswordPaswordField.getText() == "") {
            errorLabel.setText("Choose password");
            return;
        }
        DbConnection dbConnection = new DbConnection();
        String username = usernameTextField.getText();
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md5.update(StandardCharsets.UTF_8.encode(paswordPaswordField.getText()));
        String password = String.format("%032x", new BigInteger(1, md5.digest()));

        if(avatarComboBox.getSelectionModel().getSelectedItem() == null){
           errorLabel.setText("Choose avatar");
           return;
        }
        if(username == ""){
            errorLabel.setText("Chose username");
            return;
        }
        if(password == ""){
            errorLabel.setText("Choose password");
            return;
        }
        ArrayList<User> userArrayList = dbConnection.getAllUser_list();

        for (User user :
                userArrayList) {
            if(user.getUsername().equals(username)){
                errorLabel.setText("This player exist!");
                return;
            }
        }
        usernameTextField.setText("");
        paswordPaswordField.setText("");

        dbConnection.setUser(username,password,((ClassToComboBox)avatarComboBox.getSelectionModel().getSelectedItem()).getPath());
        succesLabel.setText("Succesfuly Created Player "+ username);
        usernameTextField.setDisable(true);
        paswordPaswordField.setDisable(true);
        creatUserButton.setDisable(true);

    }
    public void  avatarComboboxAction(){
        errorLabel.setText("");
        ClassToComboBox classToComboBox = (ClassToComboBox) avatarComboBox.getSelectionModel().getSelectedItem();
        Image image = new Image(classToComboBox.getPath());
        avatarImageView.setImage(image);
    }
    public void  backToMenuAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/menuScreen.fxml"));
        Parent pane =(Parent) fxmlLoader.load();
        Scene scene = new Scene(pane);
        Stage stage = (Stage) ((Node)backToMenuButton).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        String css = this.getClass().getResource("/css/aplication.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("MENU");
        stage.show();

    }
}
