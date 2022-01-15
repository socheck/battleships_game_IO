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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

public class EditProfileController {

    @FXML
    public Button confirbButton;
    @FXML
    public TextField oldPasswordField;
    @FXML
    public TextField newPasswordField;
    @FXML
    public ComboBox avatarComboBox;
    @FXML
    public ImageView xd;
    @FXML
    public Label errorPaswordLabel;
    @FXML
    public Label succesLabel;
    @FXML
    public Button backToStatisticButton;
    @FXML
    public Button changeAvatarButton;
    private User selectedUser;

    ArrayList<ClassToComboBox> avatarsList = new ArrayList<>();

    private ObservableList<ClassToComboBox> avatarObservableList;
    private ListProperty<ClassToComboBox> listPropertyAvatar;

    public void initialize(){
        ArrayList<String> results = new ArrayList<String>();
        File[] files = new File("src/img/avatars/user").listFiles();
        for (File file : files) {
            if (file.isFile()) {
                results.add(file.getName());
            }
        }
        for (String s:
             results) {
            avatarsList.add(new ClassToComboBox(s.replaceFirst("[.][^.]+$", "")));
        }
        listPropertyAvatar = new SimpleListProperty<>(); //OBSlUGA Comboboxa z listy
        avatarObservableList = FXCollections.observableArrayList(avatarsList);
        listPropertyAvatar.set(avatarObservableList);
        avatarComboBox.itemsProperty().bindBidirectional(listPropertyAvatar);

    }
    public void setAvatarView(){
        Image photo = new Image(selectedUser.getAvatar_path());
        xd.setImage(photo);
        for (ClassToComboBox classToComboBox :
                avatarsList) {
            if(classToComboBox.getPath().equals(selectedUser.getAvatar_path())){
                avatarComboBox.setPromptText(classToComboBox.getDispayText());
                return;
            }
        }
    }

    @FXML
    private void confirmAction(){
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md5.update(StandardCharsets.UTF_8.encode(oldPasswordField.getText()));
        String oldPassword = String.format("%032x", new BigInteger(1, md5.digest()));

        MessageDigest md52 = null;
        try {
            md52 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md52.update(StandardCharsets.UTF_8.encode(newPasswordField.getText()));
        String newPassword = String.format("%032x", new BigInteger(1, md52.digest()));

        if(oldPassword ==""){
            errorPaswordLabel.setText("InvalidData");
            return;
        }
        if(newPassword ==""){
            errorPaswordLabel.setText("InvalidData");
            return;
        }
        if(!oldPassword.equals(selectedUser.getPassword())){
            errorPaswordLabel.setText("InvalidData");
            return;
        }
        errorPaswordLabel.setText("");

        DbConnection dbConnection = new DbConnection();
        dbConnection.updateUser_password(newPassword, selectedUser.getId());

        oldPasswordField.setText("");
        newPasswordField.setText("");
        succesLabel.setText("Successfully changed!");
        oldPasswordField.setDisable(true);
        newPasswordField.setDisable(true);
        confirbButton.setDisable(true);
        avatarComboBox.setDisable(true);
        changeAvatarButton.setDisable(true);

    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }
    public void selectNewAvatarAction(){
        succesLabel.setText("");
        ClassToComboBox classToComboBox = (ClassToComboBox) avatarComboBox.getSelectionModel().getSelectedItem();
        Image image = new Image(classToComboBox.getPath());
        xd.setImage(image);
    }
    public void  backToStatisticAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/statisticProfileScreen.fxml"));
        Parent pane =(Parent) fxmlLoader.load();
        Scene scene = new Scene(pane);
        Stage stage = (Stage) ((Node)backToStatisticButton).getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setResizable(true);
        String css = this.getClass().getResource("/css/application.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("Statistic/Profile");
        stage.show();

    }

    public void changeAvatarAction(){
        String newPath;

        if(avatarComboBox.getSelectionModel().getSelectedItem() == null){
            newPath = selectedUser.getAvatar_path();
        }else {
            newPath = ((ClassToComboBox)avatarComboBox.getSelectionModel().getSelectedItem()).getPath();
        }
        if(newPath.equals(selectedUser.getAvatar_path())){
            return;
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md5.update(StandardCharsets.UTF_8.encode(oldPasswordField.getText()));
        String oldPassword = String.format("%032x", new BigInteger(1, md5.digest()));

        if(oldPassword ==""){
            errorPaswordLabel.setText("InvalidData");
            return;
        }
        if(!oldPassword.equals(selectedUser.getPassword())){
            errorPaswordLabel.setText("InvalidData");
            return;
        }
        errorPaswordLabel.setText("");

        DbConnection dbConnection = new DbConnection();
        dbConnection.updateUser_avatar(newPath, selectedUser.getId());
        oldPasswordField.setText("");
        newPasswordField.setText("");
        succesLabel.setText("Successfully changed!");
        oldPasswordField.setDisable(true);
        newPasswordField.setDisable(true);
        confirbButton.setDisable(true);
        avatarComboBox.setDisable(true);
        changeAvatarButton.setDisable(true);
    }
}
