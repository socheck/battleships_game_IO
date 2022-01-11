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
import sample.ClassToComboBox;
import sample.User;

import java.io.IOException;
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
    public ComboBox avatarComboBox;
    @FXML
    public ImageView avatarImageView;
    @FXML
    public Label errorLabel;



    ArrayList<ClassToComboBox> avatarsList = new ArrayList<>();

    private ObservableList<ClassToComboBox> avatarObservableList;
    private ListProperty<ClassToComboBox> listPropertyAvatar;

    public void initialize(){
        for(int k = 1; k < 11; k++ ){
            avatarsList.add(new ClassToComboBox("avatar_"+ k));
        }
        listPropertyAvatar = new SimpleListProperty<>(); //OBSlUGA Comboboxa z listy
        avatarObservableList = FXCollections.observableArrayList(avatarsList);
        listPropertyAvatar.set(avatarObservableList);
        avatarComboBox.itemsProperty().bindBidirectional(listPropertyAvatar);


    }

    public void  createUserAction(){
        errorLabel.setText("");
        DbConnection dbConnection = new DbConnection();
        String username = usernameTextField.getText();
        String password = paswordPaswordField.getText();
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
        ArrayList<User> userArrayList = dbConnection.getUser_list();

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
        stage.setTitle("MENU");
        stage.show();

    }


}
