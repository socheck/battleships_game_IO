package controller;

import db.DbConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.sqlite.core.DB;
import sample.User;

import java.io.IOException;
import java.util.ArrayList;

public class StaticticProfileController {
    @FXML
    public Button exitButton;
    @FXML
    public Button backToMenuButton;
    @FXML
    public TableView statisticTableView;
    @FXML
    public TableColumn<User,String> avatarTableColumn;
    @FXML
    public TableColumn<User,String> UsarnameTableColumn;
    @FXML
    public TableColumn<User,String> winsTablecolumn;
    @FXML
    public TableColumn<User,String> battlesTablecolumn;

    @FXML
    public TableColumn<User,String> hitsTablecolumn;

    @FXML
    public TableColumn<User,String> allShootsTablecolumn;

    @FXML
    public TableColumn<User,String> ratioTablecolumn;

    @FXML
    public Button modyfiProfileButton;


    private  User selectedUser;


    private DbConnection dbConnection;
    ArrayList<User> userArrayList;
    public void initialize(){
        dbConnection = new DbConnection();
        userArrayList = dbConnection.getUser_list();
        avatarTableColumn.setCellValueFactory(new PropertyValueFactory<>("photo"));
        UsarnameTableColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        winsTablecolumn.setCellValueFactory(new PropertyValueFactory<>("wins"));
        battlesTablecolumn.setCellValueFactory(new PropertyValueFactory<>("battles"));
        hitsTablecolumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        allShootsTablecolumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        ratioTablecolumn.setCellValueFactory(new PropertyValueFactory<>("aim_ratio"));


        for (User u :
                userArrayList) {
            statisticTableView.getItems().add(u);
        }
        modyfiProfileButton.setDisable(true);

    }

    public void modyfiProfileAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/editProfileScreen.fxml"));
        Parent pane =(Parent) fxmlLoader.load();
        EditProfileController editProfileController = (EditProfileController)  fxmlLoader.getController();
        Scene scene = new Scene(pane);
        Stage stage = (Stage) ((Node)modyfiProfileButton).getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setTitle("Modyfi profile");
        stage.show();
        editProfileController.setSelectedUser(selectedUser);
        editProfileController.setAvatarView();

    }

    public void selectProfileOnMouseClicked(){
         selectedUser = (User) statisticTableView.getSelectionModel().getSelectedItem();
         modyfiProfileButton.setDisable(false);

    }

















    @FXML
    public void exitAction(){
        ((Stage) exitButton.getScene().getWindow()).close();
    }
    @FXML
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
