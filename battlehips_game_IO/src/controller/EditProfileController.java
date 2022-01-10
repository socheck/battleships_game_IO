package controller;

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
import sample.User;

import java.io.IOException;
import java.util.ArrayList;

class ClassToComboBox{
    private String path;
    private String dispayText;

    public ClassToComboBox(String dispayText) {
        this.path = "/img/avatars/"+ dispayText + ".jpg";
        this.dispayText = dispayText;
    }

    @Override
    public String toString() {
        return dispayText;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDispayText() {
        return dispayText;
    }

    public void setDispayText(String dispayText) {
        this.dispayText = dispayText;
    }
}
public class EditProfileController {

    @FXML
    private Button confirbButton;
    @FXML
    private TextField oldPasswordField;
    @FXML
    private TextField newPasswordField;
    @FXML
    private ComboBox avatarComboBox;
    @FXML
    public ImageView xd;
    @FXML
    public Label errorPaswordLabel;
    @FXML
    public Label succesLabel;
    @FXML
    public Button backToStatisticButton;
    private User selectedUser;

    ArrayList<ClassToComboBox> avatarsList = new ArrayList<>();

    private ObservableList<ClassToComboBox> avatarObservableList;
    private ListProperty<ClassToComboBox> listPropertyAvatar;

    public void initialize(){
        for(int k = 1; k < 11; k++ ){
            avatarsList.add(new ClassToComboBox("avatar_"+ k));
        }
        listPropertyAvatar = new SimpleListProperty<>(); //OBSlUGA Comboboxa z listy mieszkan
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
        String oldPassword = oldPasswordField.getText();
        String newPassword = newPasswordField.getText();
        String newPath;

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

        if(avatarComboBox.getSelectionModel().getSelectedItem() == null){
            newPath = selectedUser.getAvatar_path();
        }
        //zapisujemy do bazy


        oldPasswordField.setText("");
        newPasswordField.setText("");
        succesLabel.setText("Successfully changed!");
        oldPasswordField.setDisable(true);
        newPasswordField.setDisable(true);
        confirbButton.setDisable(true);
        avatarComboBox.setDisable(true);


    }

    public User getSelectedUser() {
        return selectedUser;
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
        stage.setTitle("Statistic/Profile");
        stage.show();

    }
}
